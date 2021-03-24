import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1987_알파벳2 {
    static int R, C, answer;
    static int[] pos;
    static int[][] board;
    //static boolean[] alphabet = new boolean['Z']와 같은 방법 또한 있다.

    /**
     * DFS 경로 추적에 대하여
     * <p>
     * visited 객체를 새로 만들지 않고 계속 원복하는 방식을 사용하고 있다.
     * 새로 만들고 싶은데 객체 생성을 하지 않고 싶을 때 사용하는 방식이 바로 비트마스크이다.
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++)
                board[i][j] = str.charAt(j) - 'A';
        }
        // end of Input
        dfs(0, 0, 1, (1 << board[0][0]));

        System.out.print(answer);
    } // end of Main

    public static void dfs(int r, int c, int step, int visit) {
        if (answer == 26) return;
        if (answer < step)
            answer = step;
        if (0 <= r - 1 && (visit & 1 << board[r - 1][c]) == 0) { // 상
            dfs(r - 1, c, step + 1, visit | (1 << board[r - 1][c]));
        }
        if (r + 1 < R && (visit & 1 << board[r + 1][c]) == 0) { // 하
            dfs(r + 1, c, step + 1, visit | (1 << board[r + 1][c]));
        }
        if (0 <= c - 1 && (visit & 1 << board[r][c - 1]) == 0) { // 좌
            dfs(r, c - 1, step + 1, visit | (1 << board[r][c - 1]));
        }
        if (c + 1 < C && (visit & 1 << board[r][c + 1]) == 0) { // 우
            dfs(r, c + 1, step + 1, visit | (1 << board[r][c + 1]));
        }
    }

} // end of Class
