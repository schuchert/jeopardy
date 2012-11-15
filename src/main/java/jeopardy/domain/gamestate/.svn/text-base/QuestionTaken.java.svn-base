package jeopardy.domain.gamestate;

import java.util.List;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Game;
import jeopardy.domain.GameState;
import jeopardy.domain.Player;
import jeopardy.exception.AlreadyTakeException;
import jeopardy.exception.AnswerQuestionCurrentlySelectedException;
import jeopardy.exception.GameAlreadyStartedException;

public class QuestionTaken implements GameState {

    public Player addPlayer(Game game, String name) {
        throw new GameAlreadyStartedException();
    }

    public List<Player> end(Game game) {
        GameState state = GameStates.getState("end");
        game.setState(state);
        return state.end(game);
    }

    private boolean answeredInTime(Game game) {
        long endTime = System.currentTimeMillis();
        long timeToAnswer = endTime - game.getCurrentAnswerStartTime();
        return timeToAnswer <= game.getMillisecondsToAnswer();
    }

    public Player answer(Game game, boolean answeredCorrectly) {
        boolean inTime = answeredInTime(game);
        Player currentPlayer = game.getCurrentPlayer();
        AnswerQuestion currentAq = game.getCurrentAnswerQuestion();

        if (answeredCorrectly && inTime) {
            currentPlayer.receive(currentAq.getValue());
        } else {
            currentPlayer.pay(currentAq.getValue());
        }

        game.setCurrentPlayer(null);

        if (answeredCorrectly) {
            game.setCurrentAnswerQuestion(null);
            game.setState(GameStates.getState("start"));
        } else {
            game.setState(GameStates.getState("select"));
        }
        return currentPlayer;
    }

    public Player getCurrentPlayer(Game game) {
        return game.getCurrentPlayerUnchecked();
    }

    public boolean isStarted(Game game) {
        return true;
    }

    public void noAnswer(Game game) {
        game.setState(GameStates.getState("noAnswer"));
    }

    public AnswerQuestion select(Game game, String category, int value) {
        throw new AnswerQuestionCurrentlySelectedException();
    }

    public Player start(Game game) {
        throw new GameAlreadyStartedException();
    }

    public AnswerQuestion take(Game game, String name) {
        if (game.getCurrentPlayer().getName().equals(name)) {
            return game.getCurrentAnswerQuestion();
        }
        throw new AlreadyTakeException(game.getCurrentPlayer(), game.getCurrentAnswerQuestion());
    }

}
