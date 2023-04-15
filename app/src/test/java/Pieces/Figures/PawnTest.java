package Pieces.Figures;

import Pieces.Field;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class PawnTest {

    @Test
    public void testCalculatePossibleMovesWhitePawn() {

        //Setup
        Pawn pawn = new Pawn(new Field(1, 1), true, Color.WHITE);
        ArrayList<Point> correctMoves = new ArrayList<>();
        correctMoves.add(new Point(1, 2));
        correctMoves.add(new Point(1, 3));

        //function call
        ArrayList<Point> allPossibleMoves = pawn.calculatePossibleMoves(pawn.getCurrentPosition());

        //test
        for(Point point : correctMoves) {
            Assert.assertTrue(allPossibleMoves.contains(point));
        }
    }

    @Test
    public void testCalculatePossibleMovesBlackPawn() {

        //Setup
        Pawn pawn = new Pawn(new Field(3, 4), true, Color.BLACK);
        ArrayList<Point> correctMoves = new ArrayList<>();
        correctMoves.add(new Point(3, 3));

        //function call
        ArrayList<Point> allPossibleMoves = pawn.calculatePossibleMoves(pawn.getCurrentPosition());

        //test
        for(Point point : allPossibleMoves) {
            Assert.assertTrue(correctMoves.contains(point));
        }
    }
}
