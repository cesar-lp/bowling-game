package bowling;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.InputWrapper;
import bowling.io.OutputWrapper;
import bowling.io.ScoreDisplay;
import bowling.io.impl.CommandLineInputWrapper;
import bowling.io.impl.ConsoleDisplay;
import bowling.io.impl.ConsoleOutputWrapper;
import bowling.io.impl.TextFileDataLoader;
import bowling.model.BowlingGame;

public class BowlingGameResultsReader {

    public static void main(String[] args) {
        InputWrapper inputWrapper;

        if (args.length == 0) {
            throw new IllegalArgumentException("File name was not provided");
        } else if (args.length > 1) {
            throw new IllegalArgumentException("Only one file name is expected at a time");
        } else {
            inputWrapper = new CommandLineInputWrapper(args[0]);
        }

        OutputWrapper outputWrapper = new ConsoleOutputWrapper();
        ScoreDisplay display = new ConsoleDisplay(outputWrapper);
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());

        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();
    }
}
