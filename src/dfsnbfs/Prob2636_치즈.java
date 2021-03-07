package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob2636_치즈 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int[][] map;

    static Queue<int[]> cheese = new LinkedList<>();

    static int size, time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < tmp.length(); j += 2)
                map[i][j / 2] = tmp.charAt(j) - '0';
        } // end of input
        bfs();
        System.out.println(time);
        System.out.println(size);
    }

    public static void bfs() {
        int nx, ny;
        cheeseAir();
        while (!cheese.isEmpty()) {
            size = cheese.size();
            time++;
            for (int i = 0; i < size; i++) {
                int[] pos = cheese.poll();
                for (int j = 0; j < 4; j++) {
                    nx = pos[0] + dx[j];
                    ny = pos[1] + dy[j];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 1) continue;
                    map[nx][ny] = 2;
                    cheese.offer(new int[]{nx, ny});
                }
            }
            cheeseAir();
        }
    }

    public static void cheeseAir() {
        Queue<int[]> outside = new LinkedList<>();
        outside.offer(new int[]{0, 0});
        map[0][0] = 2;

        int nx, ny;
        while (!outside.isEmpty()) {
            int[] pos = outside.poll();
            for (int i = 0; i < 4; i++) {
                nx = pos[0] + dx[i];
                ny = pos[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 2) continue;
                if (map[nx][ny] == 0)
                    outside.offer(new int[]{nx, ny});
                else cheese.offer(new int[]{nx, ny});
                map[nx][ny] = 2;
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 2) map[i][j] = 0;
    }

}
