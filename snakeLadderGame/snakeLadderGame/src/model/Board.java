package model;

import java.util.Map;

public class Board {
    private int size;
    private Map<Integer,Integer> ladders;
    private Map<Integer,Integer> snakes;

    public Board(int size, Map<Integer,Integer> ladders, Map<Integer,Integer> snakes){
        this.size = size;
        this.ladders = ladders;
        this.snakes = snakes;
    }

    public int getSize(){
        return size;
    }

    public int getFinalPosition(int position){
        if(ladders.containsKey(position)){
            System.out.println("Ladder! Move up from " + position +" to " + ladders.get(position));
            System.out.println("-------------------------------------------------ladder-------------ladder-------");
            return ladders.get(position);
        } else if (snakes.containsKey(position)) {
            System.out.println("Snake! Move from "+ position +" to " + snakes.get(position));
            System.out.println("-------------------------------------------------snake-------------snakes-------");
            return snakes.get(position);

        }

        return position;
    }
}
