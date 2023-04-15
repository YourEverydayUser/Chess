package GameSession;
import Pieces.Field;
import Pieces.Figures.Color;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Set;
import java.util.function.Function;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameSessionTest {

    static GameSession game = GameSession.getInstance();
    static Field[][] keys = game.accessBoard().getKeys();
    @Test
    public void testA() {
        //Setup
        Function<Field, String> pieceName = field -> game.accessBoard().getGameBoard().get(field).getName();
        Function<Field, Color> pieceColor = field -> game.accessBoard().getGameBoard().get(field).getColor();

        // Test the position of the white pieces
        Assert.assertEquals("Tower", pieceName.apply(keys[0][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[0][0]));
        Assert.assertEquals("Knight", pieceName.apply(keys[1][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[1][0]));
        Assert.assertEquals("Bishop", pieceName.apply(keys[2][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[2][0]));
        Assert.assertEquals("Queen", pieceName.apply(keys[3][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[3][0]));
        Assert.assertEquals("King", pieceName.apply(keys[4][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[4][0]));
        Assert.assertEquals("Bishop", pieceName.apply(keys[5][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[5][0]));
        Assert.assertEquals("Knight", pieceName.apply(keys[6][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[6][0]));
        Assert.assertEquals("Tower", pieceName.apply(keys[7][0]));
        Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[7][0]));
        for (int col = 0; col < 8; col++) {
            Assert.assertEquals("Pawn", pieceName.apply(keys[col][1]));
            Assert.assertEquals(Color.WHITE, pieceColor.apply(keys[col][1]));
        }

        // Test the position of the black pieces
        Assert.assertEquals("Tower", pieceName.apply(keys[0][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[0][7]));
        Assert.assertEquals("Knight", pieceName.apply(keys[1][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[1][7]));
        Assert.assertEquals("Bishop", pieceName.apply(keys[2][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[2][7]));
        Assert.assertEquals("Queen", pieceName.apply(keys[3][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[3][7]));
        Assert.assertEquals("King", pieceName.apply(keys[4][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[4][7]));
        Assert.assertEquals("Bishop", pieceName.apply(keys[5][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[5][7]));
        Assert.assertEquals("Knight", pieceName.apply(keys[6][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[6][7]));
        Assert.assertEquals("Tower", pieceName.apply(keys[7][7]));
        Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[7][7]));
        for (int col = 0; col < 8; col++) {
            Assert.assertEquals("Pawn", pieceName.apply(keys[col][6]));
            Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[col][6]));
        }
    }

    @Test
    public void testB() {
        for(Field[] field : keys) {
            for(Field field1 : field) {
                Assert.assertTrue(game.accessBoard().getGameBoard().containsKey(field1));
            }

        }
    }

    @Test
    public void testC() {
        // When
        game.playTurn(keys[1][1], keys[1][3]);
        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(keys[1][3]).getName(), "Pawn");
    }

    @Test
    public void testD() {
        // When
        game.playTurn(keys[1][6], keys[1][4]);
        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(keys[1][4]).getName(), "Pawn");
    }

    @Test
    public void testE() {
        // When
        game.playTurn(keys[1][0], keys[2][2]);
        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(keys[2][2]).getName(), "Knight");
    }

    @Test
    public void testF() {
        // When
        game.playTurn(keys[1][7], keys[0][5]);
        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(keys[0][5]).getName(), "Knight");
    }

    @Test
    public void testG() {
        // When
        game.playTurn(keys[0][0], keys[1][0]);
        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(keys[1][0]).getName(), "Tower");
    }

    public static void main(String[] args) {
        GameSession game = GameSession.getInstance();
        Field[][] keys = game.accessBoard().getKeys();

    }


}
