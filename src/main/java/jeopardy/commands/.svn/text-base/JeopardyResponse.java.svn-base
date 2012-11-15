package jeopardy.commands;

import java.io.Serializable;

public abstract class JeopardyResponse implements Serializable {
    private boolean successful;
    private Exception thrown;

    public JeopardyResponse() {
        successful = true;
    }

    public JeopardyResponse(boolean successful) {
        this.successful = successful;
    }

    public JeopardyResponse(boolean successful, Exception thrown) {
        this.successful = successful;
        this.thrown = thrown;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isTerminate() {
        return false;
    }

    public Exception getThrown() {
        return thrown;
    }

    public void setThrown(Exception thrown) {
        this.thrown = thrown;
    }

}
