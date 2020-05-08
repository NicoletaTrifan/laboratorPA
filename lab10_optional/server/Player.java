public class Player {
    private int playerTurn;

    public Player(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerTurn=" + playerTurn +
                '}';
    }
}
