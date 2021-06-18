package simulation;

import java.io.*;
import java.util.*;

public class Prob17135_캐슬디펜스2 {
    static int N, M, D;
    static int[][] map, copyMap;
    static int ans;

    static class Enemy {
        int r, c;

        Enemy(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Enemy enemy = (Enemy) o;
            return r == enemy.r && c == enemy.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        putArcher(0, 0, 0);
        System.out.print(ans);
    }

    public static void putArcher(int cnt, int index, long pick) {
        if (cnt == 3) {
            int tmp = 0;
            int[] position = new int[3];
            for (int i = 0; i < M; i++)
                if ((pick & (1L << i)) != 0) position[tmp++] = i;
            for (int i = 0; i < N; i++)
                System.arraycopy(map[i], 0, copyMap[i], 0, M);

            int kill = 0;
            while (!clear()) {
                kill += attack(position);
                enemyMove();
            }
            ans = Math.max(ans, kill);
            return;
        }
        for (int i = index; i < M; i++)
            putArcher(cnt + 1, i + 1, pick | (1L << i));
    }

    public static int attack(int[] archers) {
        int dist, min;
        HashSet<Enemy> set = new HashSet<>();
        ArrayList<Enemy> tmp = new ArrayList<>();
        for (int archer : archers) {
            tmp.clear();
            min = Integer.MAX_VALUE;
            for (int c = 0; c < M; c++) {
                for (int r = N - 1; r >= 0; r--) {
                    if (copyMap[r][c] == 0) continue;
                    dist = Math.abs(N - r) + Math.abs(archer - c);
                    if (dist > D || dist >= min) continue;
                    min = Math.min(dist, min);
                    tmp.add(new Enemy(r, c));
                }
            }
            tmp.sort((o1, o2) -> {
                int dist1 = Math.abs(N - o1.r) + Math.abs(archer - o1.c);
                int dist2 = Math.abs(N - o2.r) + Math.abs(archer - o2.c);
                if (dist1 != dist2) return dist1 - dist2;
                else return o1.c - o2.c;
            });
            if (!tmp.isEmpty()) set.add(tmp.get(0));
        }
        for (Enemy enemy : set)
            copyMap[enemy.r][enemy.c] = 0;
        return set.size();
    }

    public static boolean clear() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (copyMap[i][j] == 1) return false;
        return true;
    }

    public static void enemyMove() {
        for (int i = N - 1; i > 0; i--)
            System.arraycopy(copyMap[i - 1], 0, copyMap[i], 0, M);
        Arrays.fill(copyMap[0], 0);
    }
}
