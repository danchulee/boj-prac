package graph;

import java.io.*;
import java.util.*;

public class Prob1717_집합의표현 {
    static int N, M;
    static int[] root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        root = new int[N + 1];
        for (int i = 0; i <= N; i++) root[i] = i;

        StringBuilder sb = new StringBuilder();
        int sel, a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sel = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (sel == 0) union(a, b);
            else if (find(a) == find(b)) sb.append("YES").append('\n');
            else sb.append("NO").append('\n');
        }
        System.out.println(sb);
    }

    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return;
        if (aRoot < bRoot) root[bRoot] = aRoot;
        else root[aRoot] = bRoot;
    }

    public static int find(int a) {
        if (root[a] == a) return a;
        return root[a] = find(root[a]);
    }
}
