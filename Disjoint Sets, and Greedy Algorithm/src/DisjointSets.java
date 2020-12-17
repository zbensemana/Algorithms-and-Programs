/****************************
*
* COMP251 template file
*
* Assignment 2, Question 1
*
*****************************/


public class DisjointSets {

    private int[] par;
    private int[] rank;
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }

            for (int i=0; i<this.rank.length; i++) {
                rank[i] = 0;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find resentative of element i */
    public int find(int i) {

        /* Fill this method (The statement return 0 is here only to compile) */


//        Path compression and find -  goes up the chain recursively and when it finds root parent, sets all on route to root to that paretn

        if (par[i] != i){
            par[i] = find(par[i]);
        }

        return par[i];
        
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {


        int iRoot = find(i);
        int jRoot = find(j);

        // I and J are already in the same set
        if (iRoot == jRoot) {
            return -1;
        }

        // I and J are not in same set, so we merge them
//        We will chose who to merge based on rank, and if the same rank, we merge into j

//        System.out.println("Rank i  =  " + iRoot + "  is rank " +  rank[iRoot] + ", Rank j = " + jRoot + "  is rank  " + rank[jRoot]);

        if (rank[iRoot]<rank[jRoot]){
            par[iRoot] = jRoot;
        } else if (rank[jRoot]<rank[iRoot]){
            par[jRoot] = iRoot;
        } else {
            par[iRoot] = jRoot;
            rank[jRoot] = rank[jRoot] + 1;
        }



        /* Fill this method (The statement return 0 is here only to compile) */
        return 0;
        
    }
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);
        
    }

}
