package Pieces.Figures;
import Pieces.Field;
import java.util.ArrayList;

public abstract class Figure {

    private Field currentPosition;
    private boolean onGameBoard;
    private ArrayList<Field> possibleMoves;
    private final Color color;
    private String name;

    public Figure(Field currentPosition, boolean onGameBoard, Color color, String name) {
        this.currentPosition = currentPosition;
        onGameBoard = true;
        this.color = color;
        this.name = name;
    }

    public Field getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Field currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isOnGameBoard() {
        return onGameBoard;
    }

    public void setOnGameBoard(boolean onGameBoard) {
        this.onGameBoard = onGameBoard;
    }

    public ArrayList<Field> getPossibleMoves() {
        return possibleMoves;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
