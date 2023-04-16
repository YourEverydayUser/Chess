package Pieces;

import java.awt.*;

/**
 * The chessboard consists of 64 fields. Each field has an x- and y-coordinate, which
 * can be accessed.
 * @Author Fabian Kuster
 */

public class Field {

    private final int X_COORDINATE;
    private final int Y_COORDINATE;

    public Field(int xCoordinate, int yCoordinate) {
        this.X_COORDINATE = xCoordinate;
        this.Y_COORDINATE = yCoordinate;
    }

    public int getxCoordinate() {
        return X_COORDINATE;
    }

    public int getyCoordinate() {
        return Y_COORDINATE;
    }

    public static Point transformFieldToPoint(Field field) {
        return new Point(field.getxCoordinate(), field.getyCoordinate());
    }

}
