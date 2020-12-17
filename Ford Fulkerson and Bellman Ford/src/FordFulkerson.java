import java.util.*;
import java.io.File;



// Zachary Bensemana
// 260863976
// No Collaborators

public class FordFulkerson {

    public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        /* YOUR CODE GOES HERE*/

        int parent[] = new int[graph.getNbNodes()];


//        do an iterative DFS in the same way as the iterative BFS in the notes

        boolean visited[] = new boolean[graph.getNbNodes()];
//        for (int i = 0; i < graph.getNbNodes(); ++i)
//            visited[i] = false;
        // Create a visited array and mark all vertices as not
        // visited

        int s = graph.getSource();
        int q = graph.getDestination();
        visited[s] = true;
        parent[s]=-1;
        parent[q]=-1;


        // Standard DFS

        DFSrecursion(parent, s, visited, graph );

//        Build path from sink with parent list until you reach source
//        if Sink was not reached, return empty path

        int iter = q;

        if (parent[iter]!=-1){
            while (iter!=s){
                path.add(0,iter);
                iter = parent[iter];
            }
            path.add(0,iter);
        }


        return path;




    }


    public static String fordfulkerson(WGraph graph) {
        String answer = "";
        int maxFlow = 0;

//        System.out.println("true source is " + graph.getSource() + " true sink is " + graph.getDestination());

        boolean loop = true;
        ArrayList<Integer> path = new ArrayList<Integer>();


        WGraph residualGraph = new WGraph(graph);
//        int parent[] = new int[graph.getNbNodes()];

        while (loop){

           path = pathDFS(graph.getSource(), graph.getDestination(),residualGraph);
//           System.out.println("path found " + path.toString());
           if (path.size()==0){
               loop=false;
               break;
           }

           int pathFlow = Integer.MAX_VALUE;

           for (int i = 0; i<path.size()-1; i++){
               pathFlow = Math.min(pathFlow,residualGraph.getEdge(path.get(i),path.get(i+1)).weight);
           }

//            System.out.println(pathFlow + " = pathflow");

            for (int i = 0; i<path.size()-1; i++){
//                System.out.println(" i is " + i );
//
//                System.out.println(" weigh path before is " + residualGraph.getEdge(path.get(i),path.get(i+1)).weight);
//
//                System.out.println(" path is between " + path.get(i) + " and " + path.get(i+1));

                residualGraph.getEdge(path.get(i),path.get(i+1)).weight -= pathFlow;
                if (residualGraph.getEdge(path.get(i+1),path.get(i)) == null){
                    Edge temp = new Edge(path.get(i+1),path.get(i),0);
                    residualGraph.addEdge(temp);
                }

                residualGraph.getEdge(path.get(i+1),path.get(i)).weight += pathFlow;

//                System.out.println(" weight path after is " + residualGraph.getEdge(path.get(i),path.get(i+1)).weight);

            }

            maxFlow += pathFlow;
//            System.out.println("path flow = " + pathFlow + " max flow = " + maxFlow);

        }


//        subtract residual graph from regular graph
        for (Edge e : graph.getEdges()) {
            Edge f = residualGraph.getEdge(e.nodes[0],e.nodes[1]);
            e.weight -= f.weight;
        }


        /* YOUR CODE GOES HERE		*/

        answer += maxFlow + "\n" + graph.toString();
        return answer;
    }


    public static void main(String[] args) {
        String file = args[0];
        File f = new File(file);
        WGraph g = new WGraph(file);
        System.out.println(fordfulkerson(g));
    }


    private static void DFSrecursion(int[] parent, int v, boolean visited[], WGraph rGraph)
    {
        // Mark the current node as visited
        visited[v] = true;
        if (v==rGraph.getDestination()){
            return;
        }

        // Recur for all the vertices adjacent to this vertex
        for (int i=0; i<rGraph.getNbNodes(); i++) {
            Edge check = rGraph.getEdge(v, i);
            if (check != null) {
                if (!visited[i] && check.weight>0) {
                    parent[i]=v;
                    DFSrecursion(parent, i, visited, rGraph);
                }
            }
        }
    }

}





