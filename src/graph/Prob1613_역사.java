package graph;

import java.io.*;
import java.util.*;

public class Prob1613_역사 {
    static int N, K;
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        int a, b;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            graph[a][b] = -1;
            graph[b][a] = 1;
        }
        for (int j = 0; j < N; j++) //경
            for (int i = 0; i < N; i++) //출
                for (int k = 0; k < N; k++) //도
                    if (graph[i][j] == 1 && graph[j][k] == 1) graph[i][k] = 1;
                    else if (graph[i][j] == -1 && graph[j][k] == -1) graph[i][k] = -1;
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(graph[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]).append('\n');
        }
        System.out.print(sb);
    }
}
