package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;

public class Nobody implements JeopardyCommand {
    private static final long serialVersionUID = 2960886326936543779L;

    public JeopardyResponse execute(JeopardyService service) {
        try {
            service.nobodyAnswered();
            return new JeopardyStatusOnlyResponse();
        } catch (Exception e) {
            return createInvalidResponse(e);
        }
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyStatusOnlyResponse(false, e);
    }

}
