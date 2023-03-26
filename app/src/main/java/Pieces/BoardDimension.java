package Pieces;

public enum BoardDimension {

    XDIMENSION(7),
    YDIMENSION(7);

    private int value;

    BoardDimension(int i) {
        value = i;
    }

    public int getValue(){
        return value;
    }
}
