package graph;

import java.io.*;
import java.util.*;

public class Prob1647_도시분할계획 {
    static int N, M;
    static ArrayList<Node>[] graph;

    static class Node {
        int vertex, dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    // 결국 다 한붓그리기 처럼 연결되는... 그니까 그냥 가장 작은 mst 구하면 된다
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        int a, b, dist;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, dist));
            graph[b].add(new Node(a, dist));
        }
        System.out.print(prim());
    }

    public static int prim() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{1, 0});

        int max = 0;
        int cnt = 0, res = 0;
        boolean[] visit = new boolean[N + 1];
        int[] curr;
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (visit[curr[0]]) continue;
            visit[curr[0]] = true;
            max = Math.max(max, curr[1]);
            res += curr[1];
            for (Node node : graph[curr[0]])
                if (!visit[node.vertex]) pq.offer(new int[]{node.vertex, node.dist});
            if (++cnt == N) break;
        }
        return res - max;
    }
}
