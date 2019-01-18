package Week3_MaximumFlowandMinimumCut;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class FordFulkerson {

    private boolean[] marked;           //  true if s->v path in residual network
    private FlowEdge[] edgeTo;          //  last edge on s->v path
    private double value;               //  value of flow

    public FordFulkerson(FlowNetwork G, int s, int t) {
        value = 0.0;
        while (hasAugmentingPath(G, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));

            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);
        }
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()){
            int v = queue.dequeue();

            for (FlowEdge e : G.adj(v)){
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]){     //  found path from s to w in the residual network
                    edgeTo[w] = e;          //  save last edge on path to w;\
                    marked[w] = true;       //  mark w;
                    queue.enqueue(w);       //  add w to the queue
                }
            }
        }
        int index = 0;
        for (boolean a : marked)
            StdOut.print(String.format("%d:%s ", index++, a));
        int index2 = 0;
        for (FlowEdge a : edgeTo)
            StdOut.print(String.format("%d:%s ", index2++, a));
        return marked[t];                   //  is t reachable from s in residual network?

    }

    public double value(){
        return value;
    }

    public boolean inCut(int v){
        return marked[v];
    }

    public static void main(String[] args){
        FlowEdge flowEdge1 = new FlowEdge(0,2, 10);
        FlowEdge flowEdge2 = new FlowEdge(0,1, 5);
        FlowEdge flowEdge3 = new FlowEdge(1,2, 4);
        FlowEdge flowEdge4 = new FlowEdge(1,3, 8);
        FlowEdge flowEdge5 = new FlowEdge(2,4, 9);
        FlowEdge flowEdge6 = new FlowEdge(2,3, 4);
        FlowEdge flowEdge7 = new FlowEdge(4,3, 15);
        FlowEdge flowEdge8 = new FlowEdge(4,5, 10);
        FlowEdge flowEdge9 = new FlowEdge(3,5, 10);
        FlowNetwork flowNetwork = new FlowNetwork(6);
        flowNetwork.addEdge(flowEdge1);
        flowNetwork.addEdge(flowEdge2);
        flowNetwork.addEdge(flowEdge3);
        flowNetwork.addEdge(flowEdge4);
        flowNetwork.addEdge(flowEdge5);
        flowNetwork.addEdge(flowEdge6);
        flowNetwork.addEdge(flowEdge7);
        flowNetwork.addEdge(flowEdge8);
        flowNetwork.addEdge(flowEdge9);

        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0, 5);

    }
}
