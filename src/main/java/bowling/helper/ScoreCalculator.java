package bowling.helper;

import bowling.model.Player;
import bowling.model.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ScoreCalculator {

    private ScoreCalculator() {
    }

    public static void calculatePlayerScores(Player player) {
        player.setScores(getScores(player.getTurns()));
        player.setTotalScore(player.getScores().get(player.getScores().size() - 1));
    }

    private static List<Integer> getScores(List<Turn> turns) {
        List<Integer> scores = new ArrayList<>();
        ListIterator<Turn> iterator = turns.listIterator();
        int total = 0;
        int frame = 1;

        while (frame <= 10) {
            total += getScore(iterator, iterator.next(), 0, frame == 10);
            scores.add(total);
            frame++;
        }

        return scores;
    }

    private static int getScore(ListIterator<Turn> iterator, Turn currentTurn, int strikesCounter, boolean lastFrame) {
        int total = 0;

        if (currentTurn.isRegular()) {
            total += strikesCounter < 2 ? currentTurn.getScore() : currentTurn.getFirstShootScore();
        }

        if (currentTurn.isSpare()) {
            if (strikesCounter == 1) {
                total += currentTurn.getScore();
            } else if (strikesCounter == 2) {
                total += currentTurn.getFirstShootScore();
            } else {
                total += currentTurn.getScore();

                if (iterator.hasNext()) {
                    total += iterator.next().getFirstShootScore();
                    iterator.previous();
                }
            }
        }

        if (currentTurn.isStrike()) {
            total += 10;

            if (strikesCounter < 2) {
                strikesCounter++;

                if (lastFrame && iterator.hasNext()) {
                    Turn nextTurn = iterator.next();
                    total += currentTurn.isSpare() ? nextTurn.getFirstShootScore() : nextTurn.getScore();
                    return total;
                }

                if (iterator.hasNext()) {
                    Turn nextTurn = iterator.next();
                    total += getScore(iterator, nextTurn, strikesCounter, false);
                    iterator.previous();
                }
            }
        }

        return total;
    }
}
