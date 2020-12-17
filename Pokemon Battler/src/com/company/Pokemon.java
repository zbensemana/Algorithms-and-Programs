package com.company;

import java.util.Random;
import java.util.ArrayList;


public class Pokemon {
	private Random rand = new Random(10);
	private String name;
	private double maxHealth;
	private double currentHealth;
	private String type;
	private ArrayList<SkillMove> moves;


	// The range should include the last element
	private int getRandomInt(int range) {
		return rand.nextInt(range);
	}

	// you should use this method to see if the skill move is missed.
	private boolean isMoveMissed(SkillMove m) {
		double d = rand.nextDouble();
		if (d > m.getMissRate()) {
			return false;
		}
		return true;
	}

    public Pokemon(String name, double maxHealth, String type, ArrayList<SkillMove> moves) {
        this.name = name;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.moves = moves;
        this.type = type;
    }


// Checks if the move missed, then removes the corresponding skill move damage from the pokemon health.
    public void attack (Pokemon defender, SkillMove attacker){
        double newHealth = defender.getCurrentHealth();
	    if (isMoveMissed(attacker)) {
            newHealth = defender.getCurrentHealth() - attacker.getDmg();
        }
	    defender.setCurrentHealth(newHealth);
    }


//    This is to make the move list into a useful string. Uses loop to add each move to a list.
	private String moveListString (ArrayList<SkillMove> moveListConstructor) {
	    String moveList = "";
	    for (int i=0; i<moveListConstructor.size(); i++){
        moveList += ((moveListConstructor.get(i)).toString());
        if (i!=moveListConstructor.size()-1){
            moveList += ", ";
        }
        }
        return  moveList;
    }

	public String toString() {
	   return  (name + ", Moves:[" + moveListString(moves) + "]");
    }

// getters and setters below

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<SkillMove> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<SkillMove> moves) {
        this.moves = moves;
    }
}
