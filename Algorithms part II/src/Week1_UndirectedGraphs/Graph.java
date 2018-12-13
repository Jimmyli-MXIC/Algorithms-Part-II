package Week1_UndirectedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Graph {

    private final int V;
    private Bag<Integer>[] adj;

    public Graph(int V){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v]  = new Bag<Integer>();
    }
//    public Graph(In in){
//
//    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Vertices adjacent to v 邻近v的顶点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        return adj[v];
    }      //

    public int V(){
        return V;
    }

    public int E(){
        //  TODO
        return 0;
    }

    public String toString(){
        //  TODO
        return null;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
//        Graph G = new Graph(in);

//        for (int v = 0; v < G.V(); v++)
//            for (int w : G.adj(v))
//                StdOut.println(v + "-" + w);
    }

}
