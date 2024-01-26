package tictactoe;


import utils.TextIO;

public class HumanPlayer extends Player {


    public HumanPlayer(String name, Mark mark) {
        super(name, mark);
    }

    public int determineMove(Board board, UI viewer) {
        int choice = viewer.makeMove(getName(), getMark());
        boolean valid = board.isField(choice) && board.isEmptyField(choice);
        while (!valid) {
            viewer.moveTaken(choice);
            choice = viewer.makeMove(getName(), getMark());
            valid = board.isField(choice) && board.isEmptyField(choice);
        }
        return choice;
    }

}
