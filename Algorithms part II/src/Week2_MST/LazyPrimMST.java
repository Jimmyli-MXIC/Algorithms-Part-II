package Week2_MST;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;
public class LazyPrimMST {

    private boolean[] marked;   //  MST vertices
    private Queue<Edge> mst;    //  MST edges
    private MinPQ<Edge> pq;     //  PQ of edges

    public LazyPrimMST(EdgeWeightedGraph G){
        pq = new MinPQ<>();
        mst = new Queue<>();
        marked = new boolean[G.V()];
        visit(G, 0);                              //  assume G is connected

        /* repeatedly delete the min weight edge e=v-w from PQ */
        while (!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);     //  ignore if both endpoints in T
            if (marked[v] && marked[w])
                continue;
            mst.enqueue(e);                         //  add edge e to tree
            if (!marked[v])                         //  add v or w to tree
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;                           //  add v to T
        /* for each edge e=v-w, add to PQ if w not already in T */
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)])
                pq.insert(e);
    }

    public Iterable<Edge> mst(){
        return mst;
    }

}
