package Asgmt1_WordNet;

import edu.princeton.cs.algs4.Digraph;

/**
 *  The {@code SAP} class represents a shortest ancestral path
 *  between two vertex(vertices) <em>pathV</em> and <em>pathW</em>.
 *
 */
public class SAP {
    private Digraph G;
    private DeluxeBFS v;
    private DeluxeBFS w;

    /**
     * Constructor takes a digraph (not necessarily a DAG)
     * @param G
     */
    public SAP(Digraph G){
        this.G = new Digraph(G);

    }

    /**
     * Return the length of shortest ancestral path between <em>v</em> and <em>w</em>.
     *
     * @param v the vertex
     * @param w the vertex
     * @return  the length of SAP; -1 if no such path
     */
    public int length(int v, int w){
        this.v = new DeluxeBFS(G, v);
        this.w = new DeluxeBFS(G, w);
        for (int i : this.v.getAncestral()){
            for (int j : this.w.getAncestral()){
                if (i == j)

            }
        }



    }

    /**
     * Return a common ancestor of <em>pathV</em> and <em>pathW</em> that participates
     * in a shortest ancestral path.
     *
     * @param v the vertex
     * @param w the vertex
     * @return  a common ancestor vertex; -1 if no such path
     */
    public int ancestor(int v, int w){}

    /**
     * Return the length of shortest ancestral path between any vertex in <em>pathV</em>
     * and any vertex in <em>pathW</em>
     *
     * @param v the subsets of vertices
     * @param w the subsets of vertices
     * @return  the length of SAP; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w){}

    /**
     * Return a common ancestor that participates in shortest ancestral path.
     *
     * @param v the subsets of vertices
     * @param w the subsets of vertices
     * @return  a common ancestor vertex; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){}

    /**
     * Unit tests the {@code SAP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args){}

}
