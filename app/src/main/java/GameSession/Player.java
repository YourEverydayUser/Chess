package GameSession;
import Pieces.Board;
import Pieces.Field;
import Pieces.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Color color;
    private final List<Figure> figures;
    private final List<Figure> deadPool;
    private boolean isChecked;

    private Player(Color color) {
        figures = new ArrayList<>();
        deadPool = new ArrayList<>();
        this.color = color;
        isChecked = false;
    }

    public static Player initializeWhitePlayer(Board gameBoard) {
        Player whitePlayer = new Player(Color.WHITE);
        whitePlayer.figures.add(new King(gameBoard.getKeys()[4][0], true, Color.WHITE));
        whitePlayer.figures.add(new Queen(gameBoard.getKeys()[3][0], true, Color.WHITE));
        whitePlayer.figures.add(new Bishop(gameBoard.getKeys()[5][0], true, Color.WHITE));
        whitePlayer.figures.add(new Bishop(gameBoard.getKeys()[2][0], true, Color.WHITE));
        whitePlayer.figures.add(new Knight(gameBoard.getKeys()[6][0], true, Color.WHITE));
        whitePlayer.figures.add(new Knight(gameBoard.getKeys()[1][0], true, Color.WHITE));
        whitePlayer.figures.add(new Tower(gameBoard.getKeys()[7][0], true, Color.WHITE));
        whitePlayer.figures.add(new Tower(gameBoard.getKeys()[0][0], true, Color.WHITE));

        for(int i = 0; i <= 7; i++) {
            whitePlayer.figures.add(new Pawn(gameBoard.getKeys()[i][1], true, Color.WHITE));
        }

        return whitePlayer;
    }

    public static Player initializeBlackPlayer(Board gameBoard) {
        Player blackPlayer = new Player(Color.BLACK);
        blackPlayer.figures.add(new King(gameBoard.getKeys()[4][7], true, Color.BLACK));
        blackPlayer.figures.add(new Queen(gameBoard.getKeys()[3][7], true, Color.BLACK));
        blackPlayer.figures.add(new Bishop(gameBoard.getKeys()[5][7], true, Color.BLACK));
        blackPlayer.figures.add(new Bishop(gameBoard.getKeys()[2][7], true, Color.BLACK));
        blackPlayer.figures.add(new Knight(gameBoard.getKeys()[6][7], true, Color.BLACK));
        blackPlayer.figures.add(new Knight(gameBoard.getKeys()[1][7], true, Color.BLACK));
        blackPlayer.figures.add(new Tower(gameBoard.getKeys()[7][7], true, Color.BLACK));
        blackPlayer.figures.add(new Tower(gameBoard.getKeys()[0][7], true, Color.BLACK));

        for(int i = 0; i <= 7; i++) {
            blackPlayer.figures.add(new Pawn(gameBoard.getKeys()[i][6], true, Color.BLACK));
        }

        return blackPlayer;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public List<Figure> getDeadPool() {
        return deadPool;
    }

    public Color getColor() {
        return color;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
