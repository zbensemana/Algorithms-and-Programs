import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class Sorting {

    /*
     * This method takes as input an HashMap with values that are Comparable.
     * It returns an ArrayList containing all the keys from the map, ordered
     * in descending order based on the values they mapped to.
     *
     * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number
     * of pairs in the map.
     */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
            for(int j=0; j<N-i-1; j++){
                if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) <0){
                    K temp = sortedUrls.get(j);
                    sortedUrls.set(j, sortedUrls.get(j+1));
                    sortedUrls.set(j+1, temp);
                }
            }
        }
        return sortedUrls;
    }




    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
        // ADD YOUR CODE HERE
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());
        mergeSort(sortedUrls, results);
        return sortedUrls;
    }



    public static <K, V extends Comparable> ArrayList<K> mergeSort(ArrayList<K> whole, HashMap<K, V> results) {
        ArrayList<K> left = new ArrayList<K>();
        ArrayList<K> right = new ArrayList<K>();
        int center;

//        Base case
        if (whole.size() == 1) {
            return whole;
        }

        else {
//            Divides the list into 2
            center = whole.size()/2;

//            Partitions it into left and right
            for (int i = 0; i < center; i++) {
                left.add(whole.get(i));
            }
            for (int i = center; i < whole.size(); i++) {
                right.add(whole.get(i));
            }

//            Right and Left recursive sort
            right = mergeSort(right, results);
            left  = mergeSort(left, results);

            // Merge the results together
            merge(left, right, whole, results);
        }
        return whole;
    }


    private static <K, V extends Comparable> void merge(ArrayList<K> left, ArrayList<K> right, ArrayList<K> whole, HashMap<K, V> results) {

//        Indices for merging
        int wholeIndex = 0;
        int rightIndex = 0;
        int leftIndex = 0;


        while (leftIndex < left.size() && rightIndex < right.size()) {
//            Compares the lists and sorts them accordingly
            if ( results.get(left.get(leftIndex)).compareTo(results.get(right.get(rightIndex))) > 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            }
            else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }

            wholeIndex++;
        }

        ArrayList<K> rest;
        int restIndex;

        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        }

        else {
            rest = left;
            restIndex = leftIndex;
        }

        //Copies the rest into it
        for (int i = restIndex; i < rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }



}






