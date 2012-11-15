package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;

public class TerminateClient implements JeopardyCommand {
    private static final long serialVersionUID = 1L;

    public JeopardyResponse execute(JeopardyService service) {
        return createInvalidResponse(null);
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyResponse() {
            private static final long serialVersionUID = 1L;

            public boolean isTerminate() {
                return true;
            }
        };
    }

}
