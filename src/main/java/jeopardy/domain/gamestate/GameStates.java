package jeopardy.domain.gamestate;

import java.util.HashMap;
import java.util.Map;

import jeopardy.domain.GameState;

public class GameStates {
    private static Map<String, GameState> GAME_STATES = new HashMap<String, GameState>();

    static {
        GAME_STATES.put("initial", new AcceptingPlayers());

        GameState awaiting = new AwaitingQuestionSelection();
        GAME_STATES.put("start", new AwaitingQuestionSelection());

        GAME_STATES.put("noAnswer", awaiting);
        GAME_STATES.put("select", new QuestionSelected());
        GAME_STATES.put("end", new GameOver());
        
        GAME_STATES.put("take", new QuestionTaken());
    }

    public static GameState getState(String name) {
        return GAME_STATES.get(name);
    }

}
