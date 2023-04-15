package GameSession;

import Pieces.Board;
import Pieces.Field;
import Pieces.Figures.Figure;
import java.util.ArrayList;


public class GameSession {

    private Board gameBoard;
    private int turnCount;
    private ArrayList<Player> players;

    private GameSession() {
        gameBoard = new Board();
        turnCount = 0;
        players = new ArrayList<>();
        players.add(Player.initializeBlackPlayer());
        players.add(Player.initializeWhitePlayer());

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
}
