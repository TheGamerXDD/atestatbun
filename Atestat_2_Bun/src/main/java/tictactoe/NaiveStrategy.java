package tictactoe;

import java.util.stream.IntStream;

public class NaiveStrategy implements Stratergy {

    @Override
    public String getName() {
        return "naive";
    }

    @Override
    public int determineMove(Board board, Mark mark) {
        var empty = IntStream.range(0, Board.DIM * Board.DIM)
                .filter(board::isEmptyField)
                .toArray();

        int idx = (int) (Math.random() * (empty.length - 1));
        return empty[idx];
    }
}
