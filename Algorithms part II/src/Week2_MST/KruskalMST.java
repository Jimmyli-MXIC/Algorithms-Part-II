package Week2_MST;


import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class KruskalMST {

    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph G) {

        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges())
            pq.insert(e);

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }
}
