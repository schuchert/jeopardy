package jeopardy.clientserver;

import java.io.IOException;
import java.net.Socket;

import jeopardy.commands.JeopardyCommand;
import jeopardy.commands.JeopardyResponse;

public class ClientConnection implements Runnable {
    private JeopardyObjectSocket jos;
    private JeopardyService service;

    public ClientConnection(Socket socket, JeopardyService service) throws IOException {
        this.jos = new JeopardyObjectSocket(socket);
        this.service = service;
    }

    private JeopardyResponse executeCommand(JeopardyCommand jc) {
        JeopardyResponse resp = null;
        try {
            resp = jc.execute(service);
        } catch (Exception e) {
            resp = jc.createInvalidResponse(e);
        }
        return resp;
    }

    private void shutdownClient() {
        // give the client a little time and then close the socket;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // don't care, ignore
        }
        jos.shutdown();
    }

    public void run() {
        try {
            while (true) {
                JeopardyCommand jc = jos.getCommand();
                JeopardyResponse resp = executeCommand(jc);
                jos.send(resp);

                if (resp.isTerminate()) {
                    break;
                }
            }
        } catch (Exception e) {
            // ignore, we'll just terminate the client
        }

        shutdownClient();
    }
}
