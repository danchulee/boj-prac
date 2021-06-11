package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob1926_그림 {
    static int N, M;
    static int[][] map;
    static int big, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 1) big = Math.max(big, go(i, j));
        System.out.println(cnt);
        System.out.println(big);
    }

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static int go(int r, int c) {
        cnt++;
        map[r][c] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        int nr, nc, move = 0;
        int[] curr;
        while (!q.isEmpty()) {
            move++;
            curr = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = curr[0] + dr[i];
                nc = curr[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 0) continue;
                map[nr][nc] = 0;
                q.offer(new int[]{nr, nc});
            }
        }
        return move;
    }
}
