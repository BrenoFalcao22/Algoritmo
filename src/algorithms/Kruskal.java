package algorithms;


import java.util.*;

public class Kruskal {

    public static List<Graph.Edge> minimumSpanningTree(Graph graph) {
        List<Graph.Edge> result = new ArrayList<>();
        Collections.sort(graph.getEdges(), Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[graph.getVertices()];
        for (int i = 0; i < graph.getVertices(); i++) {
            parent[i] = i;
        }

        for (Graph.Edge edge : graph.getEdges()) {
            int x = find(parent, edge.src);
            int y = find(parent, edge.dest);
            if (x != y) {
                result.add(edge);
                union(parent, x, y);
            }
        }
        return result;
    }

    private static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private static void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        parent[xRoot] = yRoot;
    }
}