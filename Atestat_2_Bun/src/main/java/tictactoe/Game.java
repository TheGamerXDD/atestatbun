package tictactoe;

public class Game {
    public static final int NUMBER_PLAYERS = 2;

    private UI viewer;

    private Board board;

    private Player[] players;

    private int current;

    public Game(Player s0, Player s1, String viewer) {
        board = new Board();
        players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
        current = 0;

        this.viewer = viewer.equalsIgnoreCase("GUI") ? new TicTacToeGUI(board) : new TicTacToeTUI(board);
    }


    public void start() {
        boolean continueGame = true;
        while (continueGame) {
            reset();
            play();
            continueGame = viewer.askNewGame();
        }
    }


    private void reset() {
        current = 0;
        board.reset();
    }


    private void play() {
        viewer.update();

        while (!board.gameOver()) {
            players[current].makeMove(board, viewer);

            current = current == 1 ? 0 : 1;
            viewer.update();
        }

        viewer.printResult(getWinner());
    }

    private Player getWinner()
    {
        if(!board.hasWinner())  {
            return null;
        }
       return board.isWinner(players[0].getMark()) ? players[0] : players[1];
    }
}
