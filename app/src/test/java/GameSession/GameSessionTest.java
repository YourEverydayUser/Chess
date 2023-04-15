package GameSession;
import Pieces.Field;
import Pieces.Figures.Color;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.function.Function;

public class GameSessionTest {

    @Test
    public void testInitializeGame() {
        //Setup
        GameSession game = GameSession.getInstance();
        Field[][] keys = game.accessBoard().getKeys();
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
            Assert.assertEquals("Peasent", pieceName.apply(keys[col][1]));
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
            Assert.assertEquals("Peasent", pieceName.apply(keys[col][6]));
            Assert.assertEquals(Color.BLACK, pieceColor.apply(keys[col][6]));
        }
    }
}
