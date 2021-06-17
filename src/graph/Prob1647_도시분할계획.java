package graph;

import java.io.*;
import java.util.*;

public class Prob1647_도시분할계획 {
    static int N, M;
    static int[] parent;
    //    static ArrayList<Node>[] graph;
//
//    static class Node {
//        int vertex, dist;
//
//        Node(int vertex, int dist) {
//            this.vertex = vertex;
//            this.dist = dist;
//        }
//    }
    static Edge[] edgeList;

    static class Edge {
        int a, b, dist;

        Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
//        graph = new ArrayList[N + 1];
//        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        edgeList = new Edge[M];
        int a, b, dist;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(a, b, dist);
//            graph[a].add(new Node(b, dist));
//            graph[b].add(new Node(a, dist));
        }
        Arrays.sort(edgeList, (o1, o2) -> o1.dist - o2.dist);
        int cnt = 0, max = 0, ans = 0;
        for (Edge edge : edgeList)
            if (union(edge.a, edge.b)) {
                ans += edge.dist;
                max = Math.max(max, edge.dist);
                if (++cnt == N - 1) break;
            }
        System.out.print(ans - max);
//        System.out.print(prim());
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        else if (aRoot > bRoot) parent[bRoot] = aRoot;
        else parent[aRoot] = bRoot;
        return true;

    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

//    public static int prim() {
//        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
//        pq.offer(new int[]{1, 0});
//
//        int max = 0;
//        int cnt = 0, res = 0;
//        boolean[] visit = new boolean[N + 1];
//        int[] curr;
//        while (!pq.isEmpty()) {
//            curr = pq.poll();
//            if (visit[curr[0]]) continue;
//            visit[curr[0]] = true;
//            max = Math.max(max, curr[1]);
//            res += curr[1];
//            for (Node node : graph[curr[0]])
//                if (!visit[node.vertex]) pq.offer(new int[]{node.vertex, node.dist});
//            if (++cnt == N) break;
//        }
//        return res - max;
//    }
}
