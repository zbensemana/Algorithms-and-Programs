import java.io.*;
import java.util.*;

public class Chaining {
    
     public int m; // number of SLOTS 
     public int A; // the default random number
     int w;
     int r;
     public ArrayList<ArrayList<Integer>>  Table;


    // if A==-1, then a random A is generated. else, input A is used.
    protected Chaining(int w, int seed, int A){
        this.w = w;
//         Generates r - our amount of digits to recognize
        this.r = (int) (w-1)/2 +1;
//         Generates Number of Slots from r
        this.m = power2(r);
//         Makes a hash table and gives each box its own ArrayList
        this.Table = new ArrayList<ArrayList<Integer>>(m);
        for (int i=0; i<m; i++) {
             Table.add(new ArrayList<Integer>());
        }
//        Generates A on first iteration or Keeps A
        if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
        }
        else{
            this.A = A;
        }
     }


    /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }


     //generate a random number in a range (for A)
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;     
    }




    /**Implements the hash function h(k)*/
    // TODO: implement this and change the return statement
    public int chain (int key) {

//       Multiply the key by A and then taking Modulo 2
//      Bit shift by the necessary amount

        return (A * key) % power2(w) >> (w-r);

    }
        
    
    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int insertKey(int key){
        //TODO: implement this and change the return statement

//        Calls chain on the key to calculate the output of the hash function
        int hashValue = chain(key);

//        Takes the array list at position hashValue in the hashmap
        ArrayList<Integer> x = Table.get(hashValue);
        x.add(key);

//        Takes the size of the arraylist to calculate collisions, and subtracts itself from the counter
        return x.size()-1;


    }

    
    
    /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
    public int insertKeyArray (int[] keyArray){
        int collision = 0;
        for (int key: keyArray) {
            collision += insertKey(key);
        }
        return collision;
    }


}