package jeopardy.clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import jeopardy.commands.GetBoard;
import jeopardy.commands.GetPlayers;
import jeopardy.commands.JeopardyObjectResponse;
import jeopardy.commands.JeopardyResponse;
import jeopardy.commands.Join;
import jeopardy.commands.Nobody;
import jeopardy.commands.Select;
import jeopardy.commands.Start;
import jeopardy.commands.Take;
import jeopardy.commands.TerminateClient;
import jeopardy.commands.WasAnsweredCorrectly;
import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Board;
import jeopardy.domain.Player;

public class ServerMessageSender {
    private JeopardyObjectSocket jos;
    private JeopardyServer server;

    public ServerMessageSender(JeopardyServer js, String machine, int port) {
        this.server = js;
        this.jos = new JeopardyObjectSocket(machine, port);
    }

    @SuppressWarnings("unchecked")
    public JeopardyObjectResponse<Player> questionAnswered(boolean correct) throws Exception {
        return (JeopardyObjectResponse<Player>) jos.sendAndGetResponse(new WasAnsweredCorrectly(
                correct));
    }

    public ObjectInputStream getIn(Socket clientConnection) throws IOException {
        return new ObjectInputStream(clientConnection.getInputStream());
    }

    public ObjectOutputStream getOut(Socket clientConnection) throws IOException {
        return new ObjectOutputStream(clientConnection.getOutputStream());
    }

    public JeopardyResponse join(String name) throws Exception {
        return jos.sendAndGetResponse(new Join(name));
    }

    @SuppressWarnings("unchecked")
    public JeopardyObjectResponse<AnswerQuestion> select(String category, int value)
            throws Exception {
        Select s = new Select(category, value);
        JeopardyResponse jr = jos.sendAndGetResponse(s);
        return (JeopardyObjectResponse<AnswerQuestion>) jr;
    }

    public void shutdownServer() throws Exception {
        try {
            server.stop();
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public JeopardyObjectResponse<Player> start() throws Exception {
        Start s = new Start();
        return (JeopardyObjectResponse<Player>) jos.sendAndGetResponse(s);
    }

    public JeopardyResponse take(String name) throws Exception {
        return jos.sendAndGetResponse(new Take(name));
    }

    public void terminateClient() throws Exception {
        jos.sendAndGetResponse(new TerminateClient());
        jos.shutdown();
    }

    public JeopardyResponse nobody() throws Exception {
        return jos.sendAndGetResponse(new Nobody());
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
