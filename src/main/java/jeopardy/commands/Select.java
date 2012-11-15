package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;
import jeopardy.domain.AnswerQuestion;

public class Select implements JeopardyCommand {
    private static final long serialVersionUID = 1L;
    private String category;
    private int value;

    public Select() {
    }

    public Select(String category, int value) {
        this.category = category;
        this.value = value;
    }

    public JeopardyResponse execute(JeopardyService service) {
        try {
            AnswerQuestion aq = service.select(category, value);
            return new JeopardyObjectResponse<AnswerQuestion>(aq);
        } catch (Exception e) {
            return createInvalidResponse(e);
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyObjectResponse<AnswerQuestion>(e);
    }

}
