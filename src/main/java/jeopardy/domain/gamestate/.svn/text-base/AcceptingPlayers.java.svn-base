package jeopardy.domain.gamestate;

import java.util.Iterator;
import java.util.List;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Game;
import jeopardy.domain.GameState;
import jeopardy.domain.Player;
import jeopardy.exception.GameNotStartedException;
import jeopardy.exception.PlayerNameAlreadyUsedException;

public class AcceptingPlayers implements GameState {
    public Player addPlayer(Game game, String name) {
        Iterator<Player> iter = game.getPlayers().iterator();
        while (iter.hasNext()) {
            if (iter.next().getName().equals(name)) {
                throw new PlayerNameAlreadyUsedException();
            }
        }

        Player p = new Player();
        p.setName(name);
        game.getPlayers().add(p);
        return p;
    }

    public List<Player> end(Game game) {
        throw new GameNotStartedException();
    }

    public Player answer(Game game, boolean answeredCorrectly) {
        throw new GameNotStartedException();
    }

    public Player getCurrentPlayer(Game game) {
        throw new GameNotStartedException();
    }

    public boolean isStarted(Game game) {
        return false;
    }

    public void noAnswer(Game game) {
        throw new GameNotStartedException();
    }

    public AnswerQuestion select(Game game, String category, int value) {
        throw new GameNotStartedException();
    }

    public Player start(Game game) {
        game.setState(GameStates.getState("start"));
        List<Player> players = game.getPlayers();
        return players.get((int) (Math.random() * players.size()));
    }

    public AnswerQuestion take(Game game, String name) {
        throw new GameNotStartedException();
    }
}
