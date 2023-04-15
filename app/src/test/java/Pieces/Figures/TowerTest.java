package Pieces.Figures;

import Pieces.Field;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.*;

public class TowerTest {
    
        @Test
        public void testCalculatePossibleMoves() {

            //Setup
            Tower tower = new Tower(new Field(3, 3), true, Color.BLACK);
            ArrayList<Point> correctMoves = new ArrayList<>();
            correctMoves.add(new Point(0, 3));
            correctMoves.add(new Point(1, 3));
            correctMoves.add(new Point(2, 3));
            correctMoves.add(new Point(4, 3));
            correctMoves.add(new Point(5, 3));
            correctMoves.add(new Point(6, 3));
            correctMoves.add(new Point(7, 3));
            correctMoves.add(new Point(3, 0));
            correctMoves.add(new Point(3, 1));
            correctMoves.add(new Point(3, 2));
            correctMoves.add(new Point(3, 4));
            correctMoves.add(new Point(3, 5));
            correctMoves.add(new Point(3, 6));
            correctMoves.add(new Point(3, 7));

            //function call
            ArrayList<Point> allPossibleMoves = tower.calculatePossibleMoves(tower.getCurrentPosition());

            //test
            for (Point point : allPossibleMoves) {
                Assert.assertTrue(correctMoves.contains(point));
            }
        }
}
