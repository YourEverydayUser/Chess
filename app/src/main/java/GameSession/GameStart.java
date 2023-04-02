package GameSession;

import Pieces.Board;
import Pieces.BoardDimension;
import Pieces.Field;
import Pieces.Figures.Figure;
import Pieces.Figures.King;

import java.util.HashMap;

public class GameStart {

    private Board gameBoard;

    public GameStart() {
        gameBoard = new Board();
        gameBoard.initializeBlackPlayer();
        gameBoard.initializeWhitePlayer();
    }

    public Board getGameBoard() {
        return gameBoard;
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
