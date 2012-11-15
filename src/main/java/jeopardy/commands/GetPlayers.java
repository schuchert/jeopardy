package jeopardy.commands;

import java.util.List;

import jeopardy.clientserver.JeopardyService;
import jeopardy.domain.Player;

public class GetPlayers implements JeopardyCommand {
    private static final long serialVersionUID = 1L;

    public JeopardyResponse execute(JeopardyService service) {
        List<Player> players = service.getPlayers();
        return new JeopardyObjectResponse<List<Player>>(players);
    }

    public JeopardyResponse createInvalidResponse(Exception e) {
        return new JeopardyObjectResponse<List<Player>>(e);
    }

}
