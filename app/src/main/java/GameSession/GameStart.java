package GameSession;

import Pieces.Board;
import Pieces.Field;
import Pieces.Figures.King;

public class GameStart {

    private Board gameBoard;

    public GameStart() {
        gameBoard = new Board();
        int counter = 0;
        for(Field[] row : gameBoard.getGameBoard()) {
            for(Field field : row) {
                if(counter % 32==3)
                    field.setFigure(new King(field, true));
                counter++;
            }
        }
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void printGameBoard(Board board) {
        for(Field[] row : board.getGameBoard()) {
            for(Field field : row) {
                if(field.getFigure() != null) {
                    System.out.print("   K   ");
                } else {
                    System.out.print("   .   ");
                }
            }
            System.out.println("\n");
        }
    }
}
