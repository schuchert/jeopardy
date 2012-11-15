package jeopardy.domain;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jeopardy.domain.gamestate.GameStates;
import jeopardy.exception.NoSuchPlayerException;

public class Game {
    public static final long DEFAULT_ANSWER_TIME = 60 * 1000;
    private Board board;
    private AnswerQuestion currentAq;
    private long currentAnswerStartTime;
    private long millisecondsToAnswer = DEFAULT_ANSWER_TIME;
    private List<Player> players = new ArrayList<Player>();
    private Player currentPlayer;
    private GameState state;
    private PrintStream stream = System.out;

    public Game() {
        state = GameStates.getState("initial");
    }

    public void setOutputStream(PrintStream stream) {
        this.stream = stream;
    }

    public PrintStream getOutputStream() {
        return stream;
    }

    public void setBoard(Board b) {
        board = b;
    }

    public Board getBoard() {
        return board;
    }

    public int scoreFor(String name) {
        return getPlayerNamed(name).getBalance();
    }

    public Player answer(boolean answeredCorrectly) {
        return state.answer(this, answeredCorrectly);
    }

    public AnswerQuestion select(String category, int value) {
        return state.select(this, category, value);
    }

    public Player getPlayerNamed(String name) {
        Iterator<Player> iter = players.iterator();
        while (iter.hasNext()) {
            Player current = iter.next();
            if (current.getName().equals(name)) {
                return current;
            }
        }

        throw new NoSuchPlayerException(name);
    }

    public synchronized AnswerQuestion take(String name) {
        return state.take(this, name);
    }

    public void setAnswerTime(int milliseconds) {
        millisecondsToAnswer = milliseconds;
    }

    public Player addPlayer(String name) {
        return state.addPlayer(this, name);
    }

    public Player start() {
        return state.start(this);
    }

    public int getPlayerCount() {
        return players.size();
    }

    public boolean isStarted() {
        return state.isStarted(this);
    }

    public Player getCurrentPlayer() {
        return state.getCurrentPlayer(this);
    }

    public Player getCurrentPlayerUnchecked() {
        return currentPlayer;
    }

    public void noAnswer() {
        state.noAnswer(this);
    }

    public List<Player> end() {
        return state.end(this);
    }

    public AnswerQuestion retrieve(String category, int value) {
        return board.retrieve(category, value);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setState(GameState next) {
        this.state = next;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public AnswerQuestion getCurrentAnswerQuestion() {
        return currentAq;
    }

    public void setCurrentAnswerStartTime(long time) {
        this.currentAnswerStartTime = time;
    }

    public void setCurrentAnswerQuestion(AnswerQuestion currentAq) {
        this.currentAq = currentAq;
    }

    public long getCurrentAnswerStartTime() {
        return currentAnswerStartTime;
    }

    public long getMillisecondsToAnswer() {
        return millisecondsToAnswer;
    }

}
