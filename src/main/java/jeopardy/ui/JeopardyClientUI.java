package jeopardy.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.SocketException;

import jeopardy.clientserver.ClientMessageSender;
import jeopardy.commands.JeopardyObjectResponse;
import jeopardy.domain.AnswerQuestion;

public class JeopardyClientUI {
    private ClientMessageSender messageSender;
    private String name;
    private InputStreamReader isr;
    private BufferedReader br;
    private PrintStream out = System.out;

    private void usage() {
        out.printf("Usage: JeopardyClientInterface player_name machine port\n");
        System.exit(0);
    }

    public JeopardyClientUI(String[] args, InputStream inStream) throws Exception {
        if (args.length != 3) {
            usage();
        }
        name = args[0];
        String machine = args[1];
        int port = Integer.parseInt(args[2]);

        messageSender = new ClientMessageSender(machine, port);
        messageSender.join(name);
        isr = new InputStreamReader(inStream);
        br = new BufferedReader(isr);
    }

    public static void main(String[] args) {
        try {
            new JeopardyClientUI(args, System.in).processInput();
        } catch (SocketException e) {
            // ignore, shutting down
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrintStream(PrintStream stream) {
        this.out = stream;
    }

    private String getLine() throws IOException {
        return br.readLine();
    }

    private void line() {
        out.printf("=================================\n");
    }

    private void prompt() {
        out.printf("Just press <ENTER> to take question or type quit to end the game\n");
        out.printf("%s>>>> ", name);
    }

    private void processInput() throws Exception {
        while (true) {
            line();
            prompt();
            String line = getLine();

            if (isTake(line)) {
                take();
            }
            if (isQuit(line)) {
                break;
            }
        }
        messageSender.terminateClient();
    }

    private boolean isQuit(String line) {
        return line.matches("^\\s*[Qq][Uu][Ii][Tt].*$");
    }

    private boolean isTake(String line) {
        return line.matches("^\\s*$");
    }

    private void display(AnswerQuestion aq) {
        if (aq != null) {
            out.printf("For %d, the answer is: %s\n", aq.getValue(), aq.getAnswer());
        }
    }

    private void take() throws Exception {
        JeopardyObjectResponse<AnswerQuestion> jr = messageSender.take(name);
        line();
        if(!jr.isSuccessful()) {
            out.printf("You didn't get this one: %s\n", jr.getThrown());
        }
        display(jr.getValue());
        line();
    }
}
