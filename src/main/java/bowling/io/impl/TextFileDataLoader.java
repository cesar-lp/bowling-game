package bowling.io.impl;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.pojo.PlayerShoot;
import bowling.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class TextFileDataLoader implements DataLoader {

    private static final Logger logger = LoggerFactory.getLogger(TextFileDataLoader.class.getName());
    private static final String WHITESPACE_SEPARATOR = " ";

    private final PlayerFactory playerFactory;

    public TextFileDataLoader(PlayerFactory playerFactory) {
        this.playerFactory = playerFactory;
    }

    @Override
    public List<Player> loadPlayers() {
        List<String> fileLines = readFile();
        List<Player> players = new ArrayList<>();

        try {
            fileLines.stream()
                    .map(this::extractNameAndScore)
                    .map(PlayerShoot::fromStringParts)
                    .forEachOrdered(playerShoot -> playerFactory.registerPlayerShoot(players, playerShoot));
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.error(illegalArgumentException.getMessage());
            System.exit(1);
        }

        return players;
    }

    private List<String> readFile() {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        boolean fileNotFound = true;

        while (fileNotFound) {
            String fileName = requestFileName(scanner);

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                lines = reader.lines().collect(toList());
                fileNotFound = false;
            } catch (FileNotFoundException e) {
                logger.error("File {} was not found, please try again.", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lines;
    }

    private String requestFileName(Scanner scanner) {
        System.out.println("\nEnter file to scan: ");
        return scanner.nextLine();
    }

    private String[] extractNameAndScore(String line) {
        String[] parts = line.split(WHITESPACE_SEPARATOR);

        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Expected to found 2 values (player name and pines knocked over) but found " +
                            parts.length);
        }

        return parts;
    }
}

