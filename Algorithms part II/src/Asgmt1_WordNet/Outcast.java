package Asgmt1_WordNet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordNet){
        this.wordNet = wordNet;

    }

    public String outcast(String[] nouns){

        int[] distances = new int[nouns.length];
        for (int i = 0, sz = nouns.length; i < sz; i++){
            for (int j = i+1; j < sz; j++){
                int dist = wordNet.distance(nouns[i], nouns[j]);
                distances[i] += dist;
                distances[j] += dist;
            }
        }

        int maximumDistance = 0;
        int maximumIndex = 0;

        for (int i = 0; i < distances.length; i++){
            if (maximumDistance < distances[i]){
                maximumDistance = distances[i];
                maximumIndex = i;
            }
        }
        return nouns[maximumIndex];

    }

    public static void main(String[] args){
        WordNet wordNet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordNet);

        for (int t = 2; t < args.length; t++){
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }

    }
}
