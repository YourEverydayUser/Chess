package Pieces.Figures;
import Pieces.Field;
import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Figure {

    public Bishop(Field currentPosition, boolean onGameBoard, Color color) {
        super(currentPosition, onGameBoard, color, "Bishop");
    }

    /**
     * Returns the theoretically possible moves for the bishop figure
     * @param currentPosition the current position of the bishop
     * @return ArrayList<Point> all possible moves
     */
    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int currentX = currentPosition.getxCoordinate();
        int currentY = currentPosition.getyCoordinate();

        int sum = currentX + currentY;

        for(int x = 0; x <= 7; x++) {
            for(int y = 0; y <= 7; y++) {
                if((x + y == sum) || ((((x - currentX) + (y - currentY)) % 2 == 0) && ((x - currentX) == (y - currentY)))) {
                    possibleMoves.add(new Point(x, y));
                }
            }
        }
        return possibleMoves;
    }

    public static void main(String[] args) {
        Bishop bishop = new Bishop(new Field(4, 5), true, Color.BLACK);

        for(Point point : bishop.calculatePossibleMoves(bishop.getCurrentPosition())) {
            System.out.println(point);
        }
    }
}
