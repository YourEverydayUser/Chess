package Pieces;

import java.awt.*;

public class Field {

    private final int XCOORDINATE;
    private final int YCOORDINATE;
    private final Color COLOR;

    //are needed for the GUI to define when a field is selected
    private int lowerYBorder;
    private int lowerXBorder;
    private int upperYBorder;
    private int upperXBorder;

    public Field(int xCoordinate, int yCoordinate) {
        this.XCOORDINATE = xCoordinate;
        this.YCOORDINATE = yCoordinate;

        if((xCoordinate+yCoordinate) % 2 == 0) {
            COLOR = Color.BLACK;
        } else {
            COLOR = Color.WHITE;
        }
    }

    public int getxCoordinate() {
        return XCOORDINATE;
    }

    public int getyCoordinate() {
        return YCOORDINATE;
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
