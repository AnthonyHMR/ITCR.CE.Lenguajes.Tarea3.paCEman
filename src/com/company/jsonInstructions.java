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

    /**
     * Obtiene la cantidad de fantasmas
     * @return
     */
    public int getGhosts() {
        return Ghosts;
    }

    /**
     * Brinda un numero de fantasmas
     * @param ghosts cantidad de fantasmas a añadir
     */
    public void setGhosts(int ghosts) {
        this.Ghosts = ghosts;
    }

    /**
     * Obtiene la cantidad de frutas
     * @return
     */
    public int getFruits() {
        return Fruits;
    }

    /**
     * Brinda el numero de frutas
     * @param fruits cantidad de frutas a añadir
     */
    public void setPastillas(int fruits) {
        Fruits = fruits;
    }
}
