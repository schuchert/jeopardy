package jeopardy.ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jeopardy.clientserver.JeopardyServer;
import jeopardy.clientserver.JeopardyService;
import jeopardy.clientserver.ServerMessageSender;
import jeopardy.commands.JeopardyObjectResponse;
import jeopardy.commands.JeopardyResponse;
import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Board;
import jeopardy.domain.Game;
import jeopardy.domain.Player;
import jeopardy.io.BoardReader;

public class JeopardyServerUI {
    private ServerMessageSender messageSender;
    private InputStreamReader isr;
    private BufferedReader br;
    private PrintStream out = System.out;

    public JeopardyServerUI(String[] args) throws Exception {
        if (args.length < 2) {
            usage();
        }
        String filename = null;
        int port = 8080;
        int aqTime = 20 * 1000;

        switch (args.length) {
        case 3:
            aqTime = Integer.parseInt(args[2]) * 1000;
        case 2:
            port = Integer.parseInt(args[1]);
        case 1:
            filename = args[0];
        }

        JeopardyService service = new JeopardyService();
        Game g = new Game();
        FileReader fr = new FileReader(filename);
        g.setBoard(BoardReader.createBoard(fr));
        fr.close();
        service.setGame(g);
        service.setAnswerQuestionTime(aqTime);

        JeopardyServer js = new JeopardyServer();
        js.setServerSocketTimeout(20);
        js.setService(service);
        js.setPort(port);
        js.start();

        messageSender = new ServerMessageSender(js, "localhost", port);

        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);

        initMappings();
    }

    public static void main(String[] args) {
        try {
            JeopardyServerUI jsi = new JeopardyServerUI(args);
            jsi.processInput();
            // } catch (SocketException e) {
            // // ignore
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrintStream(PrintStream stream) {
        this.out = stream;
    }

    private void line() {
        out.printf("=====================================\n");
    }

    private void displayPlayerScores() throws Exception {
        Iterator<Player> iter = messageSender.getPlayers().getValue().iterator();
        line();
        while (iter.hasNext()) {
            out.printf("\t%s\n", iter.next());
        }
        line();
    }

    private void displayCategory(String prefix, String category,
            List<AnswerQuestion> answerQuestions) {
        out.printf("%s%20s", prefix, category);
        Iterator<AnswerQuestion> aq = answerQuestions.iterator();
        while (aq.hasNext()) {
            AnswerQuestion current = aq.next();
            out.printf("%5s", (current.isUsed() ? "" : "" + current.getValue()));
        }
        out.printf("\n");
    }

    private void displayCategory(int index, Board b, String categoryName) {
        displayCategory(String.format("%2d: ", index), categoryName, b.retrieve(categoryName));
    }

    public List<String> displayBoard() throws Exception {
        line();
        Board b = messageSender.getBoard().getValue();
        List<String> categories = new ArrayList<String>(b.getCategories());

        Iterator<String> iter = categories.iterator();
        int index = 0;
        while (iter.hasNext()) {
            displayCategory(++index, b, iter.next());
        }
        line();

        return categories;
    }

    private void prompt() throws Exception {
        out.printf("(b)egin  (d)isplay board (s)elect (c)orrect (n)obody (i)ncorrect (q)uit\n");
        out.printf("====> ");
    }

    private String getLine() throws Exception {
        return br.readLine();
    }

    private int getInteger() throws Exception {
        try {
            return Integer.parseInt(getLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    private void processInput() throws Exception {
        out.printf("Game Now Waiting For Players\n");
        while (true) {
            displayPlayerScores();
            prompt();
            String line = getLine();
            Iterator<RegularExpressionToMethod> iter = inputMappings.iterator();
            while (iter.hasNext()) {
                RegularExpressionToMethod rem = iter.next();
                if (rem.matches(line)) {
                    rem.execute();
                    break;
                }
            }
        }
    }

    public void incorrect() throws Exception {
        JeopardyObjectResponse<Player> resp = messageSender.questionAnswered(false);
        if (!resp.isSuccessful()) {
            out.printf("Problem telling system player got it wrong: %s\n", resp.getThrown());
        }
    }

    public void begin() throws Exception {
        Player firstPlayer = messageSender.start().getValue();
        line();
        out.printf("Starting player: %s\n", firstPlayer);
        line();
    }

    public void quitGame() throws Exception {
        messageSender.shutdownServer();
        Thread.sleep(1000);
        System.exit(0);
    }

    public void nobody() throws Exception {
        JeopardyResponse resp = messageSender.nobody();
        if (!resp.isSuccessful()) {
            out.printf("Problem setting nobody responsible: %s\n", resp.getThrown());
        }
    }

    public void correct() throws Exception {
        JeopardyObjectResponse<Player> resp = messageSender.questionAnswered(true);
        if (!resp.isSuccessful()) {
            out.printf("Problem saying answer correct: " + resp.getThrown());
        }
    }

    public void select() throws Exception {
        List<String> categories = displayBoard();
        out.printf("Select Category Number==> ");
        int categoryNumber = getInteger();
        if (categoryNumber < 1 || categoryNumber > categories.size()) {
            System.err.printf("Invalid Category Number: %d\n", categoryNumber);
            return;
        }

        String categoryName = categories.get(categoryNumber - 1);

        displayCategory(">>>", categoryName, messageSender.getBoard().getValue().retrieve(
                categoryName));

        out.printf("Select Dollar Amount==> ");
        int dollarAmount = getInteger();
        if (dollarAmount < 0) {
            System.err.printf("Invalid dollar amount entered\n");
            return;
        }

        JeopardyObjectResponse<AnswerQuestion> aqr = messageSender.select(categories
                .get(categoryNumber - 1), dollarAmount);

        if (aqr.isSuccessful()) {
            AnswerQuestion current = aqr.getValue();
            out.printf("---------------------------------------------------------\n");
            out.printf("For %d here is your answer:\n\t%s\n", current.getValue(), current
                    .getAnswer());
            out.printf("---------------------------------------------------------\n");
        } else {
            out.printf("Problem selecting answer question: %s\n", aqr.getThrown());
        }
    }

    private void usage() {
        out.printf("JeopartyServer boardfilename port time_seconds\n");
        System.exit(0);
    }

    private List<RegularExpressionToMethod> inputMappings = new ArrayList<RegularExpressionToMethod>();

    private RegularExpressionToMethod buildMapping(String letters, String methodName)
            throws Exception {
        String pattern = String.format("\\s*[%s].*", letters);
        Method method = getClass().getDeclaredMethod(methodName, (Class[]) null);
        return new RegularExpressionToMethod(pattern, method, this);
    }

    private void initMappings() throws Exception {
        inputMappings.add(buildMapping("bB", "begin"));
        inputMappings.add(buildMapping("sS", "select"));
        inputMappings.add(buildMapping("cC", "correct"));
        inputMappings.add(buildMapping("nN", "nobody"));
        inputMappings.add(buildMapping("iI", "incorrect"));
        inputMappings.add(buildMapping("qQ", "quitGame"));
        inputMappings.add(buildMapping("dD", "displayBoard"));
    }

    public static class RegularExpressionToMethod {
        private String pattern;
        private Method method;
        private Object target;

        public RegularExpressionToMethod(String pattern, Method method, Object target) {
            this.pattern = pattern;
            this.method = method;
            this.target = target;
        }

        public void execute() throws Exception {
            method.invoke(target, (Object[]) null);
        }

        public boolean matches(String inputLine) {
            return inputLine.matches(pattern);
        }
    }
}
