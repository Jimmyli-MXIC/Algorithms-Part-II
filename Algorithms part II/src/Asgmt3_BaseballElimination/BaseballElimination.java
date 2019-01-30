package Asgmt3_BaseballElimination;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


import java.util.HashMap;
import java.util.Stack;

public class BaseballElimination {

    private final int n;
    private final HashMap<String, Integer> teamHashMap;
    private final String[] teams;
    private final int[] wins;
    private final int[] losses;
    private final int[] remaining;
    private final int[][] against;


    public BaseballElimination(String filename) {
        if (filename == null)
            throw new IllegalArgumentException();
        In input = new In(filename);
        n = input.readInt();

        teamHashMap = new HashMap<>();
        teams = new String[n];
        wins = new int[n];
        losses = new int[n];
        remaining = new int[n];
        against = new int[n][n];

        for (int i = 0; i < n; i++) {
            String teamName = input.readString();

            teamHashMap.put(teamName, i);
            teams[i] = teamName;
            wins[i] = input.readInt();
            losses[i] = input.readInt();
            remaining[i] = input.readInt();

            for (int j = 0; j < n; j++)
                against[i][j] = input.readInt();

        }
    }

    public int numberOfTeams() {
        return n;
    }

    private void validate(String team){
        if (!teamHashMap.containsKey(team))
            throw new IllegalArgumentException();
    }
    public Iterable<String> teams() {
        return teamHashMap.keySet();
    }

    public int wins(String team) {
        validate(team);
        return wins[teamHashMap.get(team)];

    }

    public int losses(String team) {
        validate(team);
        return losses[teamHashMap.get(team)];

    }

    public int remaining(String team) {
        validate(team);
        return remaining[teamHashMap.get(team)];
    }

    public int against(String team1, String team2) {
        validate(team1);
        validate(team2);
        return against[teamHashMap.get(team1)][teamHashMap.get(team2)];
    }

    public boolean isEliminated(String team) {
//        validate(team);
//        int x = teamHashMap.get(team);
//        for (int i = 0; i < n; i++) {
//            if (wins[x] + remaining[x] - wins[i] < 0) {
//                return true;
//            }
//        }
//
//        int totalCapacity = 0;
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = i + 1; j < n; j++) {
//                if (i == x || j == x)
//                    continue;
//                totalCapacity += against[i][j];
//            }
//        }
//
//        return getMaxFlow(x).value() < totalCapacity;
        return certificateOfElimination(team) != null;

    }

    private FordFulkerson getMaxFlow(int x) {
        int vertices = n + n * (n - 1) / 2 + 2;
        int s = vertices - 2;
        int t = vertices - 1;

        FlowNetwork flowNetwork = new FlowNetwork(vertices);
        for (int i = 0, index = n - 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                index++;
                if (i == x || j == x)
                    continue;
                FlowEdge sToMatch = new FlowEdge(s, index, against[i][j]);
                FlowEdge matchTeam1 = new FlowEdge(index, i, Double.POSITIVE_INFINITY);
                FlowEdge matchTeam2 = new FlowEdge(index, j, Double.POSITIVE_INFINITY);

                flowNetwork.addEdge(sToMatch);
                flowNetwork.addEdge(matchTeam1);
                flowNetwork.addEdge(matchTeam2);
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == x)
                continue;
            FlowEdge flowEdge = new FlowEdge(i, t, wins[x] + remaining[x] - wins[i]);
            flowNetwork.addEdge(flowEdge);
        }
        return new FordFulkerson(flowNetwork, s, t);
    }

    public Iterable<String> certificateOfElimination(String team) {
        validate(team);
        int x = teamHashMap.get(team);
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (wins[x] + remaining[x] - wins[i] < 0) {
                stack.push(teams[i]);
            }
        }
        if (!stack.empty())
            return stack;

        for (int i = 0; i < n; i++) {
            if (getMaxFlow(x).inCut(i))
                stack.push(teams[i]);
        }
        if (stack.isEmpty())
            return null;
        return stack;
    }

    public static void main(String[] args) {

        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
