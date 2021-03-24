import java.io.*;
import java.util.*;

public class Prob1987_알파벳1 {
    static int R, C, answer;
    static int[] pos;
    static char[][] board;
    // 아스키코드 A 65 ~ Z 90
    // 숫
    static boolean[] alphabet = new boolean[26];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        pos = new int[]{0, 0};
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++)
            board[i] = br.readLine().toCharArray();

        alphabet[board[0][0] - 'A'] = true;
        dfs(1);
        System.out.print(answer);
    }


    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void dfs(int cnt) {
        // 종료조건 딱히 없음
        int nr, nc;
        boolean in = false;
        for (int i = 0; i < 4; i++) {
            nr = pos[0] + dx[i];
            nc = pos[1] + dy[i];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if (alphabet[board[nr][nc] - 'A']) continue;
            in = true;

            alphabet[board[nr][nc] - 'A'] = true;
            pos[0] = nr;
            pos[1] = nc;
            dfs(cnt + 1);
            pos[0] -= dx[i];
            pos[1] -= dy[i];
        }
        if (!in)
            answer = Math.max(answer, cnt);
    }

}
