package jeopardy.exception;

public class NoSuchPlayerException extends RuntimeException {
    private static final long serialVersionUID = 2665638923709443707L;
    
    private final String name;

    public NoSuchPlayerException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
