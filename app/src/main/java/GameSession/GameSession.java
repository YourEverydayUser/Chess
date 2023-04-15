package GameSession;

import Observer.BoardObservable;
import Observer.BoardObserver;
import Pieces.Board;
import Pieces.Field;
import Pieces.Figures.Figure;
import Pieces.Figures.Pawn;
import Pieces.Figures.Queen;

import java.util.ArrayList;
import java.util.List;

/**
 * The class GameSession initializes the gameBoard and keeps track of the turnCount.
 */
public class GameSession implements BoardObservable {

    private Board gameBoard;
    private int turnCount;
    private final ArrayList<Player> players;
    private int currentTurn;
    private final List<BoardObserver> observers;

    private GameSession() {
        gameBoard = new Board();
        turnCount = 0;
        players = new ArrayList<>();
        players.add(Player.initializeWhitePlayer());
        players.add(Player.initializeBlackPlayer());
        currentTurn = 0;
        observers = new ArrayList<>();

        //adds the players figures to the gameBoard
        for(Player players : players) {
            for(Figure figure : players.getFigures()) {
                Field currentField = figure.getCurrentPosition();
                Field[][] keys = gameBoard.getKeys();
                gameBoard.getGameBoard().put(keys[currentField.getxCoordinate()][currentField.getyCoordinate()], figure);
            }
        }
    }

    public Board accessBoard() {
        return gameBoard;
    }

    public static GameSession getInstance() {
        return new GameSession();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int playTurn(Field fromField, Field toField) {
        Figure figure = gameBoard.getGameBoard().get(fromField);
        if (figure == null) {
            return 1;
        }
        if (!figure.getColor().equals(players.get(currentTurn).getColor())) {
            return 2;
        }
        ArrayList<Field> validMoves = gameBoard.getValidMoves(figure);
        if (!validMoves.contains(toField)) {
            return 3;
        }

        Figure capturedFigure = gameBoard.getGameBoard().get(toField);
        if (capturedFigure != null) {
            getPlayers().get(currentTurn).getDeadPool().add(capturedFigure);
            getPlayers().get(currentTurn).getFigures().remove(capturedFigure);
        }

        gameBoard.getGameBoard().put(toField, figure);
        gameBoard.getGameBoard().put(fromField, null);

        // Check if a pawn has reached the opposite end of the board
        if (figure instanceof Pawn && (toField.getyCoordinate() == 0 || toField.getyCoordinate() == 7)) {
            gameBoard.getGameBoard().put(toField, new Queen(toField, true, figure.getColor()));
        }

        currentTurn = (currentTurn + 1) % 2;
        turnCount++;
        return 4;
    }

    @Override
    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(BoardObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Board board) {
        for (BoardObserver observer : observers) {
            observer.update(this.accessBoard());
        }
    }
}
