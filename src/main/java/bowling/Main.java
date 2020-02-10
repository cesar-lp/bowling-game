package bowling;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.InputWrapper;
import bowling.io.OutputWrapper;
import bowling.io.ScoreDisplay;
import bowling.io.impl.ConsoleDisplay;
import bowling.io.impl.ConsoleInputWrapper;
import bowling.io.impl.ConsoleOutputWrapper;
import bowling.io.impl.TextFileDataLoader;
import bowling.model.BowlingGame;

public class Main {

    public static void main(String[] args) {
        InputWrapper inputWrapper = new ConsoleInputWrapper();
        OutputWrapper outputWrapper = new ConsoleOutputWrapper();
        ScoreDisplay display = new ConsoleDisplay(outputWrapper);
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());

        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();
    }
}
