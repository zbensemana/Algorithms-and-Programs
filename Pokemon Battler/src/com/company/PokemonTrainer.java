package com.company;

import java.util.ArrayList;
import com.company.Pokemon;


public class PokemonTrainer {

    private String name;
    private int win;
    private ArrayList<Pokemon> team;

    public PokemonTrainer(String name, ArrayList<Pokemon> team) {
        this.name = name;
        this.team = team;
    }

    // Getters and setters below. Appropriate toString method for better printing.

    public String toString(){
        String s = ("\n" + "\n" + "Trainer: " + name + ", Wins: " + win + ", team:[\n" + team.get(0) + ",\n" + team.get(1) + ",\n" + team.get(2) + ",\n"
         + team.get(3) + ",\n" + team.get(4) + "]");

        return (s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public ArrayList<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Pokemon> team) {
        this.team = team;
    }
}
