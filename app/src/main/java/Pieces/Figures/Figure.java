package Pieces.Figures;
import Pieces.Field;

import java.awt.*;
import java.util.ArrayList;

public abstract class Figure {

    private Field currentPosition;
    private boolean onGameBoard;
    private final Color COLOR;
    private final String NAME;

    public Figure(Field currentPosition, boolean onGameBoard, Color color, String name) {
        this.currentPosition = currentPosition;
        onGameBoard = true;
        this.COLOR = color;
        this.NAME = name;
    }

    public abstract ArrayList<Point> calculatePossibleMoves(Field currentPosition);

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

    public Color getColor() {
        return COLOR;
    }

    public String getName() {
        return NAME;
    }
}
