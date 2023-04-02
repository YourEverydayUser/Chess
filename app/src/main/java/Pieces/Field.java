package Pieces;

import Pieces.Figures.Figure;

import java.awt.*;
import java.util.ArrayList;

public class Field {

    private final int xCoordinate;
    private final int yCoordinate;
    private final String color;

    private Figure figure;

    public Field(int xCoordinate, int yCoordinate, Figure figure) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.figure = figure;

        if((xCoordinate+yCoordinate) % 2 == 0) {
            color = "black";
        } else {
            color = "white";
        }
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }


    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

}
