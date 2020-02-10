package bowling;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.ScoreDisplay;
import bowling.io.impl.ConsoleDisplay;
import bowling.io.impl.TextFileDataLoader;
import bowling.model.BowlingGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {


        ScoreDisplay display = new ConsoleDisplay();
        DataLoader dataLoader = new TextFileDataLoader(new PlayerFactory());
        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();
    }

}
