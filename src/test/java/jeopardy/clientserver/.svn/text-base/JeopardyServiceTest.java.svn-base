package jeopardy.clientserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Game;
import jeopardy.domain.Player;
import jeopardy.exception.AlreadyTakeException;
import jeopardy.exception.GameAlreadyStartedException;
import jeopardy.exception.GameNotStartedException;
import jeopardy.exception.NoSuchAnswerQuestionException;
import jeopardy.exception.PlayerNameAlreadyUsedException;
import jeopardy.io.BoardTest;

import org.junit.Before;
import org.junit.Test;

public class JeopardyServiceTest {
    private static final String CATEGORY = "Category";
    private static final String P1 = "P1";
    private static final String P2 = "P2";
    private static final String P3 = "P3";
    private JeopardyService js;

    @Before
    public void createServerAndMakeItReadyToAcceptConnections() {
        js = new JeopardyService();
        Game g = new Game();
        g.setOutputStream(new PrintStream(new ByteArrayOutputStream()));
        g.setBoard(BoardTest.createBoard());
        js.setGame(g);
        js.create();
    }

    @Test(expected = PlayerNameAlreadyUsedException.class)
    public void testJoinPlayerWithSameNameFails() {
        js.join(P1);
        js.join(P1);
        assertEquals(2, js.getPlayerCount());
    }

    @Test(expected = GameAlreadyStartedException.class)
    public void testJoinPlayersAfterGameStarted() {
        js.join(P1);
        js.start();
        js.join(P2);
    }

    @Test(expected = GameNotStartedException.class)
    public void testSelectFoundCategoryWhenGameNotStarted() {
        js.join(P1);
        js.select(CATEGORY, 200);
    }

    @Test
    public void testSelectFoundCategoryDollarAmount() {
        js.join(P1);
        js.start();
        AnswerQuestion aq = js.select(CATEGORY, 200);
        assertNotNull(aq);
    }

    @Test
    public void testTakeAnswerQuestion() {
        Player p1 = js.join(P1);
        js.start();
        js.select(CATEGORY, 200);
        js.take(P1);
        assertEquals(p1, js.getCurrentPlayer());
    }

    @Test(expected = AlreadyTakeException.class)
    public void testAttemptToTakeAfterAnswerAlreadyTaken() {
        js.join(P1);
        js.join(P2);
        js.start();
        js.select(CATEGORY, 200);
        js.take(P1);
        js.take(P2);
    }

    @Test
    public void testAnsweredCorrectly() {
        js.join(P1);
        js.join(P2);
        js.start();
        js.select(CATEGORY, 200);
        js.take(P1);
        js.answeredCorrectly();
        assertEquals(200, js.scoreFor(P1));
    }

    @Test
    public void testAnsweredIncorrectly() {
        js.join(P1);
        js.join(P2);
        js.start();
        js.select(CATEGORY, 200);
        js.take(P1);
        js.answeredIncorrectly();
        assertEquals(-200, js.scoreFor(P1));
    }

    @Test
    public void testAnsweredCorrectlyButLate() {
        js.setAnswerQuestionTime(-1);
        js.join(P1);
        js.start();
        js.select(CATEGORY, 200);
        js.take(P1);
        js.answeredCorrectly();
        assertEquals(-200, js.scoreFor(P1));
    }

    @Test
    public void testNobodyAnswered() {
        js.join(P1);
        js.join(P2);
        js.start();
        js.select(CATEGORY, 200);
        js.nobodyAnswered();
        assertEquals(0, js.scoreFor(P1));
        assertEquals(0, js.scoreFor(P2));
    }

    @Test(expected = NoSuchAnswerQuestionException.class)
    public void testSelectingAnswerQuestionNotFound() {
        js.join(P1);
        js.start();
        js.select(CATEGORY, 100000);
    }

    @Test
    public void testGameOver() {
        js.join(P1);
        js.join(P2);
        js.join(P3);
        js.start();
        js.select(CATEGORY, 200);
        js.take(P1);
        js.answeredCorrectly();
        js.select(CATEGORY, 300);
        js.take(P2);
        js.answeredCorrectly();
        js.select(CATEGORY, 500);
        js.take(P3);
        js.answeredIncorrectly();
        List<Player> players = js.gameOver();
        assertEquals(200, players.get(0).getBalance());
        assertEquals(300, players.get(1).getBalance());
        assertEquals(-500, players.get(2).getBalance());
    }

}
