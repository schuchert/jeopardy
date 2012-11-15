package jeopardy.exception;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Player;

public class AlreadyTakeException extends RuntimeException {
    private static final long serialVersionUID = -1654949517082605195L;

    private Player player;
    private AnswerQuestion aq;

    public AlreadyTakeException(Player player, AnswerQuestion aq) {
        this.player = player;
        this.aq = aq;
    }

    public Player getPlayer() {
        return player;
    }

    public AnswerQuestion getAnswerQuestion() {
        return aq;
    }
}
