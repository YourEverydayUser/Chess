package Pieces.Figures;

import Pieces.Field;

import java.awt.*;
import java.util.ArrayList;

public class King extends Figure {

    public King(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
    }

    /**
     * Returns the theoreticly possible moves, which would be all adjacent fields for the kind
     * @param currentPosition the current position of the king
     * @return ArrayList<Point> all neighboring Fields
     */
    private ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        return new ArrayList<>(currentPosition.getNeighborFields());
    }

}
