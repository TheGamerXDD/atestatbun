package tictactoe;

public class ComputerPlayer extends Player {
    private Stratergy strategy;

    private ComputerPlayer(String name, Mark mark) {
        super(name, mark);
    }

    public ComputerPlayer(Mark mark, Stratergy stratergy) {
        this(stratergy.getName() + "-computer-" + mark.name(), mark);
        this.strategy = stratergy;
    }

    public ComputerPlayer(Mark mark) {
        this(mark, new NaiveStrategy());
    }

    @Override
    public int determineMove(Board board) {
        return this.strategy.determineMove(board, getMark());
    }
}
