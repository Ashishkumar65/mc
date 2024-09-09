package model;

public class Board {
    private final char[][] grid;
    private final int size;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == '-';
    }

    public void makeMove(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    public boolean checkWin(char symbol) {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkRow(i, symbol) || checkColumn(i, symbol)) {
                return true;
            }
        }
        // Check diagonals
        return checkMainDiagonal(symbol) || checkAntiDiagonal(symbol);
    }

    private boolean checkRow(int row, char symbol) {
        for (int col = 0; col < size; col++) {
            if (grid[row][col] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, char symbol) {
        for (int row = 0; row < size; row++) {
            if (grid[row][col] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMainDiagonal(char symbol) {
        for (int i = 0; i < size; i++) {
            if (grid[i][i] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal(char symbol) {
        for (int i = 0; i < size; i++) {
            if (grid[i][size - i - 1] != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
