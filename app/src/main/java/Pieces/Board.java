package Pieces;

import Pieces.Figures.*;

import java.util.ArrayList;
import java.util.HashMap;
import static Pieces.BoardDimension.XDIMENSION;
import static Pieces.BoardDimension.YDIMENSION;

public class Board {

    private HashMap<Field, Figure> gameBoard;
    private final Field[][] keys = new Field[XDIMENSION.getValue()][YDIMENSION.getValue()];

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

    //TODO filter all possible moves and get only the valid ones
    public ArrayList<Field> getValidMoves(Figure figure) {
        ArrayList<Field> allPossibleMoves = figure.getPossibleMoves();
        int xmax;
        int ymax;

        if(!figure.getName().equals("Knight")) {
            for(Field field : allPossibleMoves) {
                if(gameBoard.get(field) != null) {
                    if(field.getyCoordinate() == figure.getCurrentPosition().getyCoordinate()) {
                        xmax = field.getxCoordinate();
                    }
                    xmax = field.getxCoordinate();
                    ymax = field.getyCoordinate();

                    allPossibleMoves.remove(field);
                }
            }
        }
        return null;
    }

}
