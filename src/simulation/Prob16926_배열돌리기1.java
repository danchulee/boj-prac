package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 한 칸씩 옮기지 말고 위치를 미리 계산해서 한 번에 R칸씩 옮기는 방법도 있을 것이다. -> 수학적 접근
 *
 */

public class Prob16926_배열돌리기1 {
    static int N, M, R;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        } // end of Input
        // R값 최적화하는 방법? -> 근데 맵 커질수록 오히려 더 오래걸리게 만들듯...
        for (int i = 0; i < R; i++)
            for (int j = 0; j < Math.min(N, M) / 2; j++)
                rotate(j);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    } // end of Main

    public static void rotate(int startPoint) {
        // 그냥 for문으로 turn_M, turn_N만큼 돌리는 방법도 있음 이게 더 빠른 것 같다..
        int turn_M = M - (2 * startPoint + 1);
        int turn_N = N - (2 * startPoint + 1);
        int dir = 0, cnt = 0, tmp = map[startPoint][startPoint];
        int[] pos = {startPoint, startPoint};
        do {
            int nx = pos[0] + dx[dir];
            int ny = pos[1] + dy[dir];
            map[pos[0]][pos[1]] = map[nx][ny];
            pos[0] = nx;
            pos[1] = ny;
            cnt++;
            if ((dir % 2 == 0 && cnt == turn_M) || (dir % 2 == 1 && cnt == turn_N)) {
                cnt = 0;
                dir++;
            }
        } while (pos[0] != startPoint || pos[1] != startPoint);
        map[startPoint + 1][startPoint] = tmp;
    } // end of Rotate
} // end of Class
