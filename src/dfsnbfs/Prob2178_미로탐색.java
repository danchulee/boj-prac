package dfsnbfs;
import java.io.*;
import java.util.*;

public class Prob2178_미로탐색 {
    static int N, M;
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 1) map[i][j] = -1;
            }
        }
        bfs();
        System.out.print(map[N - 1][M - 1]);
    }

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void bfs() {
        map[0][0] = 1;
        q.offer(new int[]{0, 0});

        boolean found = false;
        int nx, ny;
        while (!found && !q.isEmpty()) {
            int[] pos = q.poll();
            if (map[pos[0]][pos[1]] == 0) continue;
            for (int d = 0; d < 4; d++) {
                nx = pos[0] + dx[d];
                ny = pos[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 0) continue;
                map[nx][ny] = map[pos[0]][pos[1]] + 1;
                if (nx == N - 1 && ny == M - 1) {
                    found = true;
                    break;
                }
                q.offer(new int[]{nx, ny});
            }
            map[pos[0]][pos[1]] = 0;
        }
    }
}
