package jeopardy.domain.gamestate;

import java.util.List;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Game;
import jeopardy.domain.GameState;
import jeopardy.domain.Player;
import jeopardy.exception.AnswerQuestionCurrentlySelectedException;
import jeopardy.exception.GameAlreadyStartedException;
import jeopardy.exception.NoCurrentPlayerException;

class QuestionSelected implements GameState {

    public Player addPlayer(Game game, String name) {
        throw new GameAlreadyStartedException();
    }

    public List<Player> end(Game game) {
        GameState state = GameStates.getState("end");
        game.setState(state);
        return state.end(game);
    }

    public Player answer(Game game, boolean answeredCorrectly) {
        throw new NoCurrentPlayerException();
    }

    public Player getCurrentPlayer(Game game) {
        throw new NoCurrentPlayerException();
    }

    public boolean isStarted(Game game) {
        return true;
    }

    public void noAnswer(Game game) {
        game.setState(GameStates.getState("noAnswer"));
        game.setCurrentPlayer(null);
        game.setCurrentAnswerStartTime(0);
        game.setCurrentAnswerQuestion(null);
    }

    public AnswerQuestion select(Game game, String category, int value) {
        throw new AnswerQuestionCurrentlySelectedException();
    }

    public Player start(Game game) {
        throw new GameAlreadyStartedException();
    }

    public AnswerQuestion take(Game game, String name) {
        game.setState(GameStates.getState("take"));
        game.setCurrentPlayer(game.getPlayerNamed(name));
        game.getOutputStream().printf("%s Took The Question First\n",
                game.getCurrentPlayer().getName());
        return game.getCurrentAnswerQuestion();
    }

}
