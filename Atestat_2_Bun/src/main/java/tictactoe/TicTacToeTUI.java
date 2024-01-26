package tictactoe;

import utils.TextIO;

import static tictactoe.Board.DIM;

public class TicTacToeTUI implements UI{
    private Board board;
    private static final String[] NUMBERING = {" 0 | 1 | 2 ", " ---+---+---",
            " 3 | 4 | 5 ", " ---+---+---", " 6 | 7 | 8 "};
    private static final String LINE = NUMBERING[1];
    private static final String DELIM = "     ";

    TicTacToeTUI(Board board){
        this.board = board;
    }


    public String toString() {
        String s = "";
        for (int i = 0; i < DIM; i++) {
            String row = "";
            for (int j = 0; j < DIM; j++) {
                String name = board.getField(i, j).toString();
                row = row + " " + (name.equals("EMPTY") ? "  " : name) + " ";
                if (j < DIM - 1) {
                    row = row + "|";
                }
            }
            s = s + row + DELIM + NUMBERING[i * 2];
            if (i < DIM - 1) {
                s = s + "\n" + LINE + DELIM + NUMBERING[i * 2 + 1] + "\n";
            }
        }
        return s;
    }


    /**
     * Metoda folosita sa updateze jocul
     */
    @Override
    public void update() {
        System.out.println("\n situatia actuala a jocului: \n\n" + this + "\n");
    }

    @Override
    public int makeMove(String name, Mark mark) {
        String prompt = "> " + name + " (" + mark.toString() + "), ce alegi? ";

        System.out.println(prompt);
       return TextIO.getInt();
    }

    @Override
    public void moveTaken(int choice) {
        System.out.println("ERROARE: locul " + choice + " e ocupat deja.");
    }

    /**
     * Metoda folosita pentru a printa rezultatul
     *
     * @param winner - daca e NULL nu exista castigatori
     */
    @Override
    public void printResult(Player winner) {
        if (winner != null) {
            System.out.println("Jucator " + winner.getName() + " (" + winner.getMark().toString() + ") a castigat!");
        } else {
            System.out.println("Blocaj, nu e nici un castigator!");
        }
    }

    @Override
    public boolean askNewGame() {
        System.out.println("\n> Inca un joc? (y/n)?");
        return  TextIO.getBoolean();
    }
}
