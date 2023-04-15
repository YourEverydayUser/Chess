package Pieces;

import java.awt.*;

public class Field {

    private final int XCOORDINATE;
    private final int YCOORDINATE;
    private final Color COLOR;

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

    public static Point transformFieldToPoint(Field field) {
        return new Point(field.getxCoordinate(), field.getyCoordinate());
    }


}
