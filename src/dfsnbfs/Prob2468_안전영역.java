package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob2468_안전영역 {
    static int[][] map;
    static boolean[][] visit;
    static int N, ans = 1;
    static HashSet<Integer> rains = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                rains.add(map[i][j]);
            }
        }
        int cnt;
        for (int rain : rains) {
            cnt = 0;
            for (int i = 0; i < N; i++) Arrays.fill(visit[i], false);
            for (int i = 0; i < N; i++) // 안전영역 구함 (잠기는 영역 구하는 게 아님)
                for (int j = 0; j < N; j++)
                    if (!visit[i][j] && map[i][j] > rain) {
                        cnt++;
                        bfs(i, j, rain);
                    }
            ans = Math.max(ans, cnt);
        }
        System.out.print(ans);
    }

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void bfs(int r, int c, int rain) {
        Queue<int[]> q = new LinkedList<>();
        visit[r][c] = true;
        q.offer(new int[]{r, c});

        int[] curr;
        int nr, nc;
        while (!q.isEmpty()) {
            curr = q.poll();
            for (int d = 0; d < 4; d++) {
                nr = curr[0] + dr[d];
                nc = curr[1] + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || map[nr][nc] <= rain) continue;
                visit[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
    }

}
