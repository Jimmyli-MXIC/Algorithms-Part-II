package Week5_RegularExpressions;

import edu.princeton.cs.algs4.*;

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
        /* States reachable from start by epsilon-transitions */
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v)) pc.add(v);

        for (int i = 0; i < txt.length(); i++){
            /* States reachable after scanning past txt.charAt(i) */
            Bag<Integer> match = new Bag<>();
            for (int v : pc){
                if (v == M) continue;
                if ((re[v] == txt.charAt(i)) || re[v] == '.')
                    match.add(v+1);
            }
            /* Follow epsilon-transitions */
            dfs = new DirectedDFS(G, match);
            pc = new Bag<>();
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v)) pc.add(v);
        }
        /* Accept if can end in state M */
        for (int v : pc)
            if (v == M) return true;
        return false;

    }

    public Digraph buildEpsilonTransitionDigraph(){

        Digraph G = new Digraph(M+1);
        Stack<Integer> ops = new Stack<>();
        for (int i = 0; i < M; i++){
            int lp = i;

            if (re[i] == '(' || re[i] == '|') ops.push(i);          //  left parentheses and |

            else if (re[i] == ')'){
                int or = ops.pop();
                if (re[or] == '|'){
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                }
                else lp = or;
            }

            if (i < M-1 && re[i+1] == '*'){                         //  closure(needs 1-character lookahead)
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')')       //  metasymbols
                G.addEdge(i, i+1);
        }

        return G;
    }
}
