package bowling;

import bowling.io.DataLoader;
import bowling.io.impl.ConsoleDisplay;
import bowling.io.ScoreDisplay;
import bowling.io.impl.TextFileLoader;
import bowling.model.BowlingGame;

public class Main {

    public static void main(String[] args) {
        ScoreDisplay display = new ConsoleDisplay();
        DataLoader dataLoader = new TextFileLoader();
        BowlingGame game = new BowlingGame(dataLoader.load(), display);
        game.displayScores();
    }
}

