package jeopardy.clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import jeopardy.commands.JeopardyCommand;
import jeopardy.commands.JeopardyResponse;
import jeopardy.util.CloseUtil;

public class JeopardyObjectSocket {
    private Socket clientConnection;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private void initStreams(Socket socket) {
        this.clientConnection = socket;
        try {
            oos = new ObjectOutputStream(clientConnection.getOutputStream());
            ois = new ObjectInputStream(clientConnection.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JeopardyObjectSocket(Socket socket) {
        initStreams(socket);
    }

    public JeopardyObjectSocket(String machine, int port) {
        try {
            initStreams(new Socket(machine, port));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JeopardyCommand getCommand() throws IOException, ClassNotFoundException {
        return (JeopardyCommand) ois.readObject();
    }

    public void send(Object obj) throws IOException {
        oos.reset();
        oos.writeUnshared(obj);
        oos.flush();
    }

    public JeopardyResponse sendAndGetResponse(JeopardyCommand command) throws Exception {
        send(command);
        return (JeopardyResponse) ois.readObject();
    }

    public void shutdown() {
        CloseUtil.close(clientConnection);
    }
}
