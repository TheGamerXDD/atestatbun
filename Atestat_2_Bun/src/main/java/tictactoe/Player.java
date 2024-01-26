package tictactoe;

public abstract class Player {

    private String name;
    private Mark mark;

    public Player(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public Mark getMark() {
        return mark;
    }

    public abstract int determineMove(Board board, UI viewer);

    public void makeMove(Board board, UI viewer) {
        int choice = determineMove(board, viewer);
        board.setField(choice, getMark());
    }

}
