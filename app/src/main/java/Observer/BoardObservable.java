package Observer;

import Pieces.Board;

public interface BoardObservable {
    void addObserver(BoardObserver observer);
    void removeObserver(BoardObserver observer);
    void notifyObservers(Board board);
}
