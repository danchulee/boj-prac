package graph;

import java.io.*;
import java.util.*;

public class Prob1916_최소비용구하기 {
    static int N, M;
    static int start, end;
    static ArrayList<Node>[] graph;
    static final int INF = Integer.MAX_VALUE;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

    static class Node {
        int vertex, dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        int from, to, dist;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            dist = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, dist));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        System.out.print(dijkstra());
    }

    public static int dijkstra() {
        int[] curr, distance = new int[N];
        boolean[] visit = new boolean[N];

        Arrays.fill(distance, INF);
        distance[start] = 0;
        pq.offer(new int[]{start, 0});
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (visit[curr[0]])
                continue;
            visit[curr[0]] = true;
            if (curr[0] == end)
                break;
            for (Node node : graph[curr[0]]) {
                if (!visit[node.vertex] && curr[1] + node.dist < distance[node.vertex]) {
                    distance[node.vertex] = curr[1] + node.dist;
                    pq.offer(new int[]{node.vertex, distance[node.vertex]});
                }
            }
        }
        return distance[end];
    }
}
