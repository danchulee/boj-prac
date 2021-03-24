import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1987_알파벳3 {
    static int R, C, answer;
    static int[] pos;
    static char[][] board;
    static boolean[] alphabet = new boolean[26];
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
        board = new char[R][];
        for (int i = 0; i < R; i++)
            board[i] = br.readLine().toCharArray();
        // end of Input

        alphabet[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.print(answer);
    } // end of Main

    public static void dfs(int r, int c, int step) {
        if (answer == 26) return;
        if (answer < step) answer = step;
        if (0 <= r - 1 && !alphabet[board[r - 1][c]]) { // 상
            alphabet[board[r - 1][c] - 'A'] = true;
            dfs(r - 1, c, step + 1);
            alphabet[board[r - 1][c] - 'A'] = false;
        }
        if (r + 1 < R && !alphabet[board[r + 1][c]]) { // 하
            alphabet[board[r + 1][c] - 'A'] = true;
            dfs(r + 1, c, step + 1);
            alphabet[board[r + 1][c] - 'A'] = false;
        }
        if (0 <= c - 1 && !alphabet[board[r][c - 1]]) { // 좌
            alphabet[board[r][c - 1] - 'A'] = true;
            dfs(r, c - 1, step + 1);
            alphabet[board[r][c - 1] - 'A'] = false;
        }
        if (c + 1 < C && !alphabet[board[r][c + 1]]) { // 우
            alphabet[board[r][c + 1] - 'A'] = true;
            dfs(r, c + 1, step + 1);
            alphabet[board[r][c + 1] - 'A'] = false;
        }
    }

} // end of Class
