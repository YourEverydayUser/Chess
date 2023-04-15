package Pieces.Figures;
import Pieces.Field;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Figure {

    public Pawn(Field currentPosition, boolean onGameBoard, Color color) {
        super(currentPosition, onGameBoard, color, "Pawn");
    }

    public ArrayList<Point> calculatePossibleMoves(Field currentPosition) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        int row = currentPosition.getxCoordinate();
        int col = currentPosition.getyCoordinate();
        int forwardDirection = this.getColor() == Color.WHITE ? 1 : -1;

        // One step forward
        Field oneStepForward = new Field(row, col + forwardDirection);
        if (oneStepForward.getxCoordinate() >= 0 && oneStepForward.getyCoordinate() >= 0
            && oneStepForward.getxCoordinate() < 8 && oneStepForward.getyCoordinate() < 8) {
            possibleMoves.add(new Point(oneStepForward.getxCoordinate(), oneStepForward.getyCoordinate()));
        }

        // Two steps forward (only on the initial position)
        if (row == (getColor() == Color.WHITE ? 1 : 6)) {
            Field twoStepsForward = new Field(row, col + 2 * forwardDirection);
            possibleMoves.add(new Point(twoStepsForward.getxCoordinate(), twoStepsForward.getyCoordinate()));
        }

        return possibleMoves;
    }
}
