package bowling.IT;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.InputWrapper;
import bowling.io.OutputWrapper;
import bowling.io.ScoreDisplay;
import bowling.io.TestInputWrapper;
import bowling.io.impl.ConsoleDisplay;
import bowling.io.impl.ConsoleOutputWrapper;
import bowling.io.impl.TextFileDataLoader;
import bowling.model.BowlingGame;
import bowling.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGamesIT {

    OutputWrapper outputWrapper = new ConsoleOutputWrapper();
    ScoreDisplay display = new ConsoleDisplay(outputWrapper);

    @Test
    public void runZeroScoreTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/zero_score_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());

        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        assertEquals(1, game.getPlayers().size());
        assertEquals("Carl", game.getPlayers().get(0).getName());
        assertEquals(0, game.getPlayers().get(0).getTotalScore());
    }

    @Test
    public void runPerfectScoreTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/perfect_score_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());

        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        assertEquals(1, game.getPlayers().size());
        assertEquals("Carl", game.getPlayers().get(0).getName());
        assertEquals(300, game.getPlayers().get(0).getTotalScore());
    }

    @Test
    public void runAllFoulsGameTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/all_fouls_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());

        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        assertEquals(1, game.getPlayers().size());
        assertEquals("Carl", game.getPlayers().get(0).getName());
        assertEquals(0, game.getPlayers().get(0).getTotalScore());
    }

    @Test
    public void runTwoPlayerSampleGameTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/two_player_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());

        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        assertEquals(2, game.getPlayers().size());

        Player player = game.getPlayers().get(0);
        {
            assertEquals("Jeff", player.getName());
            assertEquals(167, player.getTotalScore());
        }

        player = game.getPlayers().get(1);
        {
            assertEquals("John", player.getName());
            assertEquals(151, player.getTotalScore());
        }
    }

    @Test
    public void runGameWithExtraShootsBeyondTenTurn() {
        InputWrapper inputWrapper = new TestInputWrapper("/extra_shoots_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());

        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        assertEquals(2, game.getPlayers().size());

        Player player = game.getPlayers().get(0);
        {
            assertEquals("Jeff", player.getName());
            assertEquals(167, player.getTotalScore());
        }

        player = game.getPlayers().get(1);
        {
            assertEquals("John", player.getName());
            assertEquals(151, player.getTotalScore());
        }
    }
}
