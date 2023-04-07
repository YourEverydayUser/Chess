package Pieces.Figures;

import Pieces.Field;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.awt.*;
import java.util.ArrayList;

public class BishopTest extends TestCase {

    @Test
    public void testCalculatePossibleMoves() {

        //Setup
        Bishop bishop = new Bishop(new Field(4, 5), true, Color.BLACK);
        ArrayList<Point> correctMoves = new ArrayList<>();
        correctMoves.add(new Point(0, 1));
        correctMoves.add(new Point(1, 2));
        correctMoves.add(new Point(2, 3));
        correctMoves.add(new Point(3, 4));
        correctMoves.add(new Point(3, 6));
        correctMoves.add(new Point(4, 5));
        correctMoves.add(new Point(5, 4));
        correctMoves.add(new Point(5, 6));
        correctMoves.add(new Point(6, 3));
        correctMoves.add(new Point(6, 7));
        correctMoves.add(new Point(7, 2));

        //function call
        ArrayList<Point> allPossibleMoves = bishop.calculatePossibleMoves(bishop.getCurrentPosition());

        //test
        assertEquals(correctMoves, allPossibleMoves);

    }
}