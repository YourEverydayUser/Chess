package Pieces.Figures;
import Pieces.Field;
import java.awt.*;
import java.util.ArrayList;

public class Tower extends Figure {

    public Tower(Field currentPosition, boolean onGameBoard, Color color) {
        super(currentPosition, onGameBoard, color, "Tower");
    }

    /**
     * Returns the theoretically possible moves, which would be all the fields where x xor y varies.
     * @param currentPosition the current position of the tower
     * @return ArrayList<Point> all possible moves
     */
    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int currentX = currentPosition.getxCoordinate();
        int currentY = currentPosition.getyCoordinate();

        for(int x = 0; x <= 7; x++) {
            for(int y = 0; y <= 7; y++) {
                if(((currentX == x) && (currentY != y)) || ((currentX != x) && (currentY == y))) {
                    possibleMoves.add(new Point(x, y));
                }
            }
        }
        return possibleMoves;
    }
}
