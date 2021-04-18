package graph;

import java.io.*;
import java.util.*;


// 먼저 만들어진 길은 가중치 0으로, 해서 프림 돌리기
public class Prob1774_우주신과의교감 {
    static int N, M;
    static int[][] pos;
    static boolean[][] made;
    static ArrayList<Node>[] graph;

    static class Node {
        int vertex;
        double distance;

        Node(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pos = new int[N + 1][2];
        made = new boolean[N + 1][N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, 0));
            graph[to].add(new Node(from, 0));
            made[from][to] = made[to][from] = true;
        }

        double dist;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                if (i != j && !made[i][j]) {
                    made[i][j] = made[j][i] = true;
                    dist = distance(i, j);
                    graph[i].add(new Node(j, dist));
                    graph[j].add(new Node(i, dist));
                }

        System.out.printf("%.2f", prim());
    }

    public static double prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.distance, o2.distance));
        pq.offer(new Node(1, 0));
        boolean[] visit = new boolean[N + 1];

        double res = 0;
        int cnt = 0;
        Node curr;
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (visit[curr.vertex])
                continue;
            visit[curr.vertex] = true;
            res += curr.distance;
            for (Node node : graph[curr.vertex])
                if (!visit[node.vertex])
                    pq.offer(node);
            if (++cnt == N)
                break;
        }
        return res;
    }

    public static double distance(int p1, int p2) {
        return Math.sqrt((Math.pow(pos[p1][0] - pos[p2][0], 2)) + (Math.pow(pos[p1][1] - pos[p2][1], 2)));
    }
}
