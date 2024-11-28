package algorithms;

import java.util.*;

public class Dijkstra {

    public static int shortestPathCost(Graph graph, int source, int target) {
        int vertices = graph.getVertices();
        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (visited[u]) continue;
            visited[u] = true;

            for (Graph.Edge edge : graph.getEdges()) {
                if (edge.src == u) {
                    int v = edge.dest;
                    int weight = edge.weight;
                    if (!visited[v] && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new int[]{v, dist[v]});
                    }
                }
            }
        }
        return dist[target];
    }
}