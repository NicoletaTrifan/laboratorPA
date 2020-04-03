import static java.awt.desktop.UserSessionEvent.Reason.LOCK;

public class Game {
    private Board board;
    private int numberOfPlayers;
    private int maximumNumberOfTOken;

    public Game(int numberOfPlayers, int maximumNumberOfTOken) {
        this.numberOfPlayers = numberOfPlayers;
        this.maximumNumberOfTOken=maximumNumberOfTOken;
        board=new Board(maximumNumberOfTOken);
        board.boardElements();
    }
    public void simulateGame() throws InterruptedException {
         System.out.println(board);
        Player[] players=new Player[numberOfPlayers];
        for (int i=0; i<numberOfPlayers; i++){
            players[i]=new Player();
            players[i].setBoard(board);
            System.out.println(players[i].getName());
        }
        Thread[] threads= new Thread[numberOfPlayers];
        for (int i=0; i<numberOfPlayers; i++){
            threads[i]=new Thread(players[i]);
        }
        for (int i=0; i<numberOfPlayers; i++){
            threads[i].start();
        }
        for (int i=0; i<numberOfPlayers; i++){
            threads[i].join();
        }
        for (int i=0; i<numberOfPlayers; i++){
            System.out.println(players[i].toString());
        }
    }
}
