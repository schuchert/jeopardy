package jeopardy.domain.gamestate;

import java.util.List;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Game;
import jeopardy.domain.GameState;
import jeopardy.domain.Player;
import jeopardy.exception.GameOverException;

public class GameOver implements GameState {

    public Player addPlayer(Game game, String name) {
        throw new GameOverException();
    }

    public List<Player> end(Game game) {
        return game.getPlayers();
    }

    public Player answer(Game game, boolean answeredCorrectly) {
        throw new GameOverException();
    }

    public Player getCurrentPlayer(Game game) {
        throw new GameOverException();
    }

    public boolean isStarted(Game game) {
        return false;
    }

    public void noAnswer(Game game) {
        throw new GameOverException();
    }

    public AnswerQuestion select(Game game, String category, int value) {
        throw new GameOverException();
    }

    public Player start(Game game) {
        throw new GameOverException();
    }

    public AnswerQuestion take(Game game, String name) {
        throw new GameOverException();
    }

}
