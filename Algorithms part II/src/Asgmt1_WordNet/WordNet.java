package Asgmt1_WordNet;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


import java.util.HashMap;

public class WordNet {

    private final HashMap<Integer, String> id2synset;

    private final HashMap<String, Bag<Integer>> noun2ids;

    private final SAP sap;

    public WordNet(String synsets, String hypernyms) {
        validate(synsets);
        validate(hypernyms);

        id2synset = new HashMap<>();
        noun2ids = new HashMap<>();

        parseSynsets(synsets);
        sap = new SAP(parseHypernyms(hypernyms));
    }

    private void parseSynsets(String synsets) {
        In input = new In(synsets);
        Bag<Integer> bag;

        while (input.hasNextLine()) {
            String[] tokens = input.readLine().split(",");
            int id = Integer.parseInt(tokens[0]);
            id2synset.put(id, tokens[1]);

            for (String noun : tokens[1].split(" ")) {
                bag = noun2ids.get(noun);

                if (bag == null) {
                    bag = new Bag<>();
                    bag.add(id);
                    noun2ids.put(noun, bag);
                } else {
                    bag.add(id);
                }

            }

        }


    }

    private Digraph parseHypernyms(String hypernyms) {
        Digraph digraph = new Digraph(id2synset.size());
        In input = new In(hypernyms);

        while (input.hasNextLine()) {
            String[] tokens = input.readLine().split(",");
            int id = Integer.parseInt(tokens[0]);
            for (int i = 1, sz = tokens.length; i < sz; i++) {
                digraph.addEdge(id, Integer.parseInt(tokens[i]));
            }
        }

        verifyCycle(digraph);
        verifyRoot(digraph);

        return digraph;

    }

    private void verifyRoot(Digraph digraph) {
        int roots = 0;

        for (int i = 0, sz = digraph.V(); i < sz; i++) {
            if (!digraph.adj(i).iterator().hasNext()) {
                roots += 1;
            }
        }

        if (roots != 1) {
            throw new IllegalArgumentException();
        }
    }

    private void verifyCycle(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);

        if (directedCycle.hasCycle())
            throw new IllegalArgumentException();
    }

    private void validate(String arg) {
        if (arg == null)
            throw new IllegalArgumentException();
    }

    public Iterable<String> nouns() {
        return noun2ids.keySet();
    }

    public boolean isNoun(String word) {
        return noun2ids.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        verifyNoun(nounA);
        verifyNoun(nounB);

        return sap.length(noun2ids.get(nounA), noun2ids.get(nounB));

    }

    private void verifyNoun(String noun) {
        if (!isNoun(noun))
            throw new IllegalArgumentException();
    }

    public String sap(String nounA, String nounB) {
        verifyNoun(nounA);
        verifyNoun(nounB);

        return id2synset.get(sap.ancestor(noun2ids.get(nounA), noun2ids.get(nounB)));
    }

    public static void main(String[] args) {
        WordNet wordNet = new WordNet(args[0], args[1]);

        while (!StdIn.isEmpty()){
            String nounA = StdIn.readString();


            if (!wordNet.isNoun(nounA)){
                StdOut.printf("%s is not a noun\n", nounA);
                continue;
            }
            String nounB = StdIn.readString();

            if (!wordNet.isNoun(nounB)){
                StdOut.printf("%s is not a noun\n", nounB);
                continue;
            }

            int distance = wordNet.distance(nounA, nounB);
            String ancestor = wordNet.sap(nounA, nounB);

            StdOut.printf("distance = %d, ancestor = %s\n", distance, ancestor);
        }


    }
}
