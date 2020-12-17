import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	public void crawlAndIndex(String url) throws Exception {
		// TODO : Add code here

//        If the vertex does not exist, it goes through the code after adding it.
        if (internet.addVertex(url)){
            internet.setVisited(url, true);
            ArrayList<String> content = parser.getContent(url);
            ArrayList<String> links =parser.getLinks(url);

//            Loops through strings in content
            for (String t: content) {
//                If word index contains the key, adds the url to the list
                t=t.toLowerCase();
                if (wordIndex.containsKey(t)){
                    ArrayList<String> updater = wordIndex.get(t);
                    updater.add(url);
                    wordIndex.put(t,updater);
//                    If not, word index is updated with the key and adds a url.
                } else {
                    ArrayList<String> initializer = new ArrayList<String>();
                    initializer.add(url);
                    wordIndex.put(t,initializer);
                }
            }

            for (String t: links){
                crawlAndIndex(t);
                internet.addEdge(url, t);
            }
        }
	}
	
	
	
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		// TODO : Add code here

        ArrayList<String> vertices = internet.getVertices();
        ArrayList<Double> ranks;
        ArrayList<Double> ranksLast = new ArrayList<Double>();

        //            Start by initializing them all to 1

//        Checks the sum of the ranks
        int rankTester=0;
        for (String q: vertices)
                rankTester+= internet.getPageRank(q);

//        If the sum is 0, initializes them all to 1
        if (rankTester==0){
            for (String q: vertices) {
                internet.setPageRank(q, 1.0);
            }
        }

        for (String q: vertices) {
            ranksLast.add(1.0);
        }

        boolean converged = false;

//      Repeats compute ranks until it converges
        while (!converged){
//            computes new ranks and assigns it to arraylist
            ranks = computeRanks(vertices);

//            put each rank in the corresponding vertex
            for (int i=0; i<ranks.size(); i++) {
                internet.setPageRank(vertices.get(i), ranks.get(i));

            }

//            If epsilon is ever smaller than | pr(k−1)[vi] − pr(k)[vi] |, it continues the loop
            boolean check=true;
            for (int i=0; i<ranks.size(); i++){
                if (epsilon<=Math.abs(ranksLast.get(i)-ranks.get(i))){
                    check=false;
                    break;
                }
            }
            if (check) {
                converged = true;
                continue;
            }

//            Makes these ranks equivalent to the last ranks
            ranksLast=ranks;

        }
    }

	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		// TODO : Add code here
        if (vertices.size()==0){
            return null;
        } else {

            ArrayList<Double> ranks = new ArrayList<Double>();
            for (String t : vertices) {
                Double edgeCalc = 0.0;
                ArrayList<String> edgesInto = internet.getEdgesInto(t);
//            Calculates pagerank/outdegree of each edge
                for (String q : edgesInto) {
                    edgeCalc += internet.getPageRank(q) / internet.getOutDegree(q);
                }

//           adds to and multiplies edgeCalc by damping factor
                Double rank = 0.5 + 0.5 * edgeCalc;

                ranks.add(rank);
            }

            return ranks;
        }


	}

	
	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {
		// TODO: Add code here

//        Gets urls with strings
        query = query.toLowerCase();

        ArrayList<String> urls = wordIndex.get(query);

//        Makes a hashmap that organizes the strings by pagerank
        HashMap<String, Double > wordUrls = new HashMap<String, Double>();
        for (String t: urls){
            wordUrls.put(t,internet.getPageRank(t));
        }

//        Sorts the urls by rank
        ArrayList<String> sortedUrls = Sorting.fastSort(wordUrls);

//        returns it
        if (sortedUrls.size()==0){
            return null;
        }
		return sortedUrls;
	}
}
