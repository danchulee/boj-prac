package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob1600_말이되고픈원숭이 {
    static int K, W, H, answer;
    static int[][] map;
    static int[] dxJump = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dyJump = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Pos> q = new LinkedList<>();
    static int[][][] visit;

    static class Pos {
        int x, y, leftK;

        Pos(int x, int y, int leftK) {
            this.x = x;
            this.y = y;
            this.leftK = leftK;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visit = new int[2][H][W];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < H; j++)
                Arrays.fill(visit[i][j], -1);

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        if ((H == 1 && W == 1) || bfs()) System.out.print(answer);
        else System.out.print(-1);
    }

    public static boolean bfs() {
        q.add(new Pos(0, 0, K));
        map[0][0] = 1;

        int size, nx, ny;
        Pos curr;
        while (!q.isEmpty()) {
            size = q.size();
            answer++;
            while (size-- > 0) {
                curr = q.poll();
                if (curr.leftK > 0) {
                    for (int i = 0; i < 8; i++) {
                        nx = curr.x + dxJump[i];
                        ny = curr.y + dyJump[i];
                        if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 1 ||
                                (visit[1][nx][ny] != -1 && curr.leftK <= visit[1][nx][ny]))
                            continue;
                        if (nx == H - 1 && ny == W - 1) return true;
                        visit[1][nx][ny] = curr.leftK;
                        q.add(new Pos(nx, ny, curr.leftK - 1));
                    }
                }
                for (int i = 0; i < 4; i++) {
                    nx = curr.x + dx[i];
                    ny = curr.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 1 ||
                            (visit[0][nx][ny] != -1 && curr.leftK <= visit[0][nx][ny]))
                        continue;
                    if (nx == H - 1 && ny == W - 1) return true;
                    visit[0][nx][ny] = curr.leftK;
                    q.add(new Pos(nx, ny, curr.leftK));
                }
            }
        }
        return false;
    }
}
/*

3
8 8
0 0 1 1 1 1 1 1
1 1 1 0 0 0 1 1
1 1 1 1 1 0 1 1
0 0 1 1 1 0 1 1
0 1 1 0 0 0 1 1
0 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0

 */