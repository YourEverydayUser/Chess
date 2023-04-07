package GameSession;
import Pieces.Field;
import Pieces.Figures.Color;
import Pieces.Figures.Figure;
import Pieces.Figures.King;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String color;
    private List<Figure> figures;
    private boolean activePlayer;

    private Player(String color, Boolean activePlayer) {
        figures = new ArrayList<>();
        this.color = color;
        this.activePlayer = activePlayer;
    }

    public static Player initializeWhitePlayer() {
        Player whitePlayer = new Player("white", true);
        whitePlayer.figures.add(new King(new Field(3, 0), true, Color.WHITE));
        return whitePlayer;
    }

    public static Player initializeBlackPlayer() {
        return new Player("black", false);
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public boolean isActivePlayer() {
        return activePlayer;
    }
}
