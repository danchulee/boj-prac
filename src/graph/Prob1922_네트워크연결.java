package graph;

import java.io.*;
import java.util.*;

public class Prob1922_네트워크연결 {
    static int N, M, answer;
    static int[] parents;
    //static ArrayList<Edge> edgeList = new ArrayList<>();
    static PriorityQueue<Edge> edgeList = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

    static class Edge {
        int from, to, dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N];
        for (int i = 0; i < N; i++) parents[i] = i;

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
            if (a == b) continue;
            edgeList.add(new Edge(a, b, c));
        }

        Edge edge;
        while (!edgeList.isEmpty()) {
            edge = edgeList.poll();
            if (union(edge.from, edge.to))
                answer += edge.dist;
        }

        System.out.print(answer);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static int find(int a) {
        if (a == parents[a]) return a;
        else return parents[a] = find(parents[a]);
    }
}
