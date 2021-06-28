package graph;

import java.io.*;
import java.util.*;

public class Prob1261_알고스팟 {
    static int N, M;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        String tmp;
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = tmp.charAt(j) - '0';
        }
        System.out.print(dijkstra());
    }

    public static int dijkstra() {
        int[][] distance = new int[N][M];
        boolean[][] visit = new boolean[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(distance[i], Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        distance[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        int[] curr;
        int nr, nc;
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (curr[0] == N - 1 && curr[1] == M - 1) break;
            if (visit[curr[0]][curr[1]]) continue;
            visit[curr[0]][curr[1]] = true;
            for (int i = 0; i < 4; i++) {
                nr = curr[0] + dr[i];
                nc = curr[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] || distance[nr][nc] <= curr[2] + map[nr][nc])
                    continue;
                distance[nr][nc] = curr[2] + map[nr][nc];
                pq.offer(new int[]{nr, nc, curr[2] + map[nr][nc]});
            }
        }
        return distance[N - 1][M - 1];
    }
}
