package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob4179_불 {
    static int R, C;
    static char[][] map;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static Queue<int[]> fires = new LinkedList<>();
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'F') {
                    fires.offer(new int[]{i, j});
                } else if (map[i][j] == 'J') {
                    q.offer(new int[]{i, j});
                }
            }
        } // end of input

        //firespread에서 j있으면 그냥 불로 덮어버림, 따라서 나왔는데 그 자리가 f면 걍 continue
        boolean possible = false;
        int size, nx, ny, time = 0;
        int[] pos;
        while (!possible && !q.isEmpty()) {
            time++;
            size = q.size();
            while (!possible && size-- > 0) {
                pos = q.poll();
                if (map[pos[0]][pos[1]] == 'F') continue;
                if (pos[0] == R - 1 || pos[1] == C - 1 || pos[0] == 0 || pos[1] == 0) {
                    possible = true;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    nx = pos[0] + dx[i];
                    ny = pos[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'J';
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
            fireSpread();
        }
//        if (possible)
//            System.out.print(time);
//        else System.out.print("IMPOSSIBLE");
        System.out.println(possible ? time : "IMPOSSIBLE");

    } // end of main

    public static void fireSpread() {
        int nx, ny, size = fires.size();
        int[] fire;
        while (size-- > 0) {
            fire = fires.poll();
            for (int i = 0; i < 4; i++) {
                nx = fire[0] + dx[i];
                ny = fire[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == '#' || map[nx][ny] == 'F') continue;
                map[nx][ny] = 'F';
                fires.offer(new int[]{nx, ny});
            }
        }
    } // end of fireSpread
} // end of class
