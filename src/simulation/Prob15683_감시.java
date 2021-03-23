package simulation;

import java.io.*;
import java.util.*;

public class Prob15683_감시 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] dirs = {
            {{0}, {1}, {2}, {3}},
            {{1, 3}, {0, 2}},
            {{0, 1}, {1, 2}, {2, 3}, {0, 3}},
            {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}},
            {{0, 1, 2, 3}}
    };

    static int N, M, answer;
    static int[][] map;
    static ArrayList<Cam> cams = new ArrayList<>();

    static class Cam {
        int x, y, type;

        Cam(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5)
                    cams.add(new Cam(i, j, map[i][j] - 1));
            }
        }
        answer = count();
        if (cams.size() > 0)
            dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int camN) {
        if (camN == cams.size()) {
            answer = Math.min(answer, count());
            return;
        }
        int camType = cams.get(camN).type;
        int[][] tmpMap = new int[N][M];
        copyMap(map, tmpMap);
        for (int i = 0; i < dirs[camType].length; i++) {
            for (int j = 0; j < dirs[camType][i].length; j++)
                see(camN, dirs[camType][i][j]);
            dfs(camN + 1);
            copyMap(tmpMap, map);
        }
    }

    public static void see(int camN, int dir) {
        Cam cam = cams.get(camN);
        int nx = cam.x;
        int ny = cam.y;
        do {
            map[nx][ny] = -1;
            nx += dx[dir];
            ny += dy[dir];
        } while (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 6);
    }

    public static int count() {
        int res = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 0) res++;
        return res;
    }

    public static void copyMap(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++)
            System.arraycopy(from[i], 0, to[i], 0, M);
    }
}
