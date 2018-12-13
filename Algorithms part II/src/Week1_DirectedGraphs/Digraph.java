package Week1_DirectedGraphs;

import edu.princeton.cs.algs4.Bag;

public class Digraph {

    private final int V;
    private final Bag<Integer>[] adj;

    public Digraph(int V){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int V(){
        return V;
    }

    public int E(){
        //  TODO
        return 0;
    }

    public Digraph reverse(){
        //  TODO
        return null;
    }

    public String toString(){
        //  TODO
        return null;
    }
}
