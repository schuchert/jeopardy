package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;
import jeopardy.domain.AnswerQuestion;
import jeopardy.exception.AlreadyTakeException;

public class Take implements JeopardyCommand {
    private static final long serialVersionUID = 127486610062028987L;
    private String name;

    public Take(String name) {
        this.name = name;
    }

    public JeopardyResponse execute(JeopardyService service) {
        try {
            AnswerQuestion aq = service.take(name);
            return new JeopardyObjectResponse<AnswerQuestion>(aq);
        } catch (AlreadyTakeException e) {
            return new JeopardyObjectResponse<AnswerQuestion>(e.getAnswerQuestion(), e);
        } catch (Exception e) {
            return createInvalidResponse(e);
        }
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyObjectResponse<AnswerQuestion>(e);
    }

}
