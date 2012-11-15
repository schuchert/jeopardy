package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;
import jeopardy.domain.Board;

public class GetBoard implements JeopardyCommand {
    private static final long serialVersionUID = 1L;

    public JeopardyResponse execute(JeopardyService service) {
        return new JeopardyObjectResponse<Board>(service.getBoard());
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyObjectResponse<Board>(e);
    }

}
