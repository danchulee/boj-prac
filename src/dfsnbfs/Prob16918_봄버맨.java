import java.io.*;
import java.util.*;

public class Prob16918_봄버맨 {
    static int R, C, N;
    static char[][] map;
    static Queue<int[]> bombs = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O')
                    bombs.offer(new int[]{i, j});
            }
        } // end of Input
        bfs();
        for (char[] chs : map) {
            for (char ch : chs)
                sb.append(ch);
            sb.append("\n");
        }
        System.out.print(sb);

    } // end of Main발

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    public static void bfs() {
        int nx, ny, time = 1;
        while (time != N) {
            for (int i = 0; i < R; i++)
                Arrays.fill(map[i], 'O');
            if (++time == N) break; // 폭탄 심음 1초 경과
            while (!bombs.isEmpty()) {
                int[] bomb = bombs.poll();
                map[bomb[0]][bomb[1]] = '.';
                for (int i = 0; i < 4; i++) {
                    nx = bomb[0] + dx[i];
                    ny = bomb[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    map[nx][ny] = '.';
                }
            }
            time++; // 폭탄 터짐 1초 경과
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    if (map[i][j] == 'O')
                        bombs.offer(new int[]{i, j});
        }
    }
} // end of Class
