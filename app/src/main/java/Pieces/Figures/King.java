package Pieces.Figures;

import Pieces.Field;

import java.util.ArrayList;

public class King extends Figure {

    public King(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
    }

    private ArrayList<Field> calculatePossibleMoves(Field currentPosition) {
        int currentX = currentPosition.getxCoordinate();
        int currentY = currentPosition.getyCoordinate();


        return null;
    }

}
