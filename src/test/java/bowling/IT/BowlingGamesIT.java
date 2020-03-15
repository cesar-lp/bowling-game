package bowling.IT;

import bowling.helper.PlayerFactory;
import bowling.io.DataLoader;
import bowling.io.InputWrapper;
import bowling.io.OutputWrapper;
import bowling.io.ScoreDisplay;
import bowling.io.TestInputWrapper;
import bowling.io.TestOutputWrapper;
import bowling.io.impl.ConsoleDisplay;
import bowling.io.impl.TextFileDataLoader;
import bowling.model.BowlingGame;
import bowling.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGamesIT {

    OutputWrapper outputWrapper = new TestOutputWrapper();
    ScoreDisplay display = new ConsoleDisplay(outputWrapper);

    @Before
    public void before() {
        ((TestOutputWrapper) outputWrapper).clearResults();
    }

    @Test
    public void runZeroScoreTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/zero_score_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());
        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        String expectedResult =
                "\nFrame            \t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                        "Carl\n" +
                        "Pinfalls         \t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t\n" +
                        "Score            \t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";

        assertEquals(1, game.getPlayers().size());
        assertEquals("Carl", game.getPlayers().get(0).getName());
        assertEquals(0, game.getPlayers().get(0).getTotalScore());
        assertEquals(expectedResult, getGameResult());
    }

    @Test
    public void runPerfectScoreTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/perfect_score_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());
        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        String expectedResult =
                "\nFrame            \t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                        "Carl\n" +
                        "Pinfalls         \t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\t\n" +
                        "Score            \t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";

        assertEquals(1, game.getPlayers().size());
        assertEquals("Carl", game.getPlayers().get(0).getName());
        assertEquals(300, game.getPlayers().get(0).getTotalScore());
        assertEquals(expectedResult, getGameResult());
    }

    @Test
    public void runAllFoulsGameTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/all_fouls_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());
        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        String expectedResult =
                "\nFrame            \t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                        "Carl\n" +
                        "Pinfalls         \tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\t\n" +
                        "Score            \t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";

        assertEquals(1, game.getPlayers().size());
        assertEquals("Carl", game.getPlayers().get(0).getName());
        assertEquals(0, game.getPlayers().get(0).getTotalScore());
        assertEquals(expectedResult, getGameResult());
    }

    @Test
    public void runTwoPlayerSampleGameTest() {
        InputWrapper inputWrapper = new TestInputWrapper("/two_player_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());
        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        String expectedResult =
                "\nFrame            \t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                        "Jeff\n" +
                        "Pinfalls         \t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\t\n" +
                        "Score            \t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" +
                        "John\n" +
                        "Pinfalls         \t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\t\n" +
                        "Score            \t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";

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

        assertEquals(expectedResult, getGameResult());
    }

    @Test
    public void runGameWithExtraShootsBeyondTenTurn() {
        InputWrapper inputWrapper = new TestInputWrapper("/extra_shoots_game.txt");
        DataLoader dataLoader = new TextFileDataLoader(inputWrapper, outputWrapper, new PlayerFactory());
        BowlingGame game = new BowlingGame(dataLoader, display);
        game.displayScores();

        String expectedResult =
                "\nFrame            \t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\n" +
                "Jeff\n" +
                "Pinfalls         \t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\t\n" +
                "Score            \t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" +
                "John\n" +
                "Pinfalls         \t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\t\n" +
                "Score            \t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";

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

        assertEquals(expectedResult, getGameResult());
    }

    private String getGameResult() {
        return ((TestOutputWrapper) outputWrapper).getResult();
    }
}
