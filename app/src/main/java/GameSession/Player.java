package GameSession;
import Pieces.Field;
import Pieces.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String color;
    private List<Figure> figures;
    private List<Figure> deadPool;
    private boolean activePlayer;

    private Player(String color, Boolean activePlayer) {
        figures = new ArrayList<>();
        deadPool = new ArrayList<>();
        this.color = color;
        this.activePlayer = activePlayer;
    }

    public static Player initializeWhitePlayer() {
        Player whitePlayer = new Player("white", true);
        whitePlayer.figures.add(new King(new Field(3, 0), true, Color.WHITE));
        whitePlayer.figures.add(new Queen(new Field(4, 0), true, Color.WHITE));
        whitePlayer.figures.add(new Bishop(new Field(5, 0), true, Color.WHITE));
        whitePlayer.figures.add(new Bishop(new Field(2, 0), true, Color.WHITE));
        whitePlayer.figures.add(new Knight(new Field(6, 0), true, Color.WHITE));
        whitePlayer.figures.add(new Knight(new Field(1, 0), true, Color.WHITE));
        whitePlayer.figures.add(new Tower(new Field(7, 0), true, Color.WHITE));
        whitePlayer.figures.add(new Tower(new Field(0, 0), true, Color.WHITE));

        for(int i = 0; i <= 7; i++) {
            whitePlayer.figures.add(new Peasent(new Field(i, 1), true, Color.WHITE));
        }

        return whitePlayer;
    }

    public static Player initializeBlackPlayer() {
        Player blackPlayer = new Player("black", false);
        blackPlayer.figures.add(new King(new Field(3, 7), true, Color.BLACK));
        blackPlayer.figures.add(new Queen(new Field(4, 7), true, Color.BLACK));
        blackPlayer.figures.add(new Bishop(new Field(5, 7), true, Color.BLACK));
        blackPlayer.figures.add(new Bishop(new Field(2, 7), true, Color.BLACK));
        blackPlayer.figures.add(new Knight(new Field(6, 7), true, Color.BLACK));
        blackPlayer.figures.add(new Knight(new Field(1, 7), true, Color.BLACK));
        blackPlayer.figures.add(new Tower(new Field(7, 7), true, Color.BLACK));
        blackPlayer.figures.add(new Tower(new Field(0, 7), true, Color.BLACK));

        for(int i = 0; i <= 7; i++) {
            blackPlayer.figures.add(new Peasent(new Field(i, 6), true, Color.BLACK));
        }

        return blackPlayer;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public boolean isActivePlayer() {
        return activePlayer;
    }
}
