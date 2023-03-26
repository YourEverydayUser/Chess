package Pieces.Figures;

import Pieces.Field;

import java.util.ArrayList;

public class King extends Figure {

    public King(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
    }

    private ArrayList<Field> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Field> possibleMoves = new ArrayList<>();

        for(Field field : currentPosition.getNeighborFields()) {
            if(field.getFigure() == null) {
                possibleMoves.add(field);
            }
        }

        return possibleMoves;
    }

}
