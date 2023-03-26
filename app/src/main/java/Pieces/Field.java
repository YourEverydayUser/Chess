package Pieces;

import Pieces.Figures.Figure;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Field {

    private final int xCoordinate;
    private final int yCoordinate;
    private final ArrayList<Field> neighborFields;
    private Figure figure;

    public Field(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        figure = null;

        neighborFields = new ArrayList<>();
        for(Field field : getPossibleNeighborFields(xCoordinate, yCoordinate)) {
            if((field.xCoordinate >= 0 && field.xCoordinate <= 7) &&
                    field.yCoordinate >= 0 && field.yCoordinate <= 7) {
                neighborFields.add(field);
            }
        }
    }

    public Field(int xCoordinate, int yCoordinate, Figure figure) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.figure = figure;

        neighborFields = new ArrayList<>();
        for(Field field : getPossibleNeighborFields(xCoordinate, yCoordinate)) {
            if((field.xCoordinate >= 0 && field.xCoordinate <= 7) &&
                    field.yCoordinate >= 0 && field.yCoordinate <= 7) {
                neighborFields.add(field);
            }
        }
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public ArrayList<Field> getNeighborFields() {
        return neighborFields;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    private ArrayList<Field> getPossibleNeighborFields(int xCoordinate, int yCoordinate) {
        ArrayList<Field> possibleFields = new ArrayList<>();
        possibleFields.add(new Field(xCoordinate + 1, yCoordinate));
        possibleFields.add(new Field(xCoordinate + 1, yCoordinate + 1));
        possibleFields.add(new Field(xCoordinate + 1, yCoordinate - 1));
        possibleFields.add(new Field(xCoordinate - 1, yCoordinate));
        possibleFields.add(new Field(xCoordinate - 1, yCoordinate + 1));
        possibleFields.add(new Field(xCoordinate - 1, yCoordinate - 1));
        possibleFields.add(new Field(xCoordinate, yCoordinate + 1));
        possibleFields.add(new Field(xCoordinate, yCoordinate -1));

        return possibleFields;
    }

}
