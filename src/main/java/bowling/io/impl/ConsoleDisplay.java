package bowling.io.impl;

import bowling.io.ScoreDisplay;
import bowling.model.Player;
import bowling.model.Turn;

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
        System.out.printf("\n%-20s", "Frame");
        IntStream.range(1, 11).forEachOrdered(i -> System.out.printf("%d\t\t", i));
        System.out.println();
    }

    private void displayPlayerName(Player player) {
        System.out.printf("%-20s\n", player.getName());
    }

    private void displayPlayerPinfalls(Player player) {
        System.out.printf("%-20s", "Pinfalls");

        Integer frame = 1;

        for (int i = 0; i < 10; i++) {
            Turn turn = player.getTurns().get(i);
            StringBuilder text = new StringBuilder();

            if (i == 9) {
                List<Turn> tenRoundTurns = player.getTurns().subList(9, player.getTurns().size());
                displayTenRoundPointPinfalls(tenRoundTurns);
                continue;
            }

            if (turn.isStrike()) {
                System.out.printf("\t%s\t", turn.getPointType());
                continue;
            }

            System.out.printf("%s\t%s\t", turn.getFirstShootDesc(), turn.getSecondChanceDesc());
        }

        System.out.println();
    }

    private void displayTenRoundPointPinfalls(List<Turn> tenRoundTurns) {
        for (Turn p : tenRoundTurns) {
            if (p.isStrike()) {
                System.out.printf("%s\t", p.getPointType());
            } else {
                System.out.printf("%s\t%s\t", p.getFirstShootDesc(), p.getSecondChanceDesc());
            }
        }
    }

    private void displayPlayerScores(Player player) {
        System.out.printf("%-20s", "Score");
        player.getScores()
                .stream()
                .forEachOrdered(score -> System.out.printf("%d\t\t", score));
        System.out.println();
    }
}
