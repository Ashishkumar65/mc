package service;
import model.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

public class GameService {
    private final Board board;
    private final List<Player> players;

    private final Random dice ;

    public GameService(Board board, List<Player> players){
        this.board = board;
        this.players = players;
        this.dice = new Random();
    }

    public void play(){
        boolean isGameWon = false;
        while(!isGameWon){
            for(Player player : players){
                int diceValue = dice.nextInt(6) + 1;
                int newPosition = player.getPosition() + diceValue;
                if(newPosition <= board.getSize()){
                    player.setPositiion(newPosition);
                    System.out.println(player.getName() + " rolled a " + diceValue + " and moved to " + player.getPosition());
                    int finalPosition = board.getFinalPosition(player.getPosition());
                    if(finalPosition != player.getPosition()){
                        player.setPositiion(finalPosition);
                        System.out.println(player.getName() + " moved to " + player.getPosition());
                    }
                    if(player.hasWon()){
                        System.out.println(player.getName() + " has won the game!");
                        isGameWon = true;
                        break;
                    }
                } else{
                    System.out.println(player.getName() + " rolled a " + diceValue + " and cannot move from " + player.getPosition());
                }
            }
        }

    }

}
