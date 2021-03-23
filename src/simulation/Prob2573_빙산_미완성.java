package simulation;
import java.io.*;
import java.util.StringTokenizer;

// dfs로 덩어리 개수 셀 수 있
public class Prob2573_빙산_미완성 {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visit;

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

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
        } // end of input

        do {

        } while (!allMelt());
    } // end of main

    public static boolean allMelt() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] != 0) return false;
        return true;
    }
} // end of class
