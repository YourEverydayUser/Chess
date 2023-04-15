package Pieces;

import Pieces.Figures.*;

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
        ArrayList<Field> allPossibleMoves = figure.getPossibleMoves();

        switch(figure.getName()) {
            case "Knight", "King":
                allPossibleMoves.removeIf(field -> gameBoard.get(field) != null);
                break;
            case "Bishop":
                for (Field field : allPossibleMoves) {
                    if (gameBoard.get(field) != null) {
                        allPossibleMoves.removeAll(getDiagonalBlockedFields(figure, field));
                    }
                }
                break;
            case "Tower":
                for(Field field : allPossibleMoves) {
                    if(gameBoard.get(field) != null) {
                        allPossibleMoves.removeAll(getRowColumnBlockedFields(figure, field));
                    }
                }
                break;
            case "Queen":
                for(Field field : allPossibleMoves) {
                    if(gameBoard.get(field) != null) {
                        if (figure.getCurrentPosition().getxCoordinate() == field.getxCoordinate() || figure.getCurrentPosition().getyCoordinate() == field.getyCoordinate()) {
                            allPossibleMoves.removeAll(getRowColumnBlockedFields(figure, field));
                        } else {
                            allPossibleMoves.removeAll(getDiagonalBlockedFields(figure, field));
                        }
                    }
                }
        }
        return null;
    }

    private ArrayList<Field> getDiagonalBlockedFields(Figure figure, Field occupiedField) {
            ArrayList<Field> blockedFields = new ArrayList<>();

            int figureX = figure.getCurrentPosition().getxCoordinate();
            int figureY = figure.getCurrentPosition().getyCoordinate();
            int blockedX = occupiedField.getxCoordinate();
            int blockedY = occupiedField.getyCoordinate();

            // Determine the direction of the blocking piece from the bishop
            int dx = Integer.signum(figureX - blockedX);
            int dy = Integer.signum(figureY - blockedY);

            // Remove all fields diagonally behind the blocking piece
            int x = blockedX + dx;
            int y = blockedY + dy;
            while (x != figureX && y != figureY) {
                blockedFields.add(new Field(x, y));
                x += dx;
                y += dy;
            }

            // Remove the blocking piece itself
            blockedFields.add(occupiedField);
            return blockedFields;
    }

    private ArrayList<Field> getRowColumnBlockedFields(Figure figure, Field occupiedField) {
        ArrayList<Field> blockedFields = new ArrayList<>();

        int figureX = figure.getCurrentPosition().getxCoordinate();
        int figureY = figure.getCurrentPosition().getyCoordinate();
        int occupiedX = occupiedField.getxCoordinate();
        int occupiedY = occupiedField.getyCoordinate();

        // Check if the occupied field is on the same row or column as the figure
        if (figureX == occupiedX) {
            // Remove all fields on the same row as the occupied field
            for (int y = 0; y < 8; y++) {
                if (y != occupiedY) {
                    blockedFields.add(new Field(occupiedX, y));
                }
            }
        } else if (figureY == occupiedY) {
            // Remove all fields on the same column as the occupied field
            for (int x = 0; x < 8; x++) {
                if (x != occupiedX) {
                    blockedFields.add(new Field(x, occupiedY));
                }
            }
        }

        blockedFields.add(occupiedField);

        return blockedFields;
    }

}

