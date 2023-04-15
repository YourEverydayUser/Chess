package Observer;

import Pieces.Board;

/**
 * Implementation of the observer interface
 */
public interface BoardObserver {

    /**
     * Updates the board-state when a move is made
     */
    void update(Board board);
}
