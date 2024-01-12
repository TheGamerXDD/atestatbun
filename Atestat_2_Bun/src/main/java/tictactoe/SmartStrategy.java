package tictactoe;

import java.util.stream.IntStream;

public class SmartStrategy implements Stratergy {
    @Override
    public String getName() {
        return "smart";
    }

    @Override
    public int determineMove(Board board, Mark mark) {
        int middle = (Board.DIM * Board.DIM - 1) / 2;
        if (board.isEmptyField(middle)) {
            return middle;
        }

        int win = win(board, mark);
        if (win != -1) {
            return win;
        }

        int defend = win(board, mark.other());
        if (defend != -1) {
            return defend;
        }

        return selectRandom(board);
    }

    private int win(Board board, Mark mark) {
        Board tempBoard = board.deepCopy();
        for (int i = 0; i < Board.DIM * Board.DIM; i++) {
            if (!tempBoard.isEmptyField(i)) {
                continue;
            }

            tempBoard.setField(i, mark);
            if (tempBoard.isWinner(mark)) {
                return i;
            }

            tempBoard.setField(i, Mark.EMPTY);
        }

        return -1;
    }

    private int selectRandom(Board board) {
        var empty = IntStream.range(0, Board.DIM * Board.DIM)
                .filter(board::isEmptyField)
                .toArray();

        int idx = (int) (Math.random() * (empty.length - 1));
        return empty[idx];
    }
}
