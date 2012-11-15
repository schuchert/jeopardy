package jeopardy.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Board;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    public static Board createBoard() {
        Board b = new Board();
        b.add("C2", 500, "I am x", "what is foo?");
        b.add("C2", 100, "I am x", "what is foo?");
        b.add("C2", 200, "I am x", "what is foo?");
        b.add("Category", 500, "I am x", "what is foo?");
        b.add("Category", 100, "I am x", "what is foo?");
        b.add("Category", 200, "I am x", "what is foo?");
        b.add("Category", 300, "I am x", "what is foo?");
        return b;
    }

    @Before
    public void createTestBoard() {
        board = createBoard();
    }

    @Test
    public void retrieveSuccessfulCategoryValue() {
        AnswerQuestion aq = board.retrieve("Category", 200);
        assertNotNull(aq);
    }

    @Test
    public void retrieveAllAnswersQuestionsInCategorySortedByValue() {
        List<AnswerQuestion> aqs = board.retrieve("Category");
        assertEquals(4, aqs.size());
        assertEquals(100, aqs.get(0).getValue());
        assertEquals(200, aqs.get(1).getValue());
        assertEquals(300, aqs.get(2).getValue());
        assertEquals(500, aqs.get(3).getValue());
    }

    @Test
    public void retrieveAllCategories() {
        Set<String> categories = board.getCategories();
        assertEquals(2, categories.size());
    }
}
