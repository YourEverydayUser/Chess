package GameSession.Players;
import Pieces.Figures.Figure;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String color;
    protected List<Figure> figures;

    public Player() {
        figures = new ArrayList<>();
    }

    public abstract void initialize();
}
