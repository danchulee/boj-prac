package graph;

import java.io.*;
import java.util.*;

public class Prob1753_최단경로 {
    static int V, E, K;
    static Node[] map;
    static boolean[] visit;
    static int[] distance;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);


    static class Node {
        int vertex;
        int distance;
        Node next;

        public Node(int vertex, int distance, Node next) {
            this.vertex = vertex;
            this.distance = distance;
            this.next = next;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new Node[V];
        distance = new int[V];
        visit = new boolean[V];
        K = Integer.parseInt(br.readLine());

        int from, to, dist;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            dist = Integer.parseInt(st.nextToken());
            map[from] = new Node(to, dist, map[from]);
        }
        int start = K - 1;
        int end = V - 1;
        Arrays.fill(distance, Integer.MAX_VALUE);

        dijkstra(start, end);

        for (int i = 0; i < V; i++)
            sb.append(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]).append("\n");

        System.out.print(sb);
    }

    public static void dijkstra(int start, int end) {
        distance[start] = 0;
        pq.offer(new int[]{start, 0});

        int[] now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if(visit[now[0]]) continue;
//            if (now[0] == end)
//                break;
            visit[now[0]] = true;
            for (Node node = map[now[0]]; node != null; node = node.next) {
                if (!visit[node.vertex] && distance[node.vertex] > now[1] + node.distance) {
                    distance[node.vertex] = now[1] + node.distance;
                    pq.add(new int[]{node.vertex, distance[node.vertex]});
                }
            }
        }
    }
}
