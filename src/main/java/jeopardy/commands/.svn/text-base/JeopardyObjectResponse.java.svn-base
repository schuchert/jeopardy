package jeopardy.commands;

public class JeopardyObjectResponse<T> extends JeopardyResponse {
    private static final long serialVersionUID = 3983223120990769134L;

    private T value;

    public JeopardyObjectResponse(T value) {
        super(true);
        this.value = value;
    }

    public JeopardyObjectResponse(Exception thrown) {
        super(false, thrown);
    }

    public JeopardyObjectResponse(T value, Exception thrown) {
        super(false, thrown);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
