package GameSession;

import Pieces.Board;
import Pieces.BoardDimension;
import Pieces.Field;
import Pieces.Figures.Figure;

import java.util.HashMap;

public class GameSession {

    private Board gameBoard;
    private int turnCount;

    private GameSession() {
        gameBoard = new Board();
        gameBoard.initializeBlackPlayer();
        gameBoard.initializeWhitePlayer();
        turnCount = 0;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public static GameSession getInstance() {
        return new GameSession();
    }

    public void printGameBoard(Board board) {
        HashMap<Field, Figure> gameBoard = board.getGameBoard();
        Field[][] keys = board.getKeys();
        int counter = 0;

        for(int x = 0; x < BoardDimension.XDIMENSION.getValue(); x++) {
            for(int y = 0; y < BoardDimension.YDIMENSION.getValue(); y++) {
                if(gameBoard.get(keys[x][y]) != null) {
                    System.out.print("   K   ");
                }
                else {
                    System.out.print("   .   ");
                }
                counter++;
                if(counter==8) {
                    System.out.println("\n");
                    counter=0;
                }
            }
        }
    }
}
