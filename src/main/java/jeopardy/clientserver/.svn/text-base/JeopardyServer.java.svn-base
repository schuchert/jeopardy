package jeopardy.clientserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import jeopardy.util.CloseUtil;

public class JeopardyServer implements Runnable {
    private int port;
    private ServerSocket socket;
    private volatile boolean continueAccepting = true;
    private JeopardyService service;
    private int serverSocketTimeout = 1000;
    private PrintStream out = System.out;

    public void setServerSocketTimeout(int milliseconds) {
        serverSocketTimeout = milliseconds;
        if (socket != null) {
            try {
                socket.setSoTimeout(milliseconds);
            } catch (SocketException e) {
                // ignore since the socket is probably not accepting any more
            }
        }
    }

    public void setPrintStream(PrintStream out) {
        this.out = out;
    }

    public void setService(JeopardyService service) {
        this.service = service;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public void start() throws IOException {
        socket = new ServerSocket(port);
        socket.setSoTimeout(serverSocketTimeout);
        new Thread(this).start();
    }

    public void stop() {
        continueAccepting = false;
    }

    public void run() {
        while (continueAccepting) {
            try {
                Socket clientSocket = socket.accept();
                new Thread(new ClientConnection(clientSocket, service)).start();
            } catch (SocketTimeoutException e) {
                // Timeout? If continue accepting is is true, then we'll just
                // wait some more
            } catch (IOException e) {
                out.printf("Shutting down Jeopardy Server");
                return;
            }
        }
        CloseUtil.close(socket);
    }
}
