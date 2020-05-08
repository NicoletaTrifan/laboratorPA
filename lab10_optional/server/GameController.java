import java.util.ArrayList;
import java.util.*;

public class GameController {
    private static List<Game> games = new ArrayList<>();
    private static List<Integer> gameIdList = new ArrayList<>();
    private static Map<Integer, ArrayList<Object>> gamePlayerCounter = new HashMap<Integer, ArrayList<Object>>();
    public static Game currentGame;

    public static int playerId   = 1; // fiecarui client ii asociez cate un id, de care tinem cond la gameplay
    public static int turn       = 1;

    public static int player1NoOfMoves = 0;
    public static int player2NoOfMoves = 0;

    public static Game getGameById(int id) {
        for (int i = 0; i < games.size(); ++i) {
            if (id == games.get(i).getId()) {
                return games.get(i);
            }
        }
        return null;

    }
    public static void addGame(int id) {
        Game newGame = new Game(id);
        games.add(newGame);
    }

    public static void addGameId(int gameId, int threadId) {
        gameIdList.add(gameId);
        if (gamePlayerCounter.containsKey(gameId)) {
            gamePlayerCounter.get(gameId).add(threadId);
        }
        else {
            gamePlayerCounter.put(gameId, new ArrayList<>());
            gamePlayerCounter.get(gameId).add(threadId);
        }
    }

    public static boolean isValidId(int id) {
        // daca id este deja in lista, playerul va fi nevoit sa faca join la joc, pentru ca el exista deja
        // sau va fi nevoit sa creeze un joc cu un alt id
        return !gameIdList.contains(id);
    }

    public static boolean isValidForJoin(int id) {
        // cand am un singur player in room si mai vine unul o sa fie 2, deci se pot juca
        if (gamePlayerCounter.containsKey(id)) {
            return gamePlayerCounter.get(id).size() < 2;
        }
        return false;
    }

    public static void markAsJoinedForBeggingToPlay(int gameId, int threadId) {
        gamePlayerCounter.get(gameId).add(threadId);
    }

    public static boolean areValidCoordonates(int x, int y) {
        return x >= 0 && x <= 15 && y >= 0 && y <= 15;
    }

    public static boolean areWeReadyToPlay(int id) {
        if (gamePlayerCounter.containsKey(id)) {
            return gamePlayerCounter.get(id).size() == 2;
        }
        return false;
    }

    public static void submitPlayer1Move(int gameId, int x, int y) {
        // pentru jucatorul1 al jocului curent adaug coordonatele sale
        currentGame.player1Moves_x.add(x);
        currentGame.player1Moves_y.add(y);
        player1NoOfMoves++;
    }

    public static  void submitPlayer2Move(int gameId, int x, int y) {
        // pentru jucatorul2 al jocului curent adaug coordonatele sale
        currentGame.player2Moves_x.add(x);
        currentGame.player2Moves_y.add(y);
        player2NoOfMoves++;
    }

    public static boolean isValidMove(int id, int x, int y) {
        if (areValidCoordonates(x, y)) {
            for (int i = 0; i < player1NoOfMoves; ++i) {
                if (currentGame.player1Moves_x.get(i) == x && currentGame.player1Moves_y.get(i) == y) {
                    return false;
                }
            }
            for (int i = 0; i < player2NoOfMoves; ++i) {
                if (currentGame.player2Moves_x.get(i) == x && currentGame.player2Moves_y.get(i) == y) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
