package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener, UI {

    private Board board;
    private JButton[][] buttons;

    final Object o = new Object();

    private int lastMove = 0;
    private boolean isPlayerX;
    private int moves;

    public TicTacToeGUI(Board board) {
        this.board = board;
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        isPlayerX = true;
        moves = 0;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].setBackground(Color.GRAY);
                buttons[i][j].setForeground(Color.BLACK);
              //  buttons[i][j].setPressedBackgroundColor(Color.PINK);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        for(int i=0; i< Board.DIM; i++)
        {
            for(int j=0; j<Board.DIM; j++)
            {
                if(buttons[i][j].equals(button))
                {
                    synchronized (o){
                        lastMove = board.index(i, j);
                        o.notify();
                        return;
                    }

                }
            }
        }

//        board.hasWinner();
//
//        if (button.getText().isEmpty()) {
//            if (isPlayerX) {
//                button.setText("X");
//            } else {
//                button.setText("O");
//            }
//
//            moves++;
//            isPlayerX = !isPlayerX;
//
//            if (checkWin()) {
//                String winner = isPlayerX ? "Player 0" : "Player X";
//                JOptionPane.showMessageDialog(this, winner + " wins!");
//                resetGame();
//            } else if (moves == 9) {
//                JOptionPane.showMessageDialog(this, "It's a draw!");
//                resetGame();
//            }
//        }
    }

    private boolean checkWin() {
        String[][] board = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].isEmpty()) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j].equals(board[1][j]) && board[0][j].equals(board[2][j]) && !board[0][j].isEmpty()) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].isEmpty()) {
            return true;
        }

        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].isEmpty()) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        isPlayerX = true;
        moves = 0;
    }

    /**
     * Metoda folosita sa updateze jocul
     */
    @Override
    public void update() {
        for(int i =0; i< Board.DIM; i++)
        {
            for(int  j=0; j <Board.DIM ;j++){
                buttons[i][j].setText(board.getField(i, j).toString());
            }
        }
    }

    @Override
    public int makeMove(String name, Mark mark) {
        synchronized(o) {
            try {
                o.wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }

        return this.lastMove;
    }

    @Override
    public void moveTaken(int choice) {
        int i = choice / Board.DIM;
        int j = choice % Board.DIM;
        buttons[i][j].setBackground(Color.RED);
        buttons[i][j].setForeground(Color.BLACK);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        buttons[i][j].setBackground(Color.GRAY);
        buttons[i][j].setForeground(Color.BLACK);
//        System.out.println("AI FACUT O EROARE BAI BAIATULE");
    }

    /**
     * Metoda folosita pentru a printa rezultatul
     *
     * @param winner - daca e NULL nu exista castigatori
     */
    @Override
    public void printResult(Player winner) {
        if(winner == null)
        {
            //Caz in care nu e castigator
        }
    }

    @Override
    public boolean askNewGame() {
        return false;
    }
}