package Pieces;

public class Board {

    private Field[][] gameBoard;
    private final static int YDIMENSION = 8;
    private final static int XDIMENSION = 8;

    private enum state {
        FREE("o"),
        OCCUPIED("x");

        state(String s) {

        }
    }

    public Board() {
        gameBoard = new Field[XDIMENSION][YDIMENSION];

        for(int x = 0; x < XDIMENSION; x++) {
            for(int y = 0; y < YDIMENSION; y++) {
                gameBoard[x][y] = new Field(x, y);
            }
        }
    }

    public Field[][] getGameBoard() {
        return gameBoard;
    }

    public static int getXDIMENSION() {
        return XDIMENSION;
    }

    public static int getYDIMENSION() {
        return YDIMENSION;
    }
}
