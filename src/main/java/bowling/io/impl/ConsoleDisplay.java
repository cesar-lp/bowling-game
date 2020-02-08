package bowling.io.impl;

import bowling.io.ScoreDisplay;
import bowling.model.Player;
import bowling.model.Point;

import java.util.List;
import java.util.stream.IntStream;

public class ConsoleDisplay implements ScoreDisplay {

    @Override
    public void display(List<Player> players) {
        displayFrames();
        players.stream().forEachOrdered(player -> {
            displayPlayerName(player);
            displayPlayerPinfalls(player);
            displayPlayerScores(player);
        });
    }

    private void displayFrames() {
        System.out.printf("%-20s", "Frame");
        IntStream.range(1, 11).forEachOrdered(i -> System.out.printf("%d\t\t", i));
        System.out.println();
    }

    private void displayPlayerName(Player player) {
        System.out.printf("%-20s\n", player.getName());
    }

    private void displayPlayerPinfalls(Player player) {
        System.out.printf("%-20s", "Pinfalls");
        player.getPoints().forEach(Point::printToConsole);
        System.out.println();
    }

    private void displayPlayerScores(Player player) {
        System.out.printf("%-20s", "Score");
        player.printScores();
    }
}
