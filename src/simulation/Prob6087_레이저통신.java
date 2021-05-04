package simulation;

import java.io.*;
import java.util.*;

public class Prob6087_레이저통신 {
    static int W, H;
    static int[] start, dest;
    static char[][] map;
    static int[][][] visit;
    static final int[] mirr = {1, 0, 3, 2, -1};
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static ArrayList<int[]> laser = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visit = new int[4][H][W];

        String tmp;
        for (int i = 0; i < H; i++) {
            tmp = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'C') laser.add(new int[]{i, j});
            }
        } // 꺾는 횟수 세기
        start = laser.get(0);
        dest = laser.get(1);
        System.out.print(bfs());
    }

    public static int bfs() {
        int[] curr;
        int nr, nc, nm;

        int turned = Integer.MAX_VALUE;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            Arrays.fill(visit[0][i], Integer.MAX_VALUE);
            Arrays.fill(visit[1][i], Integer.MAX_VALUE);
            Arrays.fill(visit[2][i], Integer.MAX_VALUE);
            Arrays.fill(visit[3][i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < 4; i++) {
            visit[i][start[0]][start[1]] = 0;
            nr = start[0] + dr[i];
            nc = start[1] + dc[i];
            if (nr >= 0 && nc >= 0 && nr < H && nc < W && map[nr][nc] != '*') {
                q.offer(new int[]{nr, nc, 0, i});
                visit[i][nr][nc] = 0;
            }
        }
        while (!q.isEmpty()) {
            curr = q.poll();
            for (int i = 0; i < 4; i++) {
                nr = curr[0] + dr[i];
                nc = curr[1] + dc[i];
                nm = curr[3] == i ? curr[2] : curr[2] + 1;
                if (nr < 0 || nc < 0 || nr >= H || nc >= W || map[nr][nc] == '*'
                        || visit[i][nr][nc] <= nm || nm >= turned || i == mirr[curr[3]]) continue;
                if (nr == dest[0] && nc == dest[1]) {
                    turned = nm;
                } else {
                    visit[i][nr][nc] = nm;
                    q.offer(new int[]{nr, nc, nm, i});
                }
            }
        }
        return turned;
    }
}
