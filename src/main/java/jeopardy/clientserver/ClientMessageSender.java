package jeopardy.clientserver;

import java.util.List;

import jeopardy.commands.GetBoard;
import jeopardy.commands.GetPlayers;
import jeopardy.commands.JeopardyObjectResponse;
import jeopardy.commands.JeopardyResponse;
import jeopardy.commands.Join;
import jeopardy.commands.Take;
import jeopardy.commands.TerminateClient;
import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Board;
import jeopardy.domain.Player;

public class ClientMessageSender {
    private JeopardyObjectSocket jos;

    public ClientMessageSender(String machine, int port) {
        this.jos = new JeopardyObjectSocket(machine, port);
    }

    public JeopardyResponse join(String name) throws Exception {
        return jos.sendAndGetResponse(new Join(name));
    }

    @SuppressWarnings("unchecked")
    public JeopardyObjectResponse<AnswerQuestion> take(String name) throws Exception {
        return (JeopardyObjectResponse<AnswerQuestion>) jos.sendAndGetResponse(new Take(name));
    }

    public void terminateClient() throws Exception {
        try {
            jos.sendAndGetResponse(new TerminateClient());
            jos.shutdown();
        } catch (Exception e) {
            // this will pretty much always fail, so ignore it
        }
    }

    @SuppressWarnings("unchecked")
    public JeopardyObjectResponse<Board> getBoard() throws Exception {
        return (JeopardyObjectResponse<Board>) jos.sendAndGetResponse(new GetBoard());
    }

    @SuppressWarnings("unchecked")
    public JeopardyObjectResponse<List<Player>> getPlayers() throws Exception {
        return (JeopardyObjectResponse<List<Player>>) jos.sendAndGetResponse(new GetPlayers());
    }
}
