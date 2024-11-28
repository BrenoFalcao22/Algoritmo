package algorithms;

import java.util.*;

public class MaxFlow {

    // Algoritmo de Edmonds-Karp para calcular o fluxo máximo
    public static int edmondsKarp(int[][] capacity, int source, int sink) {
        int n = capacity.length;
        int[][] flow = new int[n][n]; // Matriz de fluxo (inicializada com 0)

        int maxFlow = 0;

        // Enquanto houver um caminho aumentante
        while (true) {
            // Encontrar o caminho aumentante usando BFS
            int[] parent = bfs(capacity, flow, source, sink);
            
            // Se não houver caminho aumentante, o fluxo máximo foi encontrado
            if (parent == null) {
                break;
            }

            // Encontrar a capacidade do caminho aumentante
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }

            // Atualizar o fluxo para cada aresta no caminho aumentante
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow; // Fluxo reverso
            }

            // Adicionar o fluxo do caminho aumentante ao fluxo máximo
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    // Função para realizar uma busca em largura (BFS) no grafo
    private static int[] bfs(int[][] capacity, int[][] flow, int source, int sink) {
        int n = capacity.length;
        int[] parent = new int[n];
        Arrays.fill(parent, -1); // Inicializa todos os pais como -1 (nenhum caminho)

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        parent[source] = -2; // Marca o nó de origem com um valor especial

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < n; v++) {
                // Se o nó 'v' ainda não foi visitado e há capacidade residual
                if (parent[v] == -1 && capacity[u][v] - flow[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;

                    // Se chegamos no nó de destino, podemos parar
                    if (v == sink) {
                        return parent;
                    }
                }
            }
        }

        return null; // Se não encontrar um caminho aumentante
    }
}