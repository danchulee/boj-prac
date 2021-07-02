package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob15591_Mootube {
    static int N, Q;
    static ArrayList<Node>[] graph;

    static class Node {
        int vertex;
        long dist;

        Node(int vertex, long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    // v로부터 k이하 거리에 있는 정점의 개수
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        int a, b;
        long cost;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cost = Long.parseLong(st.nextToken());
            graph[a].add(new Node(b, cost));
            graph[b].add(new Node(a, cost));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(canGo(Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
        }
        System.out.print(sb);
    }

    public static int canGo(long limit, int num) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visit = new boolean[N + 1];
        visit[num] = true;

        int ret = 0;
        for (Node node : graph[num])
            if (node.dist >= limit) {
                visit[node.vertex] = true;
                q.offer(node);
            }

        Node curr;
        while (!q.isEmpty()) {
            ret++;
            curr = q.poll();
            for (Node node : graph[curr.vertex]) {
                if (visit[node.vertex] || node.dist < limit) continue;
                visit[node.vertex] = true;
                q.offer(new Node(node.vertex, Math.min(curr.dist, node.dist)));
            }
        }
        return ret;
    }
}
