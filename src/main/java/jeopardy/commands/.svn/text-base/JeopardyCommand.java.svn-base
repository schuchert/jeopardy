package jeopardy.commands;

import java.io.Serializable;

import jeopardy.clientserver.JeopardyService;

public interface JeopardyCommand extends Serializable {
    JeopardyResponse execute(JeopardyService service);

    JeopardyResponse createInvalidResponse(Exception e);
}
