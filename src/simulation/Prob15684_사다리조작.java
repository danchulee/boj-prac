package simulation;

import java.io.*;
import java.util.*;

public class Prob15684_사다리조작 {
    static int N, M, H;
    static int[][] ladder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H + 2][N + 2];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            ladder[a][b] = i + 1;
            ladder[a][b + 1] = i + 1;
        }
        int answer = -1;
        for (int i = 0; i <= 3 && answer == -1; i++)
            if ((M + i) % 2 == 0 && putLadder(0, i, M + 1))
                answer = i;
        System.out.println(answer);
    }


    public static boolean putLadder(int cnt, int goal, int number) {
        if (cnt == goal) {
            boolean possible = true;
//            for (int i = 1; i <= N && possible; i++) 재귀버전
//                if (!possible(1, i, i))
//                    possible = false;
            int r, c;
            for (int i = 1; i < N && possible; i++) { // 마지막 사다리는 검사 필요없음
                r = 1;
                c = i;
                while (r != H + 1) {
                    if (ladder[r][c] > 0)
                        if (ladder[r][c] == ladder[r][c + 1]) c++;
                        else c--;
                    r++;
                }
                if (c != i) possible = false;
            }
            return possible;
        }
        for (int r = 1; r <= H; r++) {
            for (int c = 1; c < N; c++) {
                if (ladder[r][c] > 0 || ladder[r][c + 1] > 0) continue;
                ladder[r][c] = number;
                ladder[r][c + 1] = number;
                if (putLadder(cnt + 1, goal, number + 1)) return true;
                ladder[r][c] = 0;
                ladder[r][c + 1] = 0;
            }
        }
        return false;
    }

//    public static boolean possible(int r, int c, int num) { 재귀 버전
//        if (r == H + 1) {
//            if (c == num) return true;
//            else return false;
//        }
//        if (ladder[r][c] > 0) {
//            if (ladder[r][c] == ladder[r][c + 1]) return possible(r + 1, c + 1, num);
//            else return possible(r + 1, c - 1, num);
//        } else return possible(r + 1, c, num);
//    }
}
