import java.util.*;

public class Q3 {

    static final int V = 9;

    int minDistance(int dist[], boolean sptSet[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void printTable(int dist[], int parent[],
                    boolean visited[], int step) {

        System.out.println("\nSTEP " + step);

        System.out.print("v\t");
        for (int i = 0; i < V; i++)
            System.out.print(i + "\t");

        System.out.println();

        System.out.print("d[v]\t");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.print("∞\t");
            else
                System.out.print(dist[i] + "\t");
        }

        System.out.println();

        System.out.print("pred\t");
        for (int i = 0; i < V; i++) {
            if (parent[i] == -1)
                System.out.print("nil\t");
            else
                System.out.print(parent[i] + "\t");
        }

        System.out.println();

        System.out.print("color\t");
        for (int i = 0; i < V; i++) {
            if (visited[i])
                System.out.print("B\t");
            else
                System.out.print("W\t");
        }

        System.out.println("\n");
    }

    void dijkstra(int graph[][], int src) {

        int dist[] = new int[V];
        int parent[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[src] = 0;

        int step = 0;

        printTable(dist, parent, visited, step);

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);

            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }

            step++;
            printTable(dist, parent, visited, step);
        }

        System.out.println("Shortest Path Tree:");

        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " -> " + i);
        }
    }

    public static void main(String[] args) {

        int graph[][] = {
                {0,4,0,0,0,0,0,8,0},
                {4,0,8,0,0,0,0,11,0},
                {0,8,0,7,0,4,0,0,2},
                {0,0,7,0,9,14,0,0,0},
                {0,0,0,9,0,10,0,0,0},
                {0,0,4,14,10,0,2,0,0},
                {0,0,0,0,0,2,0,1,6},
                {8,11,0,0,0,0,1,0,7},
                {0,0,2,0,0,6,0,7,0}
        };

        Q3 d = new Q3();
        d.dijkstra(graph, 0);
    }
}
