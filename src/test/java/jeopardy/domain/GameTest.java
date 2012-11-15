package jeopardy.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import jeopardy.exception.AlreadyTakeException;
import jeopardy.exception.AnswerQuestionCurrentlySelectedException;
import jeopardy.exception.NoCurrentPlayerException;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private static final String P1 = "p1";
    private static final String P2 = "p2";
    private Board b;
    private Game g;

    @SuppressWarnings("serial")
    @Before
    public void createBoardAndGame() throws FileNotFoundException {
        b = new Board();
        b.add("C", 200, "A", "Q");
        g = new Game();
        g.setOutputStream(new PrintStream(new ByteArrayOutputStream()));
        g.setBoard(b);
        g.setAnswerTime(1);
        g.addPlayer(P1);
        g.addPlayer(P2);
        g.start();
        g.select("C", 200);
    }

    @Test(expected = AnswerQuestionCurrentlySelectedException.class)
    public void selectAnswerWhenOneAlreadySelected() {
        g.select("C", 400);
    }

    @Test
    public void readQuestionNobodyTriesToAnswer() {
        g.noAnswer();
    }

    @Test
    public void readQuestionSomoneTriesToAnswerAndDoes() {
        g.take(P1);
        g.answer(true);
        assertEquals(200, g.scoreFor(P1));
        assertEquals(0, g.scoreFor(P2));
        assertTrue(g.retrieve("C", 200).isUsed());
    }

    @Test
    public void readQuestionSometimeTriesToAnswerButIsIncorrect() {
        g.take(P2);
        g.answer(false);
        assertEquals(-200, g.scoreFor(P2));
        assertEquals(0, g.scoreFor(P1));
    }

    @Test
    public void readQuestionSomeoneTriesToAnswerDoesNotSomeoneElseAnswersCorrectly() {
        g.take(P2);
        g.answer(false);
        g.take(P1);
        g.answer(true);
        assertEquals(-200, g.scoreFor(P2));
        assertEquals(200, g.scoreFor(P1));
    }

    @Test
    public void readQuestionSomeoneTriesToAnswerDoesNotSomeoneElseAnswersIncorrectly() {
        g.take(P1);
        g.answer(false);
        g.take(P2);
        g.answer(false);
        assertEquals(-200, g.scoreFor(P1));
        assertEquals(-200, g.scoreFor(P2));
    }

    @Test(expected = NoCurrentPlayerException.class)
    public void takeAnswerWrongAnswerCorrectlyWithoutTakingScoresSafe() {
        g.take(P1);
        g.answer(false);
        assertEquals(-200, g.scoreFor(P1));
        g.answer(false);
    }

    @Test
    public void answerCorrectlyButNotSoonEnough() {
        g.setAnswerTime(-1);
        g.take(P1);
        g.answer(true);
        assertEquals(-200, g.scoreFor(P1));
    }

    @Test(expected = AlreadyTakeException.class)
    public void attemptToTakeWhenAlreadyTakenByAnother() {
        g.take(P1);
        g.take(P2);
    }

    @Test
    public void attemptToTakeWhenAlreadyTakenBySelf() {
        g.take(P1);
        g.take(P1);
    }
}
