package Pieces.Figures;

import Pieces.Field;

import java.awt.*;
import java.util.ArrayList;

public class King extends Figure {

    public King(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
    }

    private ArrayList<Field> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Field> possibleMoves = new ArrayList<>();

        for(Point point : currentPosition.getNeighborFields()) {
            possibleMoves.add(new Field(point.x, point.y));

        }

        return possibleMoves;
    }

}
