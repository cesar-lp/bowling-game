package bowling.io.impl;

import bowling.exception.InvalidFileException;
import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.InputWrapper;
import bowling.io.OutputWrapper;
import bowling.io.pojo.PlayerShoot;
import bowling.model.Player;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static bowling.utils.FileUtils.WHITESPACE_SEPARATOR;
import static java.util.stream.Collectors.toList;

public class TextFileDataLoader implements DataLoader {

    private static final Logger logger = LoggerFactory.getLogger(TextFileDataLoader.class.getName());

    private final OutputWrapper outputWrapper;
    private final InputWrapper inputWrapper;
    private final PlayerFactory playerFactory;

    public TextFileDataLoader(InputWrapper inputWrapper, OutputWrapper outputWrapper, PlayerFactory playerFactory) {
        this.inputWrapper = inputWrapper;
        this.outputWrapper = outputWrapper;
        this.playerFactory = playerFactory;
    }

    @Override
    public List<Player> loadPlayers() {
        List<String> fileLines = readFile();
        List<Player> players = new ArrayList<>();

        if (fileLines.size() < 10) {
            throw new InvalidFileException("File must have at least 10 lines, found " + fileLines.size());
        }

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
        List<String> lines = new ArrayList<>();
        boolean fileNotFound = true;

        while (fileNotFound) {
            File file = inputWrapper.getFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                lines = reader.lines().collect(toList());
                fileNotFound = false;
            } catch (FileNotFoundException | NullPointerException e) {
                logger.error("File {} was not found, please try again.", inputWrapper.getFileName());
                System.exit(1);
            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return lines;
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

