package Pieces.Figures;
import Pieces.Field;
import java.awt.*;
import java.util.ArrayList;

public class Knight extends Figure {

    public Knight(Field currentPosition, boolean onGameBoard, Color color) {
        super(currentPosition, onGameBoard, color, "Knight");
    }

    /**
     * Returns the theoretically possible moves for the knight
     * @param currentPosition the current position of the knight
     * @return ArrayList<Point> all possible moves
     */
    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int currentX = currentPosition.getxCoordinate();
        int currentY = currentPosition.getyCoordinate();

        for(int x = 0; x <= 7; x++) {
            int absoluteXDistance = Math.abs(currentX - x);
            for(int y = 0; y <= 7; y++) {
                int absoluteYDistance = Math.abs(currentY - y);
                if((absoluteYDistance + absoluteXDistance == 3) && (y != currentY || x != currentX)
                        && absoluteYDistance <= 2 && absoluteXDistance <= 2) {
                    possibleMoves.add(new Point(x, y));
                }
            }
        }

        return possibleMoves;
    }

}
