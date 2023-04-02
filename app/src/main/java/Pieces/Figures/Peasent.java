package Pieces.Figures;
import Pieces.Field;

public class Peasent extends Figure {

    private final int movementRange = 1;
    private final int initialRange = 2;

    public Peasent(Field currentPosition, boolean onGameBoard, Color color) {
        super(currentPosition, onGameBoard, Color.BLACK);
    }
}
