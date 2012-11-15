package jeopardy.clientserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import jeopardy.commands.JeopardyObjectResponse;
import jeopardy.commands.JeopardyResponse;
import jeopardy.domain.Board;
import jeopardy.domain.Game;
import jeopardy.domain.Player;
import jeopardy.io.BoardTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JeopardyServerTest {
    private static final String PLAYER_NAME = "Brett";
    private static final String CATEGORY = "Category";
    private ServerMessageSender messageSender;

    @Test
    public void answeredCorrectlyInTime() throws Exception {
        verifyAnswerCorrectOrIncorrect(true);
    }

    @Test
    public void answeredIncorrectlyIgnoringTime() throws Exception {
        verifyAnswerCorrectOrIncorrect(false);
    }

    @Before
    public void createJeopardyServer() throws Exception {
        JeopardyService service = new JeopardyService();
        Game g = new Game();
        g.setOutputStream(new PrintStream(new ByteArrayOutputStream()));
        g.setBoard(BoardTest.createBoard());
        service.setGame(g);
        service.setAnswerQuestionTime(20 * 1000);

        JeopardyServer js = new JeopardyServer();
        js.setServerSocketTimeout(1);
        js.setService(service);
        js.setPort(8080);
        js.start();

        messageSender = new ServerMessageSender(js, "localhost", 8080);
    }

    @Test
    public void selectFoundCategory() throws Exception {
        messageSender.join(PLAYER_NAME);
        messageSender.start();
        assertTrue(messageSender.select(CATEGORY, 100).isSuccessful());
    }

    @After
    public void stopJeopardyServer() throws Exception {
        messageSender.terminateClient();
        messageSender.shutdownServer();
    }

    @Test
    public void takeBeforeSelectFails() throws Exception {
        messageSender.join(PLAYER_NAME);
        messageSender.start();
        JeopardyResponse jr = messageSender.take(PLAYER_NAME);
        assertTrue(!jr.isSuccessful());
    }

    @Test
    public void testJoinAfterStartFails() throws Exception {
        messageSender.join(PLAYER_NAME);
        messageSender.start();
        assertTrue(!messageSender.join("Larry").isSuccessful());
    }

    @Test
    public void testNobodyAnsweredNoQuestionSelected() throws Exception {
        messageSender.join(PLAYER_NAME);
        messageSender.start();
        assertTrue(!messageSender.nobody().isSuccessful());
    }

    @Test
    public void testNobodyAnsweredQuestionSelected() throws Exception {
        messageSender.join(PLAYER_NAME);
        messageSender.start();
        messageSender.select(CATEGORY, 100);
        assertTrue(messageSender.nobody().isSuccessful());
    }

    @Test
    public void testJoinSameNameFails() throws Exception {
        messageSender.join(PLAYER_NAME);
        assertTrue(!messageSender.join(PLAYER_NAME).isSuccessful());
    }

    @Test
    public void testStart() throws Exception {
        messageSender.join(PLAYER_NAME);
        JeopardyObjectResponse<Player> resp = messageSender.start();
        assertTrue(resp.isSuccessful());
        assertNotNull(resp.getValue());
    }

    @Test
    public void testSuccessfulJoin() throws Exception {
        JeopardyResponse jr = messageSender.join(PLAYER_NAME);
        assertTrue(jr.isSuccessful());
    }

    @Test
    public void getAvailableQuestions() throws Exception {
        JeopardyObjectResponse<Board> boardResp = messageSender.getBoard();
        assertTrue(boardResp.isSuccessful());
    }

    @Test
    public void getPlayers() throws Exception {
        JeopardyObjectResponse<List<Player>> resp = messageSender.getPlayers();
        assertTrue(resp.isSuccessful());
        assertEquals(0, resp.getValue().size());
        messageSender.join(PLAYER_NAME);
        resp = messageSender.getPlayers();
        assertTrue(resp.isSuccessful());
        assertEquals(1, resp.getValue().size());
    }

    private void verifyAnswerCorrectOrIncorrect(boolean correct) throws Exception {
        messageSender.join(PLAYER_NAME);
        messageSender.start();
        messageSender.select(CATEGORY, 100);
        messageSender.take(PLAYER_NAME);
        JeopardyObjectResponse<Player> resp = messageSender.questionAnswered(correct);
        if (correct) {
            assertEquals(100, resp.getValue().getBalance());
        } else {
            assertEquals(-100, resp.getValue().getBalance());
        }
    }
}
