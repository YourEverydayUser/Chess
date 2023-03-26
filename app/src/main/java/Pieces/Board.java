package Pieces;


import static Pieces.BoardDimension.XDIMENSION;
import static Pieces.BoardDimension.YDIMENSION;

public class Board {

    private Field[][] gameBoard;

    private enum state {
        FREE("o"),
        OCCUPIED("x");

        state(String s) {

        }
    }

    public Board() {
        int xDimension = XDIMENSION.getValue();
        int yDimension = YDIMENSION.getValue();
        gameBoard = new Field[xDimension][yDimension];

        for(int x = 0; x <= xDimension; x++) {
            for(int y = 0; y <= yDimension; y++) {
                gameBoard[x][y] = new Field(x, y);
            }
        }
    }

    public Field[][] getGameBoard() {
        return gameBoard;
    }

}
