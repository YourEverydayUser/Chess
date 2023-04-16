package Chess;

import GameSession.GameSession;
import Pieces.Field;
import Pieces.Figures.Color;
import org.junit.Assert;
import org.junit.Test;

public class MoveSetTests {

    /**
     * 1.) Pawn white from (3, 1) to (3, 3)
     * 2.) Pawn black from (4, 6) to (4, 4)
     * 3.) Pawn white from (3, 1) takes black pawn on (4, 4)
     */

    @Test
    public void moveSet1() {
        // Setup
        GameSession game = GameSession.getInstance();
        Field[][] keys = game.accessBoard().getKeys();

        // When
        game.playTurn(game.accessBoard().getKeys()[3][1], game.accessBoard().getKeys()[3][3]);
        game.playTurn(game.accessBoard().getKeys()[4][6], game.accessBoard().getKeys()[4][4]);

        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[3][3]).getName(), "Pawn");
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[3][3]).getColor(), Color.WHITE);
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[4][4]).getName(), "Pawn");
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[4][4]).getColor(), Color.BLACK);

        // When
        game.playTurn(game.accessBoard().getKeys()[3][3], game.accessBoard().getKeys()[4][4]);

        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[4][4]).getColor(), Color.WHITE);
    }

    /**
     * 1.) Pawn white from (2, 1) to (2, 3)
     * 2.) Pawn black from (2, 6) to (2, 4)
     * 3.) Queen white from (3, 0) to (0, 3)
     * 4.) Queen black from (3, 7) to (0, 4)
     * 5.) Queen white from (0, 3) takes Queen black on (0, 4)
     */

    @Test
    public void moveSet2() {
        // Setup
        GameSession game = GameSession.getInstance();
        Field[][] keys = game.accessBoard().getKeys();

        // When
        game.playTurn(game.accessBoard().getKeys()[2][1], game.accessBoard().getKeys()[2][3]);
        game.playTurn(game.accessBoard().getKeys()[2][6], game.accessBoard().getKeys()[2][4]);
        game.playTurn(game.accessBoard().getKeys()[3][0], game.accessBoard().getKeys()[0][3]);
        game.playTurn(game.accessBoard().getKeys()[3][7], game.accessBoard().getKeys()[0][4]);

        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[2][3]).getName(), "Pawn");
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[2][3]).getColor(), Color.WHITE);
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[2][4]).getName(), "Pawn");
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[2][4]).getColor(), Color.BLACK);
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[0][3]).getName(), "Queen");
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[0][3]).getColor(), Color.WHITE);
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[0][4]).getName(), "Queen");
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[0][4]).getColor(), Color.BLACK);

        // When
        game.playTurn(game.accessBoard().getKeys()[0][3], game.accessBoard().getKeys()[0][4]);
    }

    /**
     * 1.) Pawn white from (7, 1) to (7, 3)
     * 2.) Pawn black from (7, 6) to (7, 4)
     * 3.) Pawn white from (6, 1) to (6, 3)
     * 4.) Pawn black from (7, 4) to (6, 3)
     */

    @Test
    public void moveSet3() {
        // Setup
        GameSession game = GameSession.getInstance();
        Field[][] keys = game.accessBoard().getKeys();

        // When
        game.playTurn(game.accessBoard().getKeys()[7][1], game.accessBoard().getKeys()[7][3]);
        game.playTurn(game.accessBoard().getKeys()[7][6], game.accessBoard().getKeys()[7][4]);
        game.playTurn(game.accessBoard().getKeys()[6][1], game.accessBoard().getKeys()[6][3]);
        game.playTurn(game.accessBoard().getKeys()[7][4], game.accessBoard().getKeys()[6][3]);

        // Then
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[6][3]).getName(), "Pawn");
        Assert.assertEquals(game.accessBoard().getGameBoard().get(game.accessBoard().getKeys()[6][3]).getColor(), Color.BLACK);

    }
}
