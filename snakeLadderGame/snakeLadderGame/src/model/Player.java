package model;

public class Player {
    String name;
    int positiion;
    public Player(String name, int position) {
        this.name = name;
        this.positiion = position;
    }
    public String getName() {
        return name;
    }
    public int getPosition() {
        return positiion;
    }
    public void setPositiion(int position) {
        this.positiion = position;
    }

    public boolean hasWon(){
        return positiion == 100;
    }
}
