package jeopardy.commands;

public class JeopardyStatusOnlyResponse extends JeopardyResponse {
    private static final long serialVersionUID = 1;

    public JeopardyStatusOnlyResponse() {
    }

    public JeopardyStatusOnlyResponse(boolean status) {
        super(status);
    }

    public JeopardyStatusOnlyResponse(boolean status, Exception thrown) {
        super(status, thrown);
    }
}
