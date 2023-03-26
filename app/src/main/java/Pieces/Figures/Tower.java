package Pieces.Figures;

import Pieces.Field;

import java.awt.*;
import java.util.ArrayList;

public class Tower extends Figure {

    public Tower(Field currentPosition, boolean onGameBoard) {
        super(currentPosition, onGameBoard);
    }

    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int x = currentPosition.getxCoordinate();
        int y = currentPosition.getyCoordinate();

        for(int i = 0; i <= 7; i++) {
            for(int j = 0; j <= 7; j++) {
                if(((x == i) && (y != j)) || ((x != i) && (y == j))) {
                    possibleMoves.add(new Point(i, j));
                }
            }
        }
        return possibleMoves;
    }

    public static void main(String[] args) {
        Tower tower = new Tower(new Field(0, 0, null), true);

        for(Point point : tower.calculatePossibleMoves(tower.getCurrentPosition())) {
            System.out.println(point);
        }
    }
}
