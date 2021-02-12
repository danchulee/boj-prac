import java.io.*;
import java.util.*;

public class Prob16918 {
    static int R, C, N;
    static char[][] map;
    static int[][] state; // -1 빈칸, 0 이제 터짐, 1 대기
    static Queue<Bomb> bombs = new LinkedList<>();
    static Set<Bomb> wait = new HashSet<>();

    static class Bomb {
        int x, y;

        Bomb(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Bomb bomb = (Bomb) o;
            return x == bomb.x && y == bomb.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        state = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O') {
                    bombs.offer(new Bomb(i, j));
                    state[i][j] = 1;
                } else state[i][j] = -1;
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
        int nx, ny, size, time = 1;
        while (time != N) {
            size = bombs.size(); // 폭탄 심기 전 존재하는 폭탄 사이
            plant();
            if (++time == N) break; // 폭탄 심음 1초 경과
            while (size-- > 0) {
                Bomb bomb = bombs.poll();
                map[bomb.x][bomb.y] = '.';
                for (int i = 0; i < 4; i++) {
                    nx = bomb.x + dx[i];
                    ny = bomb.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    wait.remove(new Bomb(nx, ny));
                    map[nx][ny] = '.'; // 터진 거 처리해줘야 한다.. bombs에 들어있는 좌표중에 이 과정에서 터져버린 폭탄이 있기 때문
                }
            }
            time++; // 폭탄 터짐 1초 경과
            for (Bomb bomb : wait)
                bombs.offer(bomb);
            wait.clear();
        }
    }

    public static void plant() {
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    wait.add(new Bomb(i, j));
                    map[i][j] = 'O';
                }
            }
    }
} // end of Class
