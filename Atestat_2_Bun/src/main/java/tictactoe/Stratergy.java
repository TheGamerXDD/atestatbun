package tictactoe;

public interface Stratergy {
    String getName();
    int determineMove(Board board, Mark mark);
}
