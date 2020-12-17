import java.util.ArrayList;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        ArrayList<Edge> sortedEdges = new ArrayList<Edge>();
        WGraph mst = new WGraph();
        int nbNodes =  g.getNbNodes();
        DisjointSets x = new DisjointSets(nbNodes);

        sortedEdges = g.listOfEdgesSorted();

        for (int i=0; i<sortedEdges.size(); i++ ){
            Edge e = sortedEdges.get(i);

            Boolean safe = IsSafe(x,e);
            if (safe){
                mst.addEdge(e);
            }
        }



//        Check if it forms a cycle with the spanning tree formed so far - Use Find to see if it is the same
//         If cycle is not formed, include this edge - Union to MST
//         Else, discard it. - move on in loop
//        Repeat step#2 until there are (V-1) edges in the spanning tree.

        /* Fill this method (The statement return null is here only to compile) */
        
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        int x = p.union(e.nodes[0],e.nodes[1]);

        return (x!=-1);
    }

    public static void main(String[] args){

//        String file = args[0];
        WGraph g = new WGraph("C:\\Users\\zbens\\Desktop\\JavaPrograms\\Comp251-A2\\src\\g1.txt");
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
