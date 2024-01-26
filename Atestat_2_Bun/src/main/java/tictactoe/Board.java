package tictactoe;


public class Board {
    public static final int DIM = 3;

    private Mark[] fields;

    public Board() {
    	fields = new Mark[DIM * DIM];
    	reset();
    }


    public Board deepCopy() {
    	Board copy = new Board();
        for (int i = 0; i < this.fields.length; i++) {
            copy.setField(i, getField(i));
        }

        return copy;
    }


    public int index(int row, int col) {
        assert isField(row, col);

        return row * DIM + col;
    }


    public boolean isField(int index) {
        return 0 <= index && index < DIM * DIM;
    }


    public boolean isField(int row, int col) {
        return 0 <= row && row < DIM && 0 <= col && col < DIM;
    }


    public Mark getField(int i) {
        assert isField(i);

    	return fields[i];
    }


    public Mark getField(int row, int col) {
        assert isField(row, col);

    	return getField(index(row, col));
    }


    public boolean isEmptyField(int i) {
        assert isField(i);

    	return getField(i) == Mark.EMPTY;
    }


    public boolean isEmptyField(int row, int col) {
        assert isField(row, col);

    	return getField(row, col) == Mark.EMPTY;
    }


    public boolean isFull() {
    	for (int x = 0; x < DIM; x++) {
    	    for (int y = 0; y < DIM; y++) {
                if (isEmptyField(x, y)) {
                    return false;
                }
            }
        }

    	return true;
    }


    public boolean gameOver() {
        return hasWinner() || isFull();
    }


    public boolean hasRow(Mark m) {
    	boolean result = false;
        for (int x = 0; x < DIM; x++) {
            boolean row = true;
            for (int y = 0; y < DIM; y++) {
                row &= getField(x, y) == m;
            }

            result |= row;
        }
        return result;
    }


    public boolean hasColumn(Mark m) {
        boolean result = false;
        for (int y = 0; y < DIM; y++) {
            boolean row = true;
            for (int x = 0; x < DIM; x++) {
                row &= getField(x, y) == m;
            }

            result |= row;
        }
        return result;
    }


    public boolean hasDiagonal(Mark m) {
        boolean diagonal1 = true;
        boolean diagonal2 = true;

        for (int i = 0; i < DIM; i++) {
            diagonal1 &= m == getField(i, i);
            diagonal2 &= m == getField(i, DIM - i - 1);
        }

        return diagonal1 || diagonal2;
    }


    public boolean isWinner(Mark m) {
        return hasColumn(m) || hasRow(m) || hasDiagonal(m);
    }


    public boolean hasWinner() {
        return isWinner(Mark.OO) || isWinner(Mark.XX);
    }





    public void reset() {
    	for (int i = 0; i < DIM * DIM; i++) {
    	    setField(i, Mark.EMPTY);
        }
    }


    public void setField(int i, Mark m) {
    	fields[i] = m;
    }


    public void setField(int row, int col, Mark m) {
    	setField(index(row, col), m);
    }
}
