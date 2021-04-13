package graph;

import java.io.*;
import java.util.*;

public class Prob2458_키순서 {
    static int N, M;
    static int[][] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];

        int a, b;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 2;
        }
        int answer = 0;
        // 본인보다 큰 거 ㄱㅊ      (경은 출보다 커도 됨)
        // 본인보다 큰 거의 큰 거 ㄱㅊ(도는 출보다 커도 됨)
        // 본인보다 작은 거 ㄱㅊ     (경은 출보다 작아도 됨)
        // 본인보다 작은 거의 작은 거 ㄱㅊ (도는 출보다 작아도 안 됨)
        for (int k = 1; k <= N; k++)  // 경
            for (int i = 1; i <= N; i++)  // 출
                for (int j = 1; j <= N; j++)  // 도
                    if (dist[i][k] == 1 && dist[k][j] == 1)
                        dist[i][j] = 1;
                    else if(dist[i][k] == 2 && dist[k][j] == 2)
                        dist[i][j] = 2;

        boolean fine;
        for (int i = 1; i <= N; i++) {
            fine = true;
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == INF) {
                    fine = false;
                    break;
                }
            }
            if (fine) answer++;
        }
        System.out.print(answer);
    }
}
