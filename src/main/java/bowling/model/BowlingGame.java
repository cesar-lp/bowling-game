package bowling.model;

import bowling.io.ScoreDisplay;

import java.util.List;

public class BowlingGame {

    private final List<Player> players;
    private final ScoreDisplay scoreDisplay;

    public BowlingGame(List<Player> players, ScoreDisplay scoreDisplay) {
        this.players = players;
        this.scoreDisplay = scoreDisplay;
    }

    public void displayScores() {
        scoreDisplay.display(players);
    }
}
