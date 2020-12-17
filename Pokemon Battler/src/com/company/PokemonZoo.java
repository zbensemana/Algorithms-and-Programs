
package com.company;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class PokemonZoo {
	private Random rand = new Random(10);
    ArrayList<SkillMove> movesList = new ArrayList<SkillMove>();
    ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

    public PokemonZoo() {
		loadMoves();
		loadPokemon();
	}

	// The range should include the last element
	private int getRandomInt(int range) {
		return rand.nextInt(range);
	}

// Reads from the file one line at a time and adds each data to its corresponding SkillMove parameter.
	private void loadMoves() {

        try {
            try {
                FileReader fr = new FileReader("skillMove.txt");
                BufferedReader br = new BufferedReader(fr);
                String currentLine = br.readLine();
                while (currentLine != null) {
                    String[] splitList = currentLine.split(" ");
                    SkillMove listAdder = new SkillMove(splitList[0],splitList[1],Double.parseDouble(splitList[2]),Double.parseDouble(splitList[3]));
                    movesList.add(listAdder);
                    currentLine = br.readLine();
                }
                br.close();
                fr.close();
            } catch (FileNotFoundException f) {
                System.out.println("File not found");
            }

        }catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

//  Reads from the file one line at a time and adds each data to its corresponding Pokemon parameter.
//    Also, there is a loop that gives each pokemon a random set of skill moves that correspond to it's type and the amount of moves it should have.
    private void loadPokemon() {

            try {
                try {
                    FileReader fr = new FileReader("pokemons.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String currentLine = br.readLine();
                    while (currentLine != null) {
                        String[] splitList = currentLine.split(" ");
                        ArrayList<SkillMove> empty = new ArrayList<SkillMove>();
                        Pokemon listAdder = new Pokemon(splitList[0], Double.parseDouble(splitList[1]),splitList[2], empty);
                        listAdder.setCurrentHealth(Double.parseDouble(splitList[1]));
                        for (int i = 0; i < (Integer.parseInt(splitList[3])); ) {
                            int randomInt = getRandomInt(movesList.size() - 1);
                            if (listAdder.getType().equals(movesList.get(randomInt).getType())) {
                                listAdder.getMoves().add(movesList.get(randomInt));
                                i++;
                            }
                        }
                        pokemonList.add(listAdder);
                        currentLine = br.readLine();
                    }

                    br.close();
                    fr.close();
                } catch (FileNotFoundException f) {
                    System.out.println("File not found");
                }

            } catch (IOException e) {
                System.out.println("IO Exception");
            }


        }

    public ArrayList<SkillMove> getMovesList() {
        return movesList;
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }
}
