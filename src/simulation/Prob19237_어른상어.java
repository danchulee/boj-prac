package simulation;

import java.io.*;
import java.util.*;

public class Prob19237_어른상어 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int N, M, K;
    static int[][] map;
    static Shark[] sharks;

    static class Shark {
        int dir, x, y;
        int[][] seq = new int[4][4];

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        input();


    }

    public static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        sharks = new Shark[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0)
                    sharks[map[i][j]] = new Shark(i, j);
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            sharks[i].dir = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++)
                    sharks[i].seq[j][k] = Integer.parseInt(st.nextToken());
            }
        }
    } // end of input
}
