package service;

import model.Board;
import model.Player;

public class TicTacToeService {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public TicTacToeService(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;  // X starts first
    }

    public void initializeGame() {
        board.printBoard();
    }

    public boolean makeMove(int row, int col) {
        row--;  // Adjust for 0-based index
        col--;  // Adjust for 0-based index

        if (!isMoveValid(row, col)) {
            System.out.println("Invalid Move");
            return false;
        }

        board.makeMove(row, col, currentPlayer.getSymbol());
        board.printBoard();

        if (board.checkWin(currentPlayer.getSymbol())) {
            System.out.println(currentPlayer.getName() + " won the game");
            return true;
        }

        if (board.isBoardFull()) {
            System.out.println("Game Over");
            return true;
        }

        switchPlayer();
        return false;
    }

    private boolean isMoveValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board.isCellEmpty(row, col);
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}

