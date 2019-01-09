package Week2_ShrotestPaths;

import edu.princeton.cs.algs4.Stack;

public class SPT {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public SPT(int V){
        distTo = new double[V];
        edgeTo = new DirectedEdge[V];
    }

    public double distTo(int v){
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v){
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    private void relax(DirectedEdge e){
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
}
