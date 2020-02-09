package bowling.helper;

import bowling.model.Player;
import bowling.model.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ScoreCalculator {

    private ScoreCalculator() {}

    public static void calculatePlayerScores(Player player) {
        player.setScores(getScores(player.getTurns()));
        player.setTotalScore(getTotalScore(player.getScores()));
    }

    private static List<Integer> getScores(List<Turn> turns) {
        List<Integer> scores = new ArrayList<>();
        ListIterator<Turn> iterator = turns.listIterator();
        int total = 0;
        int frame = 1;

        while (iterator.hasNext()) {
            total += getScore(iterator, iterator.next(), 0, frame == 10);
            scores.add(total);
            frame++;
        }

        return scores;
    }

    private static int getScore(ListIterator<Turn> iterator, Turn currentTurn, int strikesCounter, boolean lastFrame) {
        int total = 0;

        if (currentTurn.isRegular()) {
            total += strikesCounter < 2 ? currentTurn.getScore() : currentTurn.getAmountKnockedOverOnFirstShoot();
        }

        if (currentTurn.isSpare()) {
            if (strikesCounter == 1) {
                total += currentTurn.getScore();
            } else if (strikesCounter == 2) {
                total += currentTurn.getAmountKnockedOverOnFirstShoot();
            } else {
                total += currentTurn.getScore();

                if (iterator.hasNext()) {
                    total += iterator.next().getAmountKnockedOverOnFirstShoot();
                    iterator.previous();
                }
            }
        }

        if (currentTurn.isStrike()) {
            total += 10;

            if (strikesCounter < 2) {
                strikesCounter++;

                if (iterator.hasNext()) {
                    Turn nextTurn = iterator.next();

                    total += getScore(iterator, nextTurn, strikesCounter, lastFrame);

                    if (!lastFrame) {
                        iterator.previous();
                    }
                }
            }
        }

        if (lastFrame) {
            while (iterator.hasNext()) {
                total += getScore(iterator, iterator.next(), 0, true);
            }
        }

        return total;
    }

    private static int getTotalScore(List<Integer> scores) {
        return scores.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
