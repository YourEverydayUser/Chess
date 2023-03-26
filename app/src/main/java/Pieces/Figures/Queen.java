package Pieces.Figures;

import Pieces.Field;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Figure {

    public Queen(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
    }

    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int x = currentPosition.getxCoordinate();
        int y = currentPosition.getyCoordinate();

        int sum = x + y;

        for(int i = 0; i <= 7; i++) {
            for(int j = 0; j <= 7; j++) {
                if((((x == i) && (y != j)) || ((x != i) && (y == j))) || //tower rules
                   (i+j==sum) || ((((i-x) + (j - y)) % 2 == 0) && ((i-x) == (j - y)))) {//bishop rules
                    possibleMoves.add(new Point(i, j));
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
