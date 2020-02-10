package bowling.model;

import bowling.helper.ScoreCalculator;
import bowling.io.DataLoader;
import bowling.io.ScoreDisplay;

import java.util.List;

public class BowlingGame {

    private final List<Player> players;
    private final ScoreDisplay scoreDisplay;

    public BowlingGame(DataLoader dataLoader, ScoreDisplay scoreDisplay) {
        this.scoreDisplay = scoreDisplay;
        this.players = dataLoader.loadPlayers();
        players.forEach(ScoreCalculator::calculatePlayerScores);
    }

    public void displayScores() {
        scoreDisplay.display(players);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
