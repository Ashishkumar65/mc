import model.Player;
import service.TicTacToeService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input players
        String[] player1Info = scanner.nextLine().split(" ");
        String[] player2Info = scanner.nextLine().split(" ");

        Player player1 = new Player(player1Info[1], player1Info[0].charAt(0));
        Player player2 = new Player(player2Info[1], player2Info[0].charAt(0));

        TicTacToeService gameService = new TicTacToeService(player1, player2);
        gameService.initializeGame();

        boolean gameOver = false;
        while (!gameOver) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            String[] position = input.split(" ");
            int row = Integer.parseInt(position[0]);
            int col = Integer.parseInt(position[1]);

            gameOver = gameService.makeMove(row, col);
        }

        scanner.close();
    }
}
