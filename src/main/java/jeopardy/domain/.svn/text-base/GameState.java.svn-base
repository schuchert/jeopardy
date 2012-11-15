package jeopardy.domain;

import java.util.List;

public interface GameState {

    public abstract Player answer(Game game, boolean answeredCorrectly);

    public abstract AnswerQuestion select(Game game, String category, int value);

    public abstract AnswerQuestion take(Game game, String name);

    public abstract Player addPlayer(Game game, String name);

    public abstract Player start(Game game);

    public abstract boolean isStarted(Game game);

    public abstract Player getCurrentPlayer(Game game);

    public abstract void noAnswer(Game game);

    public abstract List<Player> end(Game game);

}