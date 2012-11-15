package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;
import jeopardy.exception.PlayerNameAlreadyUsedException;

public class Join implements JeopardyCommand {
    private static final long serialVersionUID = 1L;

    private String name;

    public Join(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public JeopardyResponse execute(JeopardyService service) {
        try {
            service.join(getName());
            return new JeopardyStatusOnlyResponse();
        } catch (PlayerNameAlreadyUsedException e) {
            return createInvalidResponse(e);
        }
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyStatusOnlyResponse(false, e);
    }
}
