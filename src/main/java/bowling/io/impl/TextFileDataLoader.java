package bowling.io.impl;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.pojo.PlayerShoot;
import bowling.model.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class TextFileDataLoader implements DataLoader {

    private static final String WHITESPACE_SEPARATOR = " ";
    private final String filename;
    private final PlayerFactory playerFactory;

    public TextFileDataLoader(String filename, PlayerFactory playerFactory) {
        this.filename = filename;
        this.playerFactory = playerFactory;
    }

    @Override
    public List<Player> loadPlayers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> lines = reader.lines().collect(toList());
            List<Player> players = new ArrayList<>();

            lines.stream()
                    .map(this::extractNameAndScore)
                    .map(PlayerShoot::fromStringParts)
                    .forEachOrdered(playerShoot -> playerFactory.registerPlayerShoot(players, playerShoot));

            return players;
        } catch (FileNotFoundException fileNotFoundExc) {
            fileNotFoundExc.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyList();
    }

    private String[] extractNameAndScore(String line) {
        String[] splitted = line.split(WHITESPACE_SEPARATOR);

        if (splitted.length != 2) {
            throw new IllegalArgumentException(
                    "Expected to found 2 values (player name and pines knocked over) but found " +
                            splitted.length);
        }

        return splitted;
    }
}

