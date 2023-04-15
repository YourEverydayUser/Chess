package Pieces;

import Pieces.Figures.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import static Pieces.BoardDimension.XDIMENSION;
import static Pieces.BoardDimension.YDIMENSION;

public class Board {

    private HashMap<Field, Figure> gameBoard;
    private final Field[][] keys = new Field[XDIMENSION.getValue()][YDIMENSION.getValue()];

    public Board() {
        gameBoard = new HashMap<>();

        for(int x = 0; x < XDIMENSION.getValue(); x++) {
            for(int y = 0; y < YDIMENSION.getValue(); y++) {
                keys[x][y] = new Field(x, y);
                gameBoard.put(keys[x][y], null);
            }
        }
    }

    public HashMap<Field, Figure> getGameBoard() {
        return gameBoard;
    }

    public Field[][] getKeys() {
        return keys;
    }


    public ArrayList<Field> getValidMoves(Figure figure) {
        ArrayList<Point> allPossibleMoves = figure.calculatePossibleMoves(figure.getCurrentPosition());
        ArrayList<Point> invalidMoves = new ArrayList<>();
        ArrayList<Field> validMoves = new ArrayList<>();

        switch(figure.getName()) {
            case "Knight", "King":
                allPossibleMoves.removeIf(field -> gameBoard.get(getKeys()[field.x][field.y]) != null);
                break;
            case "Bishop":
                    for (Point point : allPossibleMoves) {
                        Field field = getKeys()[point.x][point.y];
                        if (gameBoard.get(field) != null) {
                            invalidMoves.addAll(getDiagonalBlockedFields(figure, field));
                        }
                    }
                    allPossibleMoves.removeAll(invalidMoves);
                break;
            case "Tower":
                for(Point point  : allPossibleMoves) {
                    Field field = getKeys()[point.x][point.y];
                    if(gameBoard.get(field) != null) {
                        invalidMoves.addAll(getRowColumnBlockedFields(figure, field));
                    }
                }
                allPossibleMoves.removeAll(invalidMoves);
                break;
            case "Queen":
                for(Point point : allPossibleMoves) {
                    Field field = getKeys()[point.x][point.y];
                    if(gameBoard.get(field) != null) {
                        if (figure.getCurrentPosition().getxCoordinate() == field.getxCoordinate() || figure.getCurrentPosition().getyCoordinate() == field.getyCoordinate()) {
                            invalidMoves.addAll(getRowColumnBlockedFields(figure, field));
                        } else {
                            invalidMoves.addAll(getDiagonalBlockedFields(figure, field));
                        }
                    }
                }
                allPossibleMoves.removeAll(invalidMoves);
                break;
        }
        for(Point point : allPossibleMoves) {
            validMoves.add(getKeys()[point.x][point.y]);
        }

        return validMoves;
    }

    private ArrayList<Point> getDiagonalBlockedFields(Figure figure, Field occupiedField) {
            ArrayList<Point> blockedFields = new ArrayList<>();

            int figureX = figure.getCurrentPosition().getxCoordinate();
            int figureY = figure.getCurrentPosition().getyCoordinate();
            int blockedX = occupiedField.getxCoordinate();
            int blockedY = occupiedField.getyCoordinate();

            // Determine the direction of the blocking piece from the bishop
            int dx = Integer.signum(blockedX - figureX);
            int dy = Integer.signum(blockedY - figureY);

            // Remove all fields diagonally behind the blocking piece
            int x = blockedX + dx;
            int y = blockedY + dy;
            while (x != figureX && y != figureY && x >= 0 && y >= 0 && x < 8 && y < 8) {
                blockedFields.add(new Point(x, y));
                x += dx;
                y += dy;
            }

            // Remove the blocking piece itself
            blockedFields.add(new Point(occupiedField.getxCoordinate(), occupiedField.getyCoordinate()));
            return blockedFields;
    }

    private ArrayList<Point> getRowColumnBlockedFields(Figure figure, Field occupiedField) {
        ArrayList<Point> blockedFields = new ArrayList<>();

        int figureX = figure.getCurrentPosition().getxCoordinate();
        int figureY = figure.getCurrentPosition().getyCoordinate();
        int occupiedX = occupiedField.getxCoordinate();
        int occupiedY = occupiedField.getyCoordinate();

        // Check if the occupied field is on the same row or column as the figure
        if (figureX == occupiedX) {
            // Remove all fields on the same row as the occupied field
            for (int y = 0; y < 8; y++) {
                if (y != occupiedY) {
                    blockedFields.add(new Point(occupiedX, y));
                }
            }
        } else if (figureY == occupiedY) {
            // Remove all fields on the same column as the occupied field
            for (int x = 0; x < 8; x++) {
                if (x != occupiedX) {
                    blockedFields.add(new Point(x, occupiedY));
                }
            }
        }

        blockedFields.add(new Point(occupiedField.getxCoordinate(), occupiedField.getyCoordinate()));

        return blockedFields;
    }

}

