package Pieces.Figures;

import Pieces.Field;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class KingTest {

    @Test
    public void testCalculatePossibleMoves() {

        //Setup
        King king = new King(new Field(3, 2), true, Color.BLACK);
        ArrayList<Point> correctMoves = new ArrayList<>();
        correctMoves.add(new Point(2, 1));
        correctMoves.add(new Point(2, 2));
        correctMoves.add(new Point(2, 3));
        correctMoves.add(new Point(3, 1));
        correctMoves.add(new Point(3, 3));
        correctMoves.add(new Point(4, 1));
        correctMoves.add(new Point(4, 2));
        correctMoves.add(new Point(4, 3));

        //function call
        ArrayList<Point> allPossibleMoves = king.calculatePossibleMoves(king.getCurrentPosition());

        //test
        for(Point point : allPossibleMoves) {
            Assert.assertTrue(correctMoves.contains(point));
        }
    }
}
