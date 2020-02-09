package bowling;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.impl.ConsoleDisplay;
import bowling.io.ScoreDisplay;
import bowling.io.impl.TextFileDataLoader;
import bowling.model.BowlingGame;

public class Main {

    public static void main(String[] args) {
        ScoreDisplay display = new ConsoleDisplay();
        DataLoader dataLoader = new TextFileDataLoader("game.txt", new PlayerFactory());
        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();
    }
}

