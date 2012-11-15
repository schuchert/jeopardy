package jeopardy.domain.gamestate;

import java.util.List;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Game;
import jeopardy.domain.GameState;
import jeopardy.domain.Player;
import jeopardy.exception.AnswerQuestionAlreadyUsedException;
import jeopardy.exception.GameAlreadyStartedException;
import jeopardy.exception.NoAnswerQuestionSelectedException;
import jeopardy.exception.NoCurrentPlayerException;
import jeopardy.exception.NoSuchAnswerQuestionException;

public class AwaitingQuestionSelection implements GameState {

    public Player addPlayer(Game game, String name) {
        throw new GameAlreadyStartedException();
    }

    public List<Player> end(Game game) {
        game.setState(GameStates.getState("end"));
        return game.getPlayers();
    }

    public Player answer(Game game, boolean answeredCorrectly) {
        throw new NoAnswerQuestionSelectedException();
    }

    public Player getCurrentPlayer(Game game) {
        throw new NoCurrentPlayerException();
    }

    public boolean isStarted(Game game) {
        return true;
    }

    public void noAnswer(Game game) {
        throw new NoCurrentPlayerException();
    }

    public AnswerQuestion select(Game game, String category, int value) {
        AnswerQuestion currentAq = game.getBoard().retrieve(category, value);
        if (currentAq == null) {
            throw new NoSuchAnswerQuestionException(category, value);
        }
        if (currentAq.isUsed()) {
            throw new AnswerQuestionAlreadyUsedException();
        }

        game.setState(GameStates.getState("select"));
        
        currentAq.setUsed(true);
        game.setCurrentAnswerQuestion(currentAq);
        game.setCurrentAnswerStartTime(System.currentTimeMillis());
        return currentAq;
    }

    public Player start(Game game) {
        throw new GameAlreadyStartedException();
    }

    public AnswerQuestion take(Game game, String name) {
        throw new NoAnswerQuestionSelectedException();
    }
}
