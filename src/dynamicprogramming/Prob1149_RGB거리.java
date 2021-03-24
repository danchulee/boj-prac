import java.io.*;
import java.util.*;

public class Prob1149_RGB거리 {
    static int N;
    static int[][] colors;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        colors = new int[N][3];
        DP = new int[3][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                colors[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 3; i++)
            DP[i][0] = colors[0][i];
        for (int i = 1; i < N; i++) {
            DP[0][i] = Math.min(DP[1][i - 1], DP[2][i - 1]) + colors[i][0];
            DP[1][i] = Math.min(DP[0][i - 1], DP[2][i - 1]) + colors[i][1];
            DP[2][i] = Math.min(DP[0][i - 1], DP[1][i - 1]) + colors[i][2];
        }

        //System.out.println(Arrays.toString(DP));
        System.out.println(Math.min(DP[0][N - 1], Math.min(DP[1][N - 1], DP[2][N - 1])));
    }
}
