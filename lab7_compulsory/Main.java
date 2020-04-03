public class Main {
   public static void main(String[] args) throws InterruptedException {
//        Board boardgame=new Board(5);
//        boardgame.boardElements();
//        System.out.println(boardgame.toString());
//        Player player1=new Player();
//        player1.setBoard(boardgame);
//        player1.setBoardTokenList();
//        Player player2=new Player();
//        player2.setBoard(boardgame);
//        player2.setBoardTokenList();
//        Thread thread1=new Thread(player1);
//        Thread thread2=new Thread(player2);
//         thread1.start();
//         thread2.start();

        Game firstGame=new Game(2, 6);
        firstGame.simulateGame();
    }
}
