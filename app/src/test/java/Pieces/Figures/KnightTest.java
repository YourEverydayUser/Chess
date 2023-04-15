package Pieces.Figures;

import Pieces.Field;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class KnightTest {

    @Test
    public void testCalculatePossibleMoves() {

        //Setup
        Knight knight = new Knight(new Field(2, 1), true, Color.BLACK);
        ArrayList<Point> correctMoves = new ArrayList<>();
        correctMoves.add(new Point(0, 0));
        correctMoves.add(new Point(0, 2));
        correctMoves.add(new Point(1, 3));
        correctMoves.add(new Point(3, 3));
        correctMoves.add(new Point(4, 2));
        correctMoves.add(new Point(4, 0));

        //function call
        ArrayList<Point> allPossibleMoves = knight.calculatePossibleMoves(knight.getCurrentPosition());

        //test
        for(Point point : allPossibleMoves) {
            Assert.assertTrue(correctMoves.contains(point));
        }
    }
}