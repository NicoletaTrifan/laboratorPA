public class Board {
    private int[][] board = new int[16][16];

    public Board() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int boardValue(int x, int y) {
        return board[x][y];
    }

    public void putPieceOnBoard(int i, int j, int turn) {
        board[i][j] = turn;
    }

    public boolean verifyWinner(int i, int j, int turn) {
        int left = j;
        int right = j;
        int top = i;
        int bottom = i;

        while (right < 15 && right > 0 && board[i][right] == turn) {
            right++;
        }
        while (left < 15 && left > 0 && board[i][left] == turn) {
            left--;
        }
        if (right - left > 5) {
            return true;
        }
        return false;
    }
}
