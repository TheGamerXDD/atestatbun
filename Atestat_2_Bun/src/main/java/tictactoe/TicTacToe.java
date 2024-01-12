package tictactoe;

import utils.TextIO;

import java.util.Map;

public class TicTacToe {

    private static final Map<String, Stratergy> STRATEGIES = Map.of(
            "-N", new NaiveStrategy(),
            "-S", new SmartStrategy()
    );

    public static void main(String[] args) {
        System.out.println("Nume prim jucator:");
        String name1 = TextIO.getln();

        System.out.println("Nume al doilea jucator: ");
        String name2 = TextIO.getln();

        Player player1 = name1.startsWith("-")
                ? new ComputerPlayer(Mark.OO, STRATEGIES.get(name1))
                : new HumanPlayer(name1, Mark.OO);

        Player player2 = name2.startsWith("-")
                ? new ComputerPlayer(Mark.XX, STRATEGIES.get(name2))
                : new HumanPlayer(name2, Mark.XX);

        Game game = new Game(player1, player2);
        game.start();
    }
}
