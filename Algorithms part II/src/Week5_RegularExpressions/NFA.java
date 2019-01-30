package Week5_RegularExpressions;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;

public class NFA {
    private char[] re;      //  match transitions
    private Digraph G;      //  epsilon transition digraph
    private int M;          //  number of states

    public NFA(String regexp){
        M = regexp.length();
        re = regexp.toCharArray();
        G = buildEpsilonTransitionDigraph();
    }

    public boolean recognizes(String txt){
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);

    }

    public Digraph buildEpsilonTransitionDigraph(){

    }
}
