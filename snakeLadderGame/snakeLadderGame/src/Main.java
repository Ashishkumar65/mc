import model.Board;
import model.Player;
import service.GameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer,Integer> ladders = new HashMap<>();
        ladders.put(6,25);
        ladders.put(11,40);
        ladders.put(60,85);
        ladders.put(46,90);
        ladders.put(17,69);

        // create snakkes map
        Map<Integer,Integer> snakes = new HashMap<>();
        snakes.put(99,54);
        snakes.put(70,55);
        snakes.put(52,42);
        snakes.put(25,2);
        snakes.put(95,72);

        Board board = new Board(100,ladders,snakes);
        List<Player> plaayers = new ArrayList<>();
        plaayers.add(new Player("Ashish",0));
        plaayers.add(new Player("Raushan",0));

        GameService gameService = new GameService(board,plaayers);
        gameService.play();
    }
}