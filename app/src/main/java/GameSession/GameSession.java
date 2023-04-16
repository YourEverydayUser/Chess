package GameSession;

import Observer.BoardObservable;
import Observer.BoardObserver;
import Pieces.Board;
import Pieces.Field;
import Pieces.Figures.Color;
import Pieces.Figures.Figure;
import Pieces.Figures.Pawn;
import Pieces.Figures.Queen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class GameSession initializes the gameBoard and keeps track of the turnCount.
 * It has the method playTurn which updates the boardState. When the board gets
 * changed all the observers will be notified.
 * @Author Fabian Kuster
 */
public class GameSession implements BoardObservable {

    private Board gameBoard;
    private int turnCount;
    private ArrayList<Player> players;
    private int currentTurn;
    private List<BoardObserver> observers;

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

    public void resetGame() {
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
        if (isKingInCheck(getCurrentPlayersColor())) {
            return 4;
        }
        if (isCheckMate(players.get(currentTurn).getColor())) {
            return 5;
        }

        Figure capturedFigure = gameBoard.getGameBoard().get(toField);
        if (capturedFigure != null) {
            getPlayers().get(currentTurn).getDeadPool().add(capturedFigure);
            getPlayers().get(currentTurn).getFigures().remove(capturedFigure);
            capturedFigure.setOnGameBoard(false);
        }

        gameBoard.getGameBoard().put(toField, figure);
        gameBoard.getGameBoard().put(fromField, null);
        //update the figures current location
        gameBoard.getGameBoard().get(gameBoard.getKeys()[toField.getxCoordinate()][toField.getyCoordinate()]).setCurrentPosition(toField);

        // Check if a pawn has reached the opposite end of the board
        if (figure instanceof Pawn && (toField.getyCoordinate() == 0 || toField.getyCoordinate() == 7)) {
            gameBoard.getGameBoard().put(toField, new Queen(toField, true, figure.getColor()));
        }

        currentTurn = (currentTurn + 1) % 2;
        turnCount++;
        return 6;
    }

    public boolean isCheckMate(Color kingColor) {
        // First, check if the king is in check
        if (!isKingInCheck(kingColor)) {
            return false;
        }

        // Next, check if any move can get the king out of check
        Figure king = getKing(kingColor);
        for (Field destination : gameBoard.getValidMoves(king)) {
            if (!isKingInCheckAfterMove(king, destination)) {
                return false;
            }
        }
        // Finally, check if any piece can block the check
        if (players.get(currentTurn).getColor() == Color.WHITE) {
            for (Figure figure : players.get(0).getFigures()) {
                for (Field destination : gameBoard.getValidMoves(figure)) {
                    if (canBlockCheck(figure, destination)) {
                        return false;
                    }
                }
            }
        }
        // If none of the above conditions are met, the game is in checkmate
        return true;
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

    public int getTurnCount() {
        return turnCount;
    }

    public boolean isKingInCheck(Color kingColor) {
        Figure king = getKing(kingColor);
        return kingColor == Color.WHITE ? isFieldAttacked(king.getCurrentPosition(), Color.BLACK)
                : isFieldAttacked(king.getCurrentPosition(), Color.WHITE);
    }

    private boolean isFieldAttacked(Field field, Color color) {
        if (color == Color.WHITE) {
            //get the figures of the opponent
            for (Figure figure : players.get(1).getFigures()) {
                for (Field f : gameBoard.getValidMoves(figure)) {
                    if (f.equals(field)) {
                        return true;
                    }
                }
            }
        } else {
            for (Figure figure : players.get(0).getFigures()) {
                for (Field f : gameBoard.getValidMoves(figure)) {
                    if (f.equals(field)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isKingInCheckAfterMove(Figure figure, Field destination) {
        Field currentPosition = figure.getCurrentPosition();
        gameBoard.getGameBoard().put(currentPosition, null);
        gameBoard.getGameBoard().put(destination, figure);
        boolean isKingInCheck = isKingInCheck(figure.getColor());
        gameBoard.getGameBoard().put(destination, null);
        gameBoard.getGameBoard().put(currentPosition, figure);
        return isKingInCheck;
    }

    private boolean canBlockCheck(Figure figure, Field destination) {
        // Temporarily move the piece and see if the king is still in check
        Field currentPosition = figure.getCurrentPosition();
        gameBoard.getGameBoard().put(currentPosition, null);
        gameBoard.getGameBoard().put(destination, figure);
        boolean canBlockCheck = !isKingInCheck(figure.getColor());
        gameBoard.getGameBoard().put(destination, null);
        gameBoard.getGameBoard().put(currentPosition, figure);
        return canBlockCheck;
    }

    private Figure getKing(Color playerColor) {
        if (playerColor == Color.WHITE) {
            return players.get(0).getFigures().stream()
                    .filter(x -> x.getName().equals("King"))
                    .findFirst()
                    .orElse(null);
        } else {
            return players.get(1).getFigures().stream()
                    .filter(x -> x.getName().equals("King"))
                    .findFirst()
                    .orElse(null);
        }
    }

    private Color getCurrentPlayersColor() {
        return players.get(currentTurn).getColor();
    }
}
