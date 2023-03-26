package Pieces;

public enum BoardDimension {

    XDIMENSION(7),
    YDIMENSION(7);

    BoardDimension(int i) {

    }

    public int getValue(){
        return XDIMENSION.getValue();
    }
}
