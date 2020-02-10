package bowling.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {

    private String name;
    private List<Turn> turns = new LinkedList<>();
    private List<Integer> scores = new ArrayList<>();
    private Integer totalScore;

    public Player(String name) {
        this.name = name;
    }

    public void registerTurn(Turn turn) {
        turns.add(turn);
    }

    public String getName() {
        return name;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public boolean didNotStrikeOnTenthTurn() {
        return !getTenthTurn().isStrike();
    }

    public Turn getTenthTurn() {
        return turns.get(9);
    }

    public Turn getLastTurn() {
        return turns.get(turns.size() - 1);
    }

    public boolean isLastTurnInProgress() {
        return getLastTurn().isInProgress();
    }

    public boolean isPlayingTenthTurn() {
        return turns.size() == 10;
    }

    public boolean isPlayingExtraTurn() {
        return turns.size() == 11;
    }

    public boolean hasCompletedExtraTurn() {
        Turn tenthTurn = getTenthTurn();
        return (tenthTurn.isStrike() || tenthTurn.isSpare()) && !isLastTurnInProgress();
    }

    public void registerSecondShoot(Shoot shoot) {
        getLastTurn().registerSecondShoot(shoot);
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
