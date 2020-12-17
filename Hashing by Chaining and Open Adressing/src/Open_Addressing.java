import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

//     made a global seed
     public int seed;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         this.seed = seed;
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }


     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random();
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
            //TODO: implement this function and change the return statement.

//            extra check if i passed is unreasonable
            if (i>=m) {
                return -1;
            }
//            Instantiates a chaining class
            Chaining cls = new Chaining(w,seed,A);
//             Calls chain hash function on key and has space for increasing i, performs modula 2^r
            return (cls.chain(key) + i) % power2(r);
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.

//            Creates useful variables, sets i = 0
            boolean space = false;
            int hashValue;
            int i = 0;
            int tableValue;

//            Iterate until space has been found in hashmap
            while (!space) {

//                makes sure if i reaches size of hashmap, it breaks since there are no more spaces
//                would return collisions as the size of hashmap
                if (i>=m){
                    break;
                }
//                call probe on appropriate i and key
                hashValue = probe(key, i);

//                Checks if there is an open spot in Hashmap.
//                If yes, updates key to location and updates boolean to end loop
//                If no, increases i and loops again
                tableValue = Table[hashValue];
                if (tableValue==-1){
                    Table[hashValue] = key;
                    space = true;
                } else {
                    i++;
                }
            }

//            i is the number of collisions
            return i;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            //TODO
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Removes key k from hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //TODO: implement this and change the return statement

//            Does the exact same thing as insertKey, but instead of looking for empty spot it looks for the key.
            boolean keyfound = false;
            int hashValue;
            int i = 0;
            int tableValue;

            while (!keyfound) {

//                Breaks if key has been looked for in each box on the hash map
                if (i>=m){
                    break;
                }

//                checks if key is in probed location, and updates the hashmap location to empty, then ends loop
//                if key is not there, increases i and loops again
                hashValue = probe(key, i);
                tableValue = Table[hashValue];

                if (tableValue==key){
                    Table[hashValue] = -1;
                    keyfound = true;
                } else {
                    i++;
                }

            }


//              i is the number of collisions
            return i;
        }
}
