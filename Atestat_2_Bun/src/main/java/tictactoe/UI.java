package tictactoe;

public interface UI {
    /**
     * Metoda folosita sa updateze jocul
     */
     public void update();

     public int makeMove(String name, Mark mark);

     public void moveTaken(int choice);

    /**
     * Metoda folosita pentru a printa rezultatul
     * @param winner - daca e NULL nu exista castigatori
     */
    public void printResult(Player winner);


    public boolean askNewGame();
}
