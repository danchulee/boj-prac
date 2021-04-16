package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob7576_토마토2 {
    static int M, N;
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int tomatos = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) q.offer(new int[]{i, j});
                else if (map[i][j] == 0) tomatos++;
            }
        }
        if (tomatos > 0)
            System.out.println(bfs(tomatos));
        else System.out.print(0);
    }

    public static int bfs(int tomatos) {
        int size, nx, ny, count = -1;
        int[] curr;
        while (!q.isEmpty()) {
            count++;
            size = q.size();
            while (size-- > 0) {
                curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    nx = curr[0] + dx[i];
                    ny = curr[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 0) continue;
                    tomatos--;
                    map[nx][ny] = 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return tomatos == 0 ? count : -1;
    }
}
