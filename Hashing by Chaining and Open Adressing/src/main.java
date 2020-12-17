import java.io.*;
import java.util.*;

//No Collaborators
// Zachary Bensemana
// 260863976

public class main {


    public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.



        Chaining cls = new Chaining(10,0,-1);
        Open_Addressing hsh = new Open_Addressing(10,0,-1);


        System.out.println("Test 1 should be 30:  " + hsh.probe(1,0));

        System.out.println("Test 2 should be 30:  " + cls.chain(1));

        System.out.println("Test 3 should be 31:  " + hsh.probe(1,1));

        System.out.println("Test 4 should be 25:  " + cls.chain(4));

        System.out.println("Test 5 should be 19:  " + cls.chain(8));

        System.out.println("Test 6 should be 1:  " + hsh.probe(1,3));


        cls = new Chaining(10,0,-1);
        int[] keys = { 1, 3, 5, 7, 9};

        System.out.println("Test 7 should be 0 collisions:  " + cls.insertKeyArray(keys));


        hsh = new Open_Addressing(10, 0, -1);
        System.out.println("Test 8 should be 8:  " + hsh.probe(15, 0));


        Open_Addressing o = new Open_Addressing(3, 0, -1);
        int[] keys2 = { 15, 15 };
        System.out.println("Test 9 should be 1 jump:  " +  o.insertKeyArray(keys2));



        hsh = new Open_Addressing(10, 0, -1);
        System.out.println("Test 10 should be hash value of -1 and " + hsh.m + " jumps:  " + hsh.Table[hsh.probe(8,0)] + " hashvalue and " + hsh.removeKey(9) + " jumps.");


    }



    }



