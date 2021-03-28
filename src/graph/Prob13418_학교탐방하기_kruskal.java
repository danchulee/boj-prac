package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Prob13418_학교탐방하기_kruskal {
    static int[] K = new int[2];
    static int N, M;
    static Edge[] edgeList;
    static int[] parents;

    static class Edge {
        int from, to;
        int upDown; // 0 오르막길 1 내리막길

        Edge(int from, int to, int upDown) {
            this.from = from;
            this.to = to;
            this.upDown = upDown;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new Edge[M + 1];
        parents = new int[N + 1];

        int A, B, C;
        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(A, B, C);
        }
        Edge edge;
        Arrays.sort(edgeList, (o1, o2) -> o1.upDown - o2.upDown);
        for (int i = 0; i <= N; i++) parents[i] = i;
        for (int i = 0; i < edgeList.length; i++) {
            edge = edgeList[i];
            if (union(edge.to, edge.from) && edge.upDown == 0) K[0]++;
        }
        for (int i = 0; i <= N; i++) parents[i] = i;
        for (int i = edgeList.length - 1; i >= 0; i--) {
            edge = edgeList[i];
            if (union(edge.to, edge.from) && edge.upDown == 0) K[1]++;
        }

        System.out.print(K[0] * K[0] - K[1] * K[1]);

    }

    public static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        if (bRoot > aRoot) parents[bRoot] = aRoot;
        else parents[aRoot] = bRoot;
        return true;
    }
}
