package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;
import jeopardy.domain.Player;

public class WasAnsweredCorrectly implements JeopardyCommand {
    private static final long serialVersionUID = 1L;
    private boolean correct;

    public WasAnsweredCorrectly(boolean correctAnswer) {
        correct = correctAnswer;
    }

    public JeopardyResponse execute(JeopardyService service) {
        try {
            Player p = null;
            if (correct) {
                p = service.answeredCorrectly();
            } else {
                p = service.answeredIncorrectly();
            }
            return new JeopardyObjectResponse<Player>(p);
        } catch (Exception e) {
            return createInvalidResponse(e);
        }
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyObjectResponse<Player>(e);
    }

}
