package Pieces.Figures;

import Pieces.Field;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Figure {

    public Queen(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
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

        int sum = currentX + currentY;

        for(int x = 0; x <= 7; x++) {
            for(int y = 0; y <= 7; y++) {
                if((((currentX == x) && (currentY != y)) || ((currentX != x) && (currentY == y))) || //tower rules
                   (x+y==sum) || ((((x-currentX) + (y - currentY)) % 2 == 0) && ((x-currentX) == (y - currentY)))) {//bishop rules
                    possibleMoves.add(new Point(x, y));
                }
            }
        }
        return possibleMoves;
    }

    public static void main(String[] args) {
        Queen quuen = new Queen(new Field(0, 0, null), true);

        for(Point point : quuen.calculatePossibleMoves(quuen.getCurrentPosition())) {
            System.out.println(point);
        }
    }
}
