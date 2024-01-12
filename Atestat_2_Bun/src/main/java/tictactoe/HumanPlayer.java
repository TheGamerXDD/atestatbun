package tictactoe;


import utils.TextIO;

public class HumanPlayer extends Player {


    public HumanPlayer(String name, Mark mark) {
        super(name, mark);
    }

    public int determineMove(Board board) {
        String prompt = "> " + getName() + " (" + getMark().toString() + ")"
                + ", ce alegi? ";
        
        System.out.println(prompt);
        int choice = TextIO.getInt();
        
        boolean valid = board.isField(choice) && board.isEmptyField(choice);
        while (!valid) {
            System.out.println("ERROARE: locul " + choice
                    + " e ocupat deja.");
            System.out.println(prompt);
            choice = TextIO.getInt();
            valid = board.isField(choice) && board.isEmptyField(choice);
        }
        return choice;
    }

}
