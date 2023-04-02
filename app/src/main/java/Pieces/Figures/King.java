package Pieces.Figures;
import Pieces.Field;
import java.awt.*;
import java.util.ArrayList;

public class King extends Figure {

    public King(Field currentPosition, boolean onGameBoard, Color color) {
        super(currentPosition, onGameBoard, color);
    }

    /**
     * Returns the theoretically possible moves, which would be all adjacent fields for the king
     * @param currentPosition the current position of the king
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
                if(((absoluteYDistance + absoluteXDistance) <= 2) && (y != currentY || x != currentX)
                        && absoluteYDistance <= 1 && absoluteXDistance <= 1) {
                    possibleMoves.add(new Point(x, y));
                }
            }
        }
        return possibleMoves;
    }

    public static void main(String[] args) {
        King king = new King(new Field(4, 4), true, Color.BLACK);

        for(Point point : king.calculatePossibleMoves(king.getCurrentPosition())) {
            System.out.println(point);
        }
    }

}
