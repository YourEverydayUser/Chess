package Pieces;

import Pieces.Figures.Figure;

import java.awt.*;
import java.util.ArrayList;

public class Field {

    private final int xCoordinate;
    private final int yCoordinate;
    private final Color color;

    //are needed for the GUI to define when a field is selected
    private int lowerYBorder;
    private int lowerXBorder;
    private int upperYBorder;
    private int upperXBorder;

    public Field(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        if((xCoordinate+yCoordinate) % 2 == 0) {
            color = Color.BLACK;
        } else {
            color = Color.WHITE;
        }
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getLowerXBorder() {
        return lowerXBorder;
    }

    public int getLowerYBorder() {
        return lowerYBorder;
    }

    public int getUpperXBorder() {
        return upperXBorder;
    }

    public int getUpperYBorder() {
        return upperYBorder;
    }

    public void setUpperXBorder(int upperXBorder) {
        this.upperXBorder = upperXBorder;
    }

    public void setUpperYBorder(int upperYBorder) {
        this.upperYBorder = upperYBorder;
    }

    public void setLowerXBorder(int lowerXBorder) {
        this.lowerXBorder = lowerXBorder;
    }

    public void setLowerYBorder(int lowerYBorder) {
        this.lowerYBorder = lowerYBorder;
    }
}
