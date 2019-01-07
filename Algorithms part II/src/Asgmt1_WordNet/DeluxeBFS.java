package Asgmt1_WordNet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class DeluxeBFS {
    private static final int INFINITY = Integer.MAX_VALUE;
    private final Digraph digraph;
    private final boolean[] marked;
    private final int[] distTo;
    private final Queue<Integer> q;
    private final Queue<Integer> pathQueue;

    public DeluxeBFS(Digraph G){
        this.digraph = G;
        marked = new boolean[G.V()];
        distTo = new int [G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        q = new Queue<>();
        pathQueue = new Queue<>();
    }

    public Iterable<Integer> path(){
        return pathQueue;
    }

    public void bfs(int s){
        validateVertex(s);
        clear();
        assert q.isEmpty();
        assert pathQueue.isEmpty();

        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        pathQueue.enqueue(s);
        while (!q.isEmpty()){
            int v = q.dequeue();
            for (int w : digraph.adj(v)){
                if (!marked[w]){
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                    pathQueue.enqueue(w);
                }
            }

        }
    }

    public void bfs(Iterable<Integer> sources){
        validateVertex(sources);
        clear();
        assert q.isEmpty();
        assert pathQueue.isEmpty();

        for (int s : sources){
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
            pathQueue.enqueue(s);
        }

        while (!q.isEmpty()){
            int v = q.dequeue();
            for (int w : digraph.adj(v)){
                if (!marked[w]){
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                    pathQueue.enqueue(w);
                }
            }
        }

    }

    private void clear(){
        while (!pathQueue.isEmpty()){
            int v = pathQueue.dequeue();
            marked[v] = false;
            distTo[v] = INFINITY;
        }
    }

    public boolean hasPathTo(int v){
        validateVertex(v);
        return marked[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    private void validateVertex(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices){
            if (v < 0 || v >= V){
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }


    }

    public int distTo(int v){
        validateVertex(v);
        return distTo[v];
    }

}