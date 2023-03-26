package Pieces.Figures;

import Pieces.Field;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Figure {

    public Bishop(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
    }

    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int x = currentPosition.getxCoordinate();
        int y = currentPosition.getyCoordinate();

        int sum = x + y;

        for(int i = 0; i <= 7; i++) {
            for(int j = 0; j <= 7; j++) {
                if((i+j==sum) || ((((i-x) + (j - y)) % 2 == 0) && ((i-x) == (j - y)))) {
                    possibleMoves.add(new Point(i, j));
                }
            }
        }
        return possibleMoves;
    }

    public static void main(String[] args) {
        Bishop bishop = new Bishop(new Field(4, 5, null), true);

        for(Point point : bishop.calculatePossibleMoves(bishop.getCurrentPosition())) {
            System.out.println(point);
        }
    }
}
