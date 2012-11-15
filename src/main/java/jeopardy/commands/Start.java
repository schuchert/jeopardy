package jeopardy.commands;

import jeopardy.clientserver.JeopardyService;
import jeopardy.domain.Player;

public class Start implements JeopardyCommand {
    private static final long serialVersionUID = 8937648361501385188L;

    public JeopardyObjectResponse<Player> execute(JeopardyService service) {
        try {
            Player p = service.start();
            return new JeopardyObjectResponse<Player>(p);
        } catch (Exception e) {
            return createInvalidResponse(e);
        }
    }

    public JeopardyObjectResponse<Player> createInvalidResponse(Exception e) {
        return new JeopardyObjectResponse<Player>(e);
    }

}
