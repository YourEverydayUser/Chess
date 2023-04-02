package Pieces;

public enum BoardDimension {

    XDIMENSION(8),
    YDIMENSION(8);

    private int value;

    BoardDimension(int i) {
        value = i;
    }

    public int getValue(){
        return value;
    }
}
