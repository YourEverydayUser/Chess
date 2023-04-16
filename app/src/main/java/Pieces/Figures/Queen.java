package Pieces.Figures;
import Pieces.Field;
import java.awt.*;
import java.util.ArrayList;

public class Queen extends Figure {

    public Queen(Field currentPosition, boolean onGameBoard, Color color) {
        super(currentPosition, onGameBoard, color, "Queen");
    }

    /**
     * Returns the theoretically possible moves for the queen figure. Combined Movement rules of bishop and tower.
     * @param currentPosition the current position of the queen
     * @return ArrayList<Point> all possible moves
     */
    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int currentX = currentPosition.getxCoordinate();
        int currentY = currentPosition.getyCoordinate();

        for (int i = 0; i < 8; i++) {
            if (i != currentY) {
                possibleMoves.add(new Point(currentX, i));
            }
            if (i != currentX) {
                possibleMoves.add(new Point(i, currentY));
            }
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (Math.abs(currentX - x) == Math.abs(currentY - y) && (currentX != x && currentY != y)) {
                    possibleMoves.add(new Point(x, y));
                }
            }
        }

        return possibleMoves;
    }
}
