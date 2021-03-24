package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob17406_배열돌리기4_미완성 {
    static int N, M, K, answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] copy_map;
    static int[][] types;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        types = new int[K][3];
        map = new int[N][M];
        copy_map = new int[N][M];
        visit = new boolean[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int R, C, S;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            types[i] = new int[]{R, C, S};
        }
        select(0);

        System.out.print(answer);
    } // end of Main

    public static void rotate(int r, int c, int s) {
        for (int i = 0; i < s; i++) {
            int nr = r - s + i - 1;
            int nc = c - s + i - 1;
            rotateOnce(nr, nc, 2 * s - 2 * i);
        }
    }

    public static void rotateOnce(int startR, int startC, int cnt) {
        int tmp = copy_map[startR][startC];
        for (int nr = startR; nr < startR + cnt; nr++)
            copy_map[nr][startC] = copy_map[nr + 1][startC];
        for (int nc = startC; nc < startC + cnt; nc++)
            copy_map[startR + cnt][nc] = copy_map[startR + cnt][nc + 1];
        for (int nr = startR + cnt; nr > startR; nr--)
            copy_map[nr][startC + cnt] = copy_map[nr - 1][startC + cnt];
        for (int nc = startC + cnt; nc > startC; nc--)
            copy_map[startR][nc] = copy_map[startR][nc - 1];
        copy_map[startR][startC + 1] = tmp;
    }

    public static void select(int cnt) {
        if (cnt == K) {
            answer = Math.min(answer, min());
            return;
        }
        for (int i = 0; i < K; i++) {
            if (visit[i]) continue;
            if (cnt == 0)
                for (int j = 0; j < N; j++)
                    System.arraycopy(map[j], 0, copy_map[j], 0, M);
            visit[i] = true;
            rotate(types[i][0], types[i][1], types[i][2]);
            select(cnt + 1);
            visit[i] = false;
        }
    }

    public static int min() {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++)
                sum += copy_map[i][j];
            res = Math.min(sum, res);
        }
        return res;
    }
} // end of Class

