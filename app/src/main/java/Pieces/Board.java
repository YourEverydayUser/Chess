package Pieces;

import Pieces.Figures.*;
import Pieces.Figures.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Board class represents the game board and is responsible for keeping track of the current board state.
 * The gameBoard is a HashMap which contains all the Fields of the board and the figures which are currently on
 * the board. If there is no figure on a field, the saved value is null. With the 2-dimensional keys Array, we can
 * access the Field values and iterate through the board.
 * @Author Fabian Kuster
 */

public class Board {

    private final HashMap<Field, Figure> gameBoard;
    private final Field[][] keys = new Field[8][8];

    public Board() {
        gameBoard = new HashMap<>();

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
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
            case "Pawn" -> validPawnMoves(figure, allPossibleMoves, invalidMoves, validMoves);
        }
        //conversion from point format to field format
        for(Point point : allPossibleMoves) {
            validMoves.add(getKeys()[point.x][point.y]);
        }

        return validMoves;
    }

    /**
     * Validates possible moves for a queen figure and removes invalid moves that are blocked by other figures on the board.
     * @param figure the queen figure for which moves are being validated
     * @param allPossibleMoves the list of all possible moves for the queen figure
     * @param invalidMoves the list of invalid moves that will be removed from the list of all possible moves
     */
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

    /**
     * This method takes a tower, a list of all the possible moves the tower can make, and an empty list to collect the invalid moves.
     * It checks if there is an occupied field in the list of all possible moves, and if so, it removes that and potentially other fields as well.
     * Then it removes all the blocked fields (i.e., fields that are obstructed by other pieces), and updates the list of all possible moves.
     * @param figure the tower
     * @param allPossibleMoves all possible moves the tower could make
     * @param invalidMoves is filled with the moves which aren't valid
     */
    private void validTowerMoves(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves) {
        for(Point point  : allPossibleMoves) {
            Field field = getKeys()[point.x][point.y];
            if(gameBoard.get(field) != null) {
                invalidMoves.addAll(getRowColumnBlockedFields(figure, field));
            }
        }
        removeBlockedFields(figure, allPossibleMoves, invalidMoves);
    }

    /**
     * This method takes a bishop, a list of all the possible moves the bishop can make, and an empty list to collect the invalid moves.
     *  It checks if there is an occupied field in the list of all possible moves, and if the piece on that field has the same color as the bishop,
     *  it removes that and potentially other fields as well. Then it removes all the blocked fields on the diagonals, and updates the list of all possible moves.
     * @param figure the bishop
     * @param allPossibleMoves all possible moves the bishop could make
     * @param invalidMoves is filled with the moves which aren't valid
     */
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

    /**
     * This method takes a pawn, a list of all the possible moves the pawn can make, and an empty list to collect the invalid moves.
     *  It first checks if there is an occupied field in the list of all possible moves, and if so, it removes that and potentially other fields as well.
     *  Then it adds the valid moves for the pawn that are not in the list of all possible moves, such as capturing an opponent's piece diagonally,
     *  and updates the list of all possible moves.
     * @param figure the pawn
     * @param allPossibleMoves all possible moves the pawn could make
     * @param invalidMoves is filled with the moves which aren't valid
     */
    private void validPawnMoves(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves, ArrayList<Field> validMoves) {
        for (Point point : allPossibleMoves) {
            Field field = getKeys()[point.x][point.y];
            if (gameBoard.get(field) != null) {
                invalidMoves.add(Field.transformFieldToPoint(field));
            }
        }
        ArrayList<Field> diag = new ArrayList<>();
        int smallX = figure.getCurrentPosition().getxCoordinate() - 1;
        int bigX = figure.getCurrentPosition().getxCoordinate() + 1;

        int smallY = figure.getCurrentPosition().getyCoordinate() - 1;
        int bigY = figure.getCurrentPosition().getyCoordinate() + 1;

        if (figure.getColor() == Color.WHITE) {
            if(bigY < 0 || bigY > 7) {
                removeBlockedFields(figure, allPossibleMoves, invalidMoves);
                return;
            } else if (smallX < 0) {
                diag.add(getKeys()[bigX][bigY]);
                validMoves.addAll(diag.stream().filter(diagonalField -> gameBoard.get(diagonalField) != null && gameBoard.get(diagonalField).getColor() != Color.WHITE).toList());
                removeBlockedFields(figure, allPossibleMoves, invalidMoves);
                return;
            } else if (bigX > 7) {
                diag.add(getKeys()[smallX][bigY]);
                validMoves.addAll(diag.stream().filter(diagonalField -> gameBoard.get(diagonalField) != null && gameBoard.get(diagonalField).getColor() != Color.WHITE).toList());
                removeBlockedFields(figure, allPossibleMoves, invalidMoves);
                return;
            } else {
                diag.add(getKeys()[smallX][bigY]);
                diag.add(getKeys()[bigX][bigY]);
                validMoves.addAll(diag.stream().filter(diagonalField -> gameBoard.get(diagonalField) != null && gameBoard.get(diagonalField).getColor() != Color.WHITE).toList());
            }
        } else {
            if(smallY < 0 || smallY > 7) {
                removeBlockedFields(figure, allPossibleMoves, invalidMoves);
                return;
            } else if (smallX < 0) {
                diag.add(getKeys()[bigX][smallY]);
                validMoves.addAll(diag.stream().filter(diagonalField -> gameBoard.get(diagonalField) != null && gameBoard.get(diagonalField).getColor() != Color.BLACK).toList());
                removeBlockedFields(figure, allPossibleMoves, invalidMoves);
                return;
            } else if (bigX > 7) {
                diag.add(getKeys()[smallX][smallY]);
                validMoves.addAll(diag.stream().filter(diagonalField -> gameBoard.get(diagonalField) != null && gameBoard.get(diagonalField).getColor() != Color.BLACK).toList());
                removeBlockedFields(figure, allPossibleMoves, invalidMoves);
                return;
            } else {
                diag.add(getKeys()[smallX][smallY]);
                diag.add(getKeys()[bigX][smallY]);
                validMoves.addAll(diag.stream().filter(diagonalField -> gameBoard.get(diagonalField) != null && gameBoard.get(diagonalField).getColor() != Color.BLACK).toList());
            }
        }
        removeBlockedFields(figure, allPossibleMoves, invalidMoves);
    }

    /**
     * This method takes a Figure, a list of all possible moves, and a list of invalid moves,
     * and removes all the invalid moves and blocked fields from the list of all possible moves.
     * @param figure the figure
     * @param allPossibleMoves all the possible moves
     * @param invalidMoves the invalid moves
     */
    private void removeBlockedFields(Figure figure, ArrayList<Point> allPossibleMoves, ArrayList<Point> invalidMoves) {
        allPossibleMoves.removeAll(invalidMoves);
        invalidMoves.clear();
        invalidMoves.addAll(
            allPossibleMoves.stream()
                    .map(point -> getKeys()[point.x][point.y])
                    .filter(field -> isPathBlocked(figure.getCurrentPosition(), field))
                    .map(Field::transformFieldToPoint)
                    .toList());
        allPossibleMoves.removeAll(invalidMoves);
    }

    /**
     * This method takes a Figure and an occupied field (i.e., a field that is obstructed by another piece)
     * and returns a list of all the fields on the diagonals that are blocked by the occupying piece.
     * @param figure the figure
     * @param occupiedField the occupied field on the same diagonal as the figure itself
     * @return ArrayList<Point> blockedFields / a list which contains all the blocked fields
     */
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
        if(!isPathBlocked(figure.getCurrentPosition(), occupiedField) &&
                figure.getColor() == gameBoard.get(occupiedField).getColor()) {
            blockedFields.add(new Point(occupiedField.getxCoordinate(), occupiedField.getyCoordinate()));
        }
        return blockedFields;
    }

    /**
     * This method takes a Figure and an occupied field (i.e., a field that is obstructed by another piece)
     * and returns a list of all the fields on the same x- or y-axis that are blocked by the occupying piece.
     * @param figure the figure
     * @param occupiedField the occupied field on the same x- or y-axis as the figure itself
     * @return ArrayList<Point> blockedFields / a list which contains all the blocked fields
     */
    private ArrayList<Point> getRowColumnBlockedFields(Figure figure, Field occupiedField) {
        ArrayList<Point> blockedFields = new ArrayList<>();

        int figureX = figure.getCurrentPosition().getxCoordinate();
        int figureY = figure.getCurrentPosition().getyCoordinate();
        int occupiedX = occupiedField.getxCoordinate();
        int occupiedY = occupiedField.getyCoordinate();

        //if the path is to the target is blocked just remove the target and the rest doesn't need to be removed.
        if(isPathBlocked(figure.getCurrentPosition(), occupiedField)) {
            blockedFields.add(Field.transformFieldToPoint(getKeys()[occupiedX][occupiedY]));
            return blockedFields;
        }

        // Check if the figure is from the opposing player, if so do nothing
        if (figure.getColor() != gameBoard.get(occupiedField).getColor()) {
            return blockedFields;
        }

        // Check if the occupied field is on the same row or column as the figure
        if (figureX == occupiedX) {
            if (occupiedY > figureY) {
                for (int y = occupiedY; y < 8; y++) {
                    blockedFields.add(Field.transformFieldToPoint(keys[occupiedX][y]));
                }
            }
            else {
                for (int y = occupiedY; y >= 0; y--) {
                    blockedFields.add(Field.transformFieldToPoint(keys[occupiedX][y]));
                }
            }
        } else {
            if (occupiedX > figureX) {
                for (int x = occupiedX; x < 8; x++) {
                    blockedFields.add(Field.transformFieldToPoint(keys[x][occupiedY]));
                }
            }
            else {
                for (int x = occupiedX; x >= 0; x--) {
                    blockedFields.add(Field.transformFieldToPoint(keys[x][occupiedY]));
                }
            }
        }
        return blockedFields;
    }

    /**
     *  Checks if there is any figure blocking the path between two fields on the game board.
     *  @param from the starting field of the path
     *  @param to the end field of the path
     *  @return true if there is a figure blocking the path, false otherwise
     */
    private boolean isPathBlocked(Field from, Field to) {
        int deltaX = Integer.compare(to.getxCoordinate(), from.getxCoordinate());
        int deltaY = Integer.compare(to.getyCoordinate(), from.getyCoordinate());

        int x = from.getxCoordinate() + deltaX;
        int y = from.getyCoordinate() + deltaY;

        while (x != to.getxCoordinate() || y != to.getyCoordinate()) {
            Field field = getKeys()[x][y];
            if (gameBoard.get(field) != null) {
                return true;
            }
            x += deltaX;
            y += deltaY;
        }
        return false;
    }
}
