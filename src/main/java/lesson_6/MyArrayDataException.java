package lesson_6;

public class MyArrayDataException extends RuntimeException {
    private final int row;
    private final int col;

    public MyArrayDataException(int row, int col, String value) {
        super("Invalid data at position [" + row + "][" + col + "]: '" + value + "'");
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
