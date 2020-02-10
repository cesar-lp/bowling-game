package bowling.io.impl;

import bowling.io.OutputWrapper;
import bowling.io.ScoreDisplay;
import bowling.model.Player;
import bowling.model.Turn;

import java.util.List;
import java.util.stream.IntStream;

public class ConsoleDisplay implements ScoreDisplay {

    private final OutputWrapper outputWrapper;

    public ConsoleDisplay(OutputWrapper outputWrapper) {
        this.outputWrapper = outputWrapper;
    }

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
        outputWrapper.displayFormatted("\n%-20s", "Frame");
        IntStream.range(1, 11).forEachOrdered(i -> outputWrapper.displayFormatted("%d\t\t", i));
        outputWrapper.displayNewLine();
    }

    private void displayPlayerName(Player player) {
        outputWrapper.displayFormatted("%-20s\n", player.getName());
    }

    private void displayPlayerPinfalls(Player player) {
        outputWrapper.displayFormatted("%-20s", "Pinfalls");

        for (int i = 0; i < 10; i++) {
            Turn turn = player.getTurns().get(i);
            StringBuilder text = new StringBuilder();

            if (i == 9) {
                List<Turn> tenRoundTurns = player.getTurns().subList(9, player.getTurns().size());
                displayTenthTurnPinfalls(tenRoundTurns);
                continue;
            }

            if (turn.isStrike()) {
                outputWrapper.displayFormatted("\t%s\t", turn.getScoreType());
                continue;
            }

            outputWrapper.displayFormatted("%s\t%s\t", turn.getFirstShootDesc(), turn.getSecondChanceDesc());
        }

        outputWrapper.displayNewLine();
    }

    private void displayTenthTurnPinfalls(List<Turn> tenthTurnPinfalls) {
        for (Turn turn : tenthTurnPinfalls) {
            if (turn.isStrike()) {
                outputWrapper.displayFormatted("%s\t", turn.getScoreType());
            } else {
                outputWrapper.displayFormatted("%s\t%s\t", turn.getFirstShootDesc(), turn.getSecondChanceDesc());
            }
        }
    }

    private void displayPlayerScores(Player player) {
        outputWrapper.displayFormatted("%-20s", "Score");
        player.getScores()
                .stream()
                .forEachOrdered(score -> outputWrapper.displayFormatted("%d\t\t", score));
        outputWrapper.displayNewLine();
    }
}
