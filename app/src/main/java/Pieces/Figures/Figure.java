package Pieces.Figures;

import Pieces.Field;

import java.util.ArrayList;

public class Figure {

    private Field currentPosition;
    private boolean onGameBoard;
    private ArrayList<Field> possibleMoves;

    public Figure(Field currentPosition, boolean onGameBoard) {
        this.currentPosition = currentPosition;
        onGameBoard = true;
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

}
