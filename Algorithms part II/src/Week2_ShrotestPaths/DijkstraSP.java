package Week2_ShrotestPaths;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else
                pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args) {
//        DirectedEdge a = new DirectedEdge(0, 1,4);
//        DirectedEdge b = new DirectedEdge(1,2,6);
//        DirectedEdge c = new DirectedEdge(2, 3, -9);
//        DirectedEdge d = new DirectedEdge(0,3,2);
//        DirectedEdge e = new DirectedEdge(3, 4, 5);
//        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(5);
//        edgeWeightedDigraph.addEdge(a);
//        edgeWeightedDigraph.addEdge(b);
//        edgeWeightedDigraph.addEdge(c);
//        edgeWeightedDigraph.addEdge(d);
//        edgeWeightedDigraph.addEdge(e);
//        DijkstraSP dijkstraSP = new DijkstraSP(edgeWeightedDigraph, 0);
//        StdOut.println(dijkstraSP.distTo(3));
//        for (DirectedEdge edge :dijkstraSP.pathTo(3))
//            StdOut.println(edge);
//        StdOut.println(dijkstraSP.distTo(4));
//        for (DirectedEdge edge :dijkstraSP.pathTo(4))
//            StdOut.println(edge);
        DirectedEdge a = new DirectedEdge(1, 2, 1);
        DirectedEdge b = new DirectedEdge(2, 3, 1);
        DirectedEdge c = new DirectedEdge(1, 3, 0);
        DirectedEdge d = new DirectedEdge(1, 4, 99);
        DirectedEdge e = new DirectedEdge(4, 2, -300);
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(5);
        edgeWeightedDigraph.addEdge(a);
        edgeWeightedDigraph.addEdge(b);
        edgeWeightedDigraph.addEdge(c);
        edgeWeightedDigraph.addEdge(d);
        edgeWeightedDigraph.addEdge(e);

        DijkstraSP dijkstraSP = new DijkstraSP(edgeWeightedDigraph, 1);
        StdOut.println(dijkstraSP.distTo(3));
        for (DirectedEdge edge : dijkstraSP.pathTo(3))
            StdOut.println(edge);

    }
}
