package Pieces;

import GameSession.GameSession;
import GameSession.Player;
import Pieces.Figures.Figure;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BoardTest extends TestCase {

    GameSession gameSession = GameSession.getInstance();
    Player blackPlayer = gameSession.getPlayers().get(0);
    Player whitePlayer = gameSession.getPlayers().get(1);

    @Test
    public void testPossibleMovesKnight() {
        Figure figure = blackPlayer.getFigures().get(4);
        ArrayList<Field> correctMoves = new ArrayList<>();
        correctMoves.add(gameSession.accessBoard().getKeys()[5][5]);
        correctMoves.add(gameSession.accessBoard().getKeys()[7][5]);

        ArrayList<Field> validMoves = gameSession.accessBoard().getValidMoves(figure);

        for(Field field : validMoves) {
            Assert.assertTrue(correctMoves.contains(field));
        }
    }

    @Test
    public void testPossibleMovesBishopBlack() {
        Figure figure = blackPlayer.getFigures().get(3);
        ArrayList<Field> validMoves = gameSession.accessBoard().getValidMoves(figure);

        Assert.assertEquals(0, validMoves.size());
    }

    @Test
    public void testPossibleMovesBishopWhite() {
        Figure figure = whitePlayer.getFigures().get(3);
        ArrayList<Field> validMoves = gameSession.accessBoard().getValidMoves(figure);

        Assert.assertEquals(0, validMoves.size());
    }

    @Test
    public void testPossibleMovesQueenBlack() {
        Figure figure = whitePlayer.getFigures().get(1);
        ArrayList<Field> validMoves = gameSession.accessBoard().getValidMoves(figure);

        Assert.assertEquals(0, validMoves.size());
    }

    @Test
    public void testPossibleMovesQueenWhite() {
        Figure figure = whitePlayer.getFigures().get(1);
        ArrayList<Field> validMoves = gameSession.accessBoard().getValidMoves(figure);

        Assert.assertEquals(0, validMoves.size());
    }
}