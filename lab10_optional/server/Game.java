import java.util.*;

public class Game {
    private int nrOfPlayers;
    private int player1Id;
    private int player2Id;
    private Board board;
    private int id;

    public static List<Integer> player1Moves_x = new ArrayList<>();
    public static List<Integer> player1Moves_y = new ArrayList<>();

    public static List<Integer> player2Moves_x = new ArrayList<>();
    public static List<Integer> player2Moves_y = new ArrayList<>();
    public static int noOfMoves = 0;

    public Game(int id) {
        this.id = id;
        board = new Board();
    }

    public int getId() {
        return id;
    }
}
