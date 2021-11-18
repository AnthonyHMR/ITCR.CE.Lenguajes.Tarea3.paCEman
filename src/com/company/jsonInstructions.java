package com.company;

public class jsonInstructions {
    private int Ghosts;
    private int Fruits;

    public jsonInstructions(){

    }

    public jsonInstructions(int Ghosts, int Fruits) {
        this.Ghosts = Ghosts;
        this.Fruits = Fruits;
    }

    public int getGhosts() {
        return Ghosts;
    }

    public void setGhosts(int ghosts) {
        this.Ghosts = ghosts;
    }

    public int getFruits() {
        return Fruits;
    }

    public void setPastillas(int fruits) {
        Fruits = fruits;
    }
}
