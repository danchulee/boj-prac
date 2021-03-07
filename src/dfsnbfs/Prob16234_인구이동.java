package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob16234_인구이동 {
    static int N, L, R, moves;
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        } // end of Input
        merge();
        System.out.print(moves);
    }

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void merge() {
        int nx, ny, value, sum, cnt;
        int[] pos;
        while ((sum = findStart()) != -1) {
            moves++;
            cnt = 1;
            while (!q.isEmpty()) {
                pos = q.poll();
                for (int i = 0; i < 4; i++) {
                    nx = pos[0] + dx[i];
                    ny = pos[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny]) continue;
                    value = Math.abs(map[pos[0]][pos[1]] - map[nx][ny]);
                    if (value >= L && value <= R) {
                        visit[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        sum += map[nx][ny];
                        cnt++;
                    }
                }
            } // end of find merge point
            move(sum / cnt);

            for (int i = 0; i < N; i++)
                Arrays.fill(visit[i], false);
        } // end of turn
    } // end of merge

    public static int findStart() { //하나만 찾서 넣아ㅇ
        int value, nx, ny;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < 4; k++) {
                    nx = i + dx[k];
                    ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    value = Math.abs(map[i][j] - map[nx][ny]);
                    if (value >= L && value <= R) {
                        visit[i][j] = true;
                        q.offer(new int[]{i, j});
                        return map[i][j];
                    }
                }
        return -1;
    }

    public static void move(int div) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (visit[i][j])
                    map[i][j] = div;
    }
}
