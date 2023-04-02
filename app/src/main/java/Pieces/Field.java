package Pieces;

import Pieces.Figures.Figure;

import java.awt.*;
import java.util.ArrayList;

public class Field {

    private final int xCoordinate;
    private final int yCoordinate;
    private final Color color;

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

}
