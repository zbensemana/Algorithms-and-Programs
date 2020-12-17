public class BellmanFord{

    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    class BellmanFordException extends Exception{
        public BellmanFordException(String str){
            super(str);
        }
    }

    class NegativeWeightException extends BellmanFordException{
        public NegativeWeightException(String str){
            super(str);
        }
    }

    class PathDoesNotExistException extends BellmanFordException{
        public PathDoesNotExistException(String str){
            super(str);
        }
    }

    BellmanFord(WGraph g, int source) throws NegativeWeightException {
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         */

        System.out.println("This is the source " + source);
        this.source = source;
        int numV = g.getNbNodes();
        distances = new int[numV];
        predecessors = new int[numV];

        for (int i = 0; i < numV; ++i)
            predecessors[i] = -1;

        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for (int i = 0; i < numV; ++i)
            distances[i] = Integer.MAX_VALUE;
        distances[source] = 0;

        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < numV; ++i) {
            for (Edge e: g.listOfEdgesSorted()) {
                int u = e.nodes[0];
                int v = e.nodes[1];
                int weight = e.weight;

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    predecessors[v] = u;
                }
            }
        }

//        Check for negative weight cycles
        for (Edge e: g.listOfEdgesSorted()) {
            int u = e.nodes[0];
            int v = e.nodes[1];
            int weight = e.weight;

            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                System.out.println("Graph contains negative weight cycle");
                throw new NegativeWeightException("Error - no weight");
            }
        }

//        distances=dist;


    }

    public int[] shortestPath(int destination) throws PathDoesNotExistException {
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Error is thrown
         */

        System.out.println("destination" + destination);

        int tracker=destination;
        int [] temp = new int [distances.length];
        int count=0;


//        iterate backward through predecessors and load them all in temp



        while (tracker != source){
            if (predecessors[tracker]==-1){
                throw new PathDoesNotExistException("Error - no path");
            }
            temp[count] = tracker;
            tracker = predecessors[tracker];
            count++;
        }
//        count++;
//        temp[count] = source;

//        for (int i = 0; i<=temp.length; i++) {
//            System.out.println(" temp " + i + " is " + temp[i]);
//        }

        int[] shortestPath = new int[count+1];

        for (int i = 0; i<=count; i++){
            shortestPath[i] = temp[count-i];
        }


//        from destination, check edges
//        whichever edge, when weight subtracted, is the same, go down that path

//        for (int i = 0; i<=shortestPath.length; i++) {
//            System.out.println(" shortest path " + i + " is " + shortestPath[i]);
//        }

        shortestPath[0] = source;


        return shortestPath;
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}

