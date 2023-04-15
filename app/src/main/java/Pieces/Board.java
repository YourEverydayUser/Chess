package Pieces;

import Pieces.Figures.*;
import Pieces.Figures.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import static Pieces.BoardDimension.XDIMENSION;
import static Pieces.BoardDimension.YDIMENSION;

/**
 * The Board class represents the game board and is responsible for keeping track of the current board state.
 * The gameBoard is a HashMap which contains all the Fields of the board and the figures which are currently on
 * the board. If there is no figure on a field, the saved value is null. With the 2-dimensional keys Array, we can
 * access the Field values and iterate through the board.
 * @Author Fabian Kuster
 */

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

    /**
     * Returns the gameBoard
     * @return HashMap<Field, Figure> gameBoard
     */
    public HashMap<Field, Figure> getGameBoard() {
        return gameBoard;
    }

    /**
     * Returns the keys for the gameBoard
     * @return Field[][] keys
     */
    public Field[][] getKeys() {
        return keys;
    }

    /**
     * Takes a figure and calculates all the valid moves. Since this is dependent
     * on the current board-state the check for validity of all the possible
     * moves for a figure takes place here.
     * @param figure the figure which wants to be moves
     * @return ArrayList<Field> a list with all the valid moves
     */
    public ArrayList<Field> getValidMoves(Figure figure) {
        ArrayList<Point> allPossibleMoves = figure.calculatePossibleMoves(figure.getCurrentPosition());
        ArrayList<Point> invalidMoves = new ArrayList<>();
        ArrayList<Field> validMoves = new ArrayList<>();

        switch (figure.getName()) {
            case "Knight", "King" -> allPossibleMoves.removeIf(field -> gameBoard.get(getKeys()[field.x][field.y]) != null &&
                    gameBoard.get(getKeys()[field.x][field.y]).getColor() == figure.getColor());
            case "Bishop" -> validBishopMoves(figure, allPossibleMoves, invalidMoves);
            case "Tower" -> validTowerMoves(figure, allPossibleMoves, invalidMoves);
            case "Queen" -> validQueenMoves(figure, allPossibleMoves, invalidMoves);
            case "Pawn" -> validPeasentMoves(figure, allPossibleMoves, invalidMoves, validMoves);
        }
        //conversion from point format to field format
        for(Point point : allPossibleMoves) {
            validMoves.add(getKeys()[point.x][point.y]);
        }

        return validMoves;
    }

    private void validQueenMoves(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves) {
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
        removeBlockedFields(figure, allPossibleMoves, invalidMoves);
        allPossibleMoves.removeAll(invalidMoves);
    }

    private void validTowerMoves(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves) {
        for(Point point  : allPossibleMoves) {
            Field field = getKeys()[point.x][point.y];
            if(gameBoard.get(field) != null) {
                invalidMoves.addAll(getRowColumnBlockedFields(figure, field));
            }
        }
        removeBlockedFields(figure, allPossibleMoves, invalidMoves);
    }

    private void validBishopMoves(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves) {
        for (Point point : allPossibleMoves) {
            Field field = getKeys()[point.x][point.y];
            if (gameBoard.get(field) != null &&
                    gameBoard.get(field).getColor() == figure.getColor()) {
                invalidMoves.addAll(getDiagonalBlockedFields(figure, field));
            }
        }
        removeBlockedFields(figure, allPossibleMoves, invalidMoves);
    }

    private void validPeasentMoves(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves, ArrayList<Field> validMoves) {
        for (Point point : allPossibleMoves) {
            Field field = getKeys()[point.x][point.y];
            if (gameBoard.get(field) != null) {
                invalidMoves.addAll(getRowColumnBlockedFields(figure, field));
            }
        }
        ArrayList<Field> diag = new ArrayList<>();
        try {
            if (figure.getColor() == Color.WHITE) {
                diag.add(getKeys()[figure.getCurrentPosition().getxCoordinate() - 1][figure.getCurrentPosition().getyCoordinate() + 1]);
                diag.add(getKeys()[figure.getCurrentPosition().getxCoordinate() + 1][figure.getCurrentPosition().getyCoordinate() + 1]);
                for (Field field : diag) {
                    if (gameBoard.get(field) != null && gameBoard.get(field).getColor() != Color.WHITE) {
                        validMoves.add(field);
                    }
                }
            } else {
                diag.add(getKeys()[figure.getCurrentPosition().getxCoordinate() - 1][figure.getCurrentPosition().getyCoordinate() - 1]);
                diag.add(getKeys()[figure.getCurrentPosition().getxCoordinate() + 1][figure.getCurrentPosition().getyCoordinate() - 1]);
                for (Field field : diag) {
                    if (gameBoard.get(field) != null && gameBoard.get(field).getColor() != Color.BLACK) {
                        validMoves.add(field);
                    }
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e){

        }
    }


    private void removeBlockedFields(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves) {
        allPossibleMoves.removeAll(invalidMoves);
        invalidMoves.clear();
        for (Point point : allPossibleMoves) {
            Field field = getKeys()[point.x][point.y];
            if (isPathBlocked(figure.getCurrentPosition(), field)) {
                invalidMoves.add(point);
            }
        }
        allPossibleMoves.removeAll(invalidMoves);
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

    private boolean isPathBlocked(Field from, Field to) {
        int deltaX = Integer.compare(to.getxCoordinate(), from.getyCoordinate());
        int deltaY = Integer.compare(to.getxCoordinate(), from.getyCoordinate());

        int x = from.getxCoordinate() + deltaX;
        int y = from.getyCoordinate() + deltaY;

        while (x != to.getxCoordinate() || y != to.getyCoordinate()) {
            Field field = getKeys()[x][y];
            if (gameBoard.get(field) != null) {
                return true; // A figure is blocking the path
            }
            x += deltaX;
            y += deltaY;
        }
        return false; // The path is not blocked
    }
}
