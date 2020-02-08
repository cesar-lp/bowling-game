package bowling.io.impl;

import bowling.io.DataLoader;
import bowling.model.Player;
import bowling.model.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class TextFileLoader implements DataLoader {

    public List<Player> readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            List<Player> players = new ArrayList<>();

            Point currentPoint = null;

            List<String> lines = Files
                    .lines(Paths.get("data.txt"))
                    .collect(Collectors.toList());

            for (String line : lines) {
                String[] splitted = line.split(" ");

                if (splitted[1].equals("10")) {
                    int i = players.indexOf(new Player(splitted[0]));

                    if (i > -1) {
                        players.get(i).addPoint(Point.strike());
                    } else {
                        players.add(new Player(splitted[0], Point.strike()));
                    }
                } else {
                    if (currentPoint == null) {
                        currentPoint = new Point(splitted[1]);
                    } else {
                        currentPoint.trackSecondPoint(splitted[1]);


                        int i = players.indexOf(new Player(splitted[0]));

                        if (i > -1) {
                            players.get(i).addPoint(currentPoint);
                        } else {
                            players.add(new Player(splitted[0], currentPoint));
                        }

                        currentPoint = null;
                    }
                }
            }

            return players;
        } catch (FileNotFoundException fileNotFoundExc) {
            fileNotFoundExc.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyList();
    }

    @Override
    public List<Player> load() {
        return null;
    }
}
