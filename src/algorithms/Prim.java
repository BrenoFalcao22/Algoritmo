package algorithms;

import java.util.*;

public class Prim {

    public static int minimumSpanningTree(Graph graph) {
        int vertices = graph.getVertices();
        boolean[] inMST = new boolean[vertices];
        int[] key = new int[vertices];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{0, 0});

        int mstCost = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (inMST[u]) continue;
            inMST[u] = true;
            mstCost += current[1];

            for (Graph.Edge edge : graph.getEdges()) {
                if (edge.src == u && !inMST[edge.dest] && edge.weight < key[edge.dest]) {
                    key[edge.dest] = edge.weight;
                    pq.add(new int[]{edge.dest, key[edge.dest]});
                }
            }
        }
        return mstCost;
    }
}

