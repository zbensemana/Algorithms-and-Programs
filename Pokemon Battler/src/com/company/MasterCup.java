
package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MasterCup {

	private Random rand = new Random(10);
    private ArrayList<PokemonTrainer> trainerList = new ArrayList<PokemonTrainer>();
//    public static void main(String[] args) {
//		MasterCup m = new MasterCup();
//	}

	public MasterCup() {
		PokemonZoo zoo = new PokemonZoo();
		createTrainers(45, zoo.getPokemonList());
		startMatch(trainerList);
		writeScores(trainerList);
        System.out.println(trainerList);

	}

	

	// The range should include the last element
	private int getRandomInt(int range) {
		return rand.nextInt(range);

	}

//	creates x pokemon trainers, gets their names from the file "name.txt". ALso adds 5 random pokemon to each of their teams.
	private void createTrainers (int amount, ArrayList<Pokemon> pokemonList) {
        int x = 0;
            try {
                try {
                    FileReader fr = new FileReader("name.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String currentLine = br.readLine();
                    while (currentLine != null && x<amount) {
                        String[] splitList = currentLine.split(" ");
                        ArrayList<Pokemon> teamBuilder = new ArrayList<Pokemon>();
                        for (int i=0; i<5; i++){
                            teamBuilder.add(pokemonList.get(getRandomInt(208)));
                        }
                        PokemonTrainer listAdder = new PokemonTrainer(splitList[0],teamBuilder);
                        trainerList.add(listAdder);
                        currentLine = br.readLine();
                        System.out.print(trainerList.get(x));
                        x++;
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


// Takes a trainer, loops through all their pokemon, and sets their current health to their max health.
    private PokemonTrainer heallAll (PokemonTrainer pokeHeal){
        for (int i=0; i<5; i++){
            pokeHeal.getTeam().get(i).setCurrentHealth(pokeHeal.getTeam().get(i).getMaxHealth());
        }
        return pokeHeal;
    }
// Gym leader attacks first using a random move, then checks if challenger pokemon is dead. If not, challenger attacks Gym leader with a random move, then checks if gym pokemon is dead.
//    Each time a pokemon dies, the next one in the list is called. When all 5 are dead, a winner is declared, and given +1 to their win stat.
    private void pvp (PokemonTrainer gymLeader, PokemonTrainer challenger) {
        int gymLeaderPoke = 0;
        int challengerPoke = 0;
        while (gymLeaderPoke < 5 && challengerPoke < 5) {
            gymLeader.getTeam().get(gymLeaderPoke).attack(challenger.getTeam().get(challengerPoke), gymLeader.getTeam().get(gymLeaderPoke).getMoves().get(getRandomInt(gymLeader.getTeam().get(gymLeaderPoke).getMoves().size())));
            if (challenger.getTeam().get(challengerPoke).getCurrentHealth() <= 0){
                challengerPoke ++;
                continue;
            }
            challenger.getTeam().get(challengerPoke).attack(gymLeader.getTeam().get(gymLeaderPoke), challenger.getTeam().get(challengerPoke).getMoves().get(getRandomInt(challenger.getTeam().get(challengerPoke).getMoves().size())));
            if (gymLeader.getTeam().get(gymLeaderPoke).getCurrentHealth() <= 0) {
                gymLeaderPoke++;
            }
        }

        if (challengerPoke == 5) {
            gymLeader.setWin(gymLeader.getWin()+1);
        }
        else if (gymLeaderPoke == 5){
            challenger.setWin(challenger.getWin()+1);
        }

        heallAll(gymLeader);
        heallAll(challenger);
    }

//    It makes every trainer the gym leader at one point, and makes them fight all the trainers.
//    The if statement is to make sure the trainers do not fight themselves.
    private void startMatch(ArrayList<PokemonTrainer> matchList){
        for (int i=0; i<matchList.size(); i++){
            for (int j=0; j<matchList.size(); j++){
                if (i == j){
                    continue;
                }
                pvp(matchList.get(i),matchList.get(j));
            }
        }
    }

//    Writes one Pokemon trainer at a time into the file "scores.text"
    private void writeScores (ArrayList<PokemonTrainer> finalScore){
        try {
            try {
                FileWriter fw = new FileWriter("scores.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                for (int x = 0; x < finalScore.size(); x++) {
                    String message = trainerList.get(x).toString();
                    bw.write(message);
                }
                bw.close();
                fw.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not Found");
            }
        } catch (IOException e) {
            System.out.println("IOException Found");
        }
    }
    }

