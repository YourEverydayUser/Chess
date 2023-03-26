package Pieces;

import Pieces.Figures.Figure;

import java.awt.*;
import java.util.ArrayList;

public class Field {

    private final int xCoordinate;
    private final int yCoordinate;
    private final ArrayList<Point> neighborFields;
    private final String color;

    private Figure figure;


    public Field(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        figure = null;

        neighborFields = new ArrayList<>();
        for(Point point : getPossibleNeighborFields(xCoordinate, yCoordinate)) {
            if((point.getX() >= 0 && point.getY() <= 7) &&
                    point.getY() >= 0 && point.getY() <= 7) {
                neighborFields.add(new Point(point.x, point.y));
            }
        }

        if((xCoordinate+yCoordinate) % 2 == 0) {
            color = "black";
        } else {
            color = "white";
        }
    }

    public Field(int xCoordinate, int yCoordinate, Figure figure) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.figure = figure;

        neighborFields = new ArrayList<>();
        for(Point point : getPossibleNeighborFields(xCoordinate, yCoordinate)) {
            if((point.getX() >= 0 && point.getY() <= 7) &&
                    point.getY() >= 0 && point.getY() <= 7) {
                neighborFields.add(new Point(point.x, point.y));
            }
        }

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

    public ArrayList<Point> getNeighborFields() {
        return neighborFields;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    private ArrayList<Point> getPossibleNeighborFields(int xCoordinate, int yCoordinate) {
            ArrayList<Point> possibleFields = new ArrayList<Point>();

            possibleFields.add(new Point(xCoordinate + 1, yCoordinate));
            possibleFields.add(new Point(xCoordinate + 1, yCoordinate + 1));
            possibleFields.add(new Point(xCoordinate + 1, yCoordinate - 1));
            possibleFields.add(new Point(xCoordinate - 1, yCoordinate));
            possibleFields.add(new Point(xCoordinate - 1, yCoordinate + 1));
            possibleFields.add(new Point(xCoordinate - 1, yCoordinate - 1));
            possibleFields.add(new Point(xCoordinate, yCoordinate + 1));
            possibleFields.add(new Point(xCoordinate, yCoordinate - 1));


        return possibleFields;
    }

}
