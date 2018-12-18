package Asgmt1_WordNet;

import edu.princeton.cs.algs4.*;

/**
 * The {@code SAP} class represents a shortest ancestral path
 * between two vertex(vertices) <em>pathV</em> and <em>pathW</em>.
 */
public class SAP {
    private final Digraph G;
    private final DeluxeBFS vcache, wcache;

    /**
     * Constructor takes a digraph (not necessarily a DAG)
     *
     * @param G
     */
    public SAP(Digraph G) {

        this.G = new Digraph(G);
        vcache = new DeluxeBFS(this.G);
        wcache = new DeluxeBFS(this.G);
    }

    /**
     * Return the length of shortest ancestral path between <em>v</em> and <em>w</em>.
     *
     * @param v the vertex
     * @param w the vertex
     * @return the length of SAP; -1 if no such path
     */
    public int length(int v, int w) {
        preCalc(v, w);

        return findDistance();
    }

    private int findDistance() {
        int minDistance = -1;
        DeluxeBFS[] caches = {vcache, wcache};

        for (DeluxeBFS cache : caches) {
            for (int t : cache.path()) {
                if (vcache.hasPathTo(t) && wcache.hasPathTo(t)) {
                    int distance = vcache.distTo(t) + wcache.distTo(t);

                    if (minDistance == -1 || distance < minDistance)
                        minDistance = distance;
                }
            }
        }
        return minDistance;
    }

    private void preCalc(int v, int w) {
        vcache.bfs(v);
        wcache.bfs(w);
    }

    private void preCalc(Iterable<Integer> v, Iterable<Integer> w) {
        vcache.bfs(v);
        wcache.bfs(w);
    }

    /**
     * Return a common ancestor of <em>pathV</em> and <em>pathW</em> that participates
     * in a shortest ancestral path.
     *
     * @param v the vertex
     * @param w the vertex
     * @return a common ancestor vertex; -1 if no such path
     */
    public int ancestor(int v, int w) {
        preCalc(v, w);
        return findAncestor();
    }

    private int findAncestor() {
        int minDistance = -1;
        int ancestor = -1;
        DeluxeBFS[] caches = {vcache, wcache};

        for (DeluxeBFS cache : caches) {
            for (int t : cache.path()) {
                if (vcache.hasPathTo(t) && wcache.hasPathTo(t)) {
                    int distance = vcache.distTo(t) + wcache.distTo(t);

                    if (minDistance == -1 || distance < minDistance) {
                        minDistance = distance;
                        ancestor = t;
                    }
                }
            }
        }
        return ancestor;
    }

    /**
     * Return the length of shortest ancestral path between any vertex in <em>pathV</em>
     * and any vertex in <em>pathW</em>
     *
     * @param v the subsets of vertices
     * @param w the subsets of vertices
     * @return the length of SAP; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        preCalc(v, w);

        return findDistance();

    }

    /**
     * Return a common ancestor that participates in shortest ancestral path.
     *
     * @param v the subsets of vertices
     * @param w the subsets of vertices
     * @return a common ancestor vertex; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        preCalc(v, w);

        return findAncestor();
    }

    /**
     * Unit tests the {@code SAP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        Bag<Integer> v = new Bag<>();
        v.add(7);
        v.add(8);
        Bag<Integer> w = new Bag<>();
        w.add(12);
        w.add(11);
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);

    }

}
