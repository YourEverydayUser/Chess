package Pieces;

import Pieces.Figures.Figure;

import java.util.ArrayList;

public class Field {

    private int xCoordinate;
    private int yCoordinate;
    private Figure figure;
    private ArrayList<Field> neighborFields;

    public Field(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        neighborFields = new ArrayList<>();

        if(xCoordinate == 0 && yCoordinate == 0) {
            neighborFields.add(new Field(1, 0));
            neighborFields.add(new Field(0, 1));
            neighborFields.add(new Field(1, 1));
        }
    }

    public Field(int xCoordinate, int yCoordinate, Figure figure) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.figure = figure;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

}
