package jeopardy.clientserver;

import java.util.List;

import jeopardy.domain.AnswerQuestion;
import jeopardy.domain.Board;
import jeopardy.domain.Game;
import jeopardy.domain.Player;

public class JeopardyService {
    private Game game;

    public void create() {
    }

    public Player join(String name) {
        return game.addPlayer(name);
    }

    public Player start() {
        return game.start();
    }

    public int getPlayerCount() {
        return game.getPlayerCount();
    }

    public void setGame(Game g) {
        game = g;
    }

    public AnswerQuestion select(String category, int value) {
        return game.select(category, value);
    }

    public AnswerQuestion take(String name) {
        return game.take(name);
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public Player answeredCorrectly() {
        return game.answer(true);
    }

    public int scoreFor(String name) {
        return game.scoreFor(name);
    }

    public Player answeredIncorrectly() {
        return game.answer(false);
    }

    public void setAnswerQuestionTime(int milliseconds) {
        game.setAnswerTime(milliseconds);
    }

    public void nobodyAnswered() {
        game.noAnswer();
    }

    public List<Player> gameOver() {
        return game.end();
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }
}
