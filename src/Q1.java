import java.util.*;

public class Q1 {

    static final int V = 7;

    // BFS for finding augmenting path
    static boolean bfs(int[][] rGraph, int s, int t,
                       int[] parent) {

        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int v = 0; v < V; v++) {

                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[t];
    }

    // DFS to find reachable vertices
    static void dfs(int[][] rGraph,
                    int s,
                    boolean[] visited) {

        visited[s] = true;

        for (int i = 0; i < V; i++) {
            if (rGraph[s][i] > 0 && !visited[i])
                dfs(rGraph, i, visited);
        }
    }

    static void edmondsKarp(int[][] graph,
                            int source,
                            int sink) {

        int[][] rGraph = new int[V][V];

        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                rGraph[i][j] = graph[i][j];

        int[] parent = new int[V];

        int maxFlow = 0;

        while (bfs(rGraph, source, sink, parent)) {

            int pathFlow = Integer.MAX_VALUE;

            for (int v = sink; v != source;
                 v = parent[v]) {

                int u = parent[v];

                pathFlow =
                        Math.min(pathFlow,
                                rGraph[u][v]);
            }

            for (int v = sink; v != source;
                 v = parent[v]) {

                int u = parent[v];

                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        System.out.println(
                "Maximum Flow = " + maxFlow);

        boolean[] visited =
                new boolean[V];

        dfs(rGraph, source, visited);

        char[] vertex =
                {'A','B','C','D','E','F','G'};

        System.out.println(
                "Minimum Cut Edges:");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {

                if (visited[i]
                        && !visited[j]
                        && graph[i][j] > 0) {

                    System.out.println(
                            vertex[i] +
                                    " -> " +
                                    vertex[j]);
                }
            }
        }
    }

    public static void main(String[] args) {

        int[][] graph =
                new int[V][V];

        // A=0 B=1 C=2 D=3 E=4 F=5 G=6

        graph[0][3] = 3; // A-D
        graph[0][1] = 3; // A-B
        graph[1][2] = 4; // B-C
        graph[2][0] = 3; // C-A
        graph[2][3] = 1; // C-D
        graph[2][4] = 2; // C-E
        graph[3][5] = 6; // D-F
        graph[3][4] = 2; // D-E
        graph[4][1] = 1; // E-B
        graph[4][6] = 1; // E-G
        graph[5][6] = 9; // F-G

        edmondsKarp(graph, 0, 6);
    }
}