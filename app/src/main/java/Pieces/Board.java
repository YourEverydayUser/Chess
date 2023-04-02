package Pieces;


import Pieces.Figures.*;

import java.util.HashMap;

import static Pieces.BoardDimension.XDIMENSION;
import static Pieces.BoardDimension.YDIMENSION;

public class Board {

    private HashMap<Field, Figure> gameBoard;
    private Field[][] keys = new Field[XDIMENSION.getValue()][YDIMENSION.getValue()];

    private enum state {
        FREE("o"),
        OCCUPIED("x");

        state(String s) {

        }
    }

    public Board() {
        gameBoard = new HashMap<>();

        for(int x = 0; x < XDIMENSION.getValue(); x++) {
            for(int y = 0; y < YDIMENSION.getValue(); y++) {
                keys[x][y] = new Field(x, y);
                gameBoard.put(keys[x][y], null);
            }
        }

    }

    public HashMap<Field, Figure> getGameBoard() {
        return gameBoard;
    }

    public Field[][] getKeys() {
        return keys;
    }

    public void initializeWhitePlayer() {

        gameBoard.put(keys[3][0], new King(keys[3][0], true, Color.WHITE));
        gameBoard.put(keys[4][0], new Queen(keys[4][0], true, Color.WHITE));
        gameBoard.put(keys[5][0], new Bishop(keys[5][0], true, Color.WHITE));
        gameBoard.put(keys[2][0], new Bishop(keys[2][0], true, Color.WHITE));
        gameBoard.put(keys[6][0], new Knight(keys[6][0], true, Color.WHITE));
        gameBoard.put(keys[1][0], new Knight(keys[1][0], true, Color.WHITE));
        gameBoard.put(keys[7][0], new Tower(keys[7][0], true, Color.WHITE));
        gameBoard.put(keys[0][0], new Tower(keys[0][0], true, Color.WHITE));

        for(int i = 0; i <= 7; i++) {
            gameBoard.put(keys[i][1], new Peasent(keys[i][1], true, Color.WHITE));
        }
    }

    public void initializeBlackPlayer() {
        gameBoard.put(keys[3][7], new King(keys[3][7], true, Color.BLACK));
        gameBoard.put(keys[4][7], new Queen(keys[4][7], true, Color.BLACK));
        gameBoard.put(keys[5][7], new Bishop(keys[5][7], true, Color.BLACK));
        gameBoard.put(keys[2][7], new Bishop(keys[2][7], true, Color.BLACK));
        gameBoard.put(keys[6][7], new Knight(keys[6][7], true, Color.BLACK));
        gameBoard.put(keys[1][7], new Knight(keys[1][7], true, Color.BLACK));
        gameBoard.put(keys[7][7], new Tower(keys[7][7], true, Color.BLACK));
        gameBoard.put(keys[0][7], new Tower(keys[0][7], true, Color.BLACK));

        for(int i = 0; i <= 7; i++) {
            gameBoard.put(keys[i][6], new Peasent(keys[i][6], true, Color.BLACK));
        }
    }

}
