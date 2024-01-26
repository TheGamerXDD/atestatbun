package tictactoe;

import utils.TextIO;

import java.util.Map;

public class TicTacToe {

    private static final Map<String, Stratergy> STRATEGIES = Map.of(
            "-N", new NaiveStrategy(),
            "-S", new SmartStrategy()
    );

    public static void main(String[] args) {
       boolean isGUIDecided = false;
       String viewer = "";
        while (!isGUIDecided) {
            System.out.println("Te rog alege tipul de viewer \n Scrie GUI sau TUI:");
            viewer = TextIO.getln();
            if ("GUI".equalsIgnoreCase(viewer) || "TUI".equalsIgnoreCase(viewer)) {
                isGUIDecided = true;
                break;
            }
            System.out.println("Ai scris un UI necunoscut, te rog incearca din nou");
        }

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

        Game game = new Game(player1, player2, viewer);
        game.start();
    }
}
