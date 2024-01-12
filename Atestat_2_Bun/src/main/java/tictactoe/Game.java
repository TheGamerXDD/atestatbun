package tictactoe;

import utils.TextIO;

public class Game {
    public static final int NUMBER_PLAYERS = 2;

    private Board board;

    private Player[] players;

    private int current;

    public Game(Player s0, Player s1) {
        board = new Board();
        players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
        current = 0;
    }


    public void start() {
        boolean continueGame = true;
        while (continueGame) {
            reset();
            play();
            System.out.println("\n> Inca un joc? (y/n)?");
            continueGame = TextIO.getBoolean();
        }
    }


    private void reset() {
        current = 0;
        board.reset();
    }


    private void play() {
        System.out.println(board.toString());

        while (!board.hasWinner() && !board.isFull()) {
            players[current].makeMove(board);

            current = current == 1 ? 0 : 1;
            update();
        }

        printResult();
    }


    private void update() {
        System.out.println("\nsituatia actuala a jocului: \n\n" + board.toString()
                + "\n");
    }


    private void printResult() {
        if (board.hasWinner()) {
            Player winner = board.isWinner(players[0].getMark()) ? players[0] : players[1];
            System.out.println("Jucator " + winner.getName() + " (" + winner.getMark().toString() + ") a castigat!");
        } else {
            System.out.println("Blocaj, nu e nici un castigator!");
        }
    }
}
