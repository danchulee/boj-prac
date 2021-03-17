package simulation;

import java.io.*;
import java.util.*;

public class Prob14500_테트로미노 {
    static int N, M;
    static int[][] tetris = {
            {6, 7, 8}, {0, 1, 3}, {6, 9, 10}, {9, 12, 13}, {6, 7, 9}, {6, 10, 13}, {6, 7, 5},
            {2, 4, 6}, {6, 7, 11}, {6, 9, 12}, {9, 10, 11}, {9, 10, 13}, {4, 5, 6}, {4, 6, 9},
            {6, 10, 11}, {6, 7, 10}, {4, 6, 10}, {4, 6, 7}, {9, 10, 12}
    };
    static int[] dx = {-3, -2, -2, -1, -1, -1, 0, 0, 0, 1, 1, 1, 2, 2};
    static int[] dy = {0, 0, 1, 0, 1, 2, 1, 2, 3, 0, 1, 2, 0, 1};
    static int[][] map;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int ni, nj, sum, answer = 0;
        boolean possible;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 19; k++) {
                    sum = map[i][j];
                    possible = true;
                    for (int m = 0; possible && m < tetris[k].length; m++) {
                        ni = i + dx[tetris[k][m]];
                        nj = j + dy[tetris[k][m]];
                        if (ni < 0 || nj < 0 || ni >= N || nj >= M)
                            possible = false;
                        else sum += map[ni][nj];
                    }
                    if (possible)
                        answer = Math.max(answer, sum);
                }
            }
        }
        System.out.print(answer);
    }
}
