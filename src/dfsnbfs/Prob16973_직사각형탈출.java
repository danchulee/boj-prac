import java.io.*;
import java.util.*;

public class Prob16973_직사각형탈출 {
    static int N, M, H, W;
    static int[][] map;
    static int[] dest = new int[2];
    static Queue<int[]> q = new LinkedList<>();

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
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        q.offer(new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1});
        dest[0] = Integer.parseInt(st.nextToken()) - 1;
        dest[1] = Integer.parseInt(st.nextToken()) - 1; // Input end

        System.out.print(bfs());
    } // end of Main

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    // 0 : 빈칸, 1 :
    // 방문 배열은 시작 부분만 체크하면 됨, 방문을 -1로 해야 벽이랑 구분할 수 있을 것이다.
    public static int bfs() {
        int size, nx, ny, answer = 0;
        boolean reach = false;
        map[q.peek()[0]][q.peek()[1]] = -1;
        while (!reach && !q.isEmpty()) {
            answer++;
            size = q.size();
            while (!reach && size-- > 0) {
                int[] pos = q.poll();
                for (int i = 0; i < 4; i++) {
                    nx = pos[0] + dx[i];
                    ny = pos[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N - H + 1 || ny >= M - W + 1 || map[nx][ny] == 1 || map[nx][ny] == -1 || !possible(nx, ny))
                        continue;
                    if (nx == dest[0] && ny == dest[1]) {
                        reach = true;
                        break;
                    }
                    map[nx][ny] = -1;
                    q.offer(new int[]{nx, ny});

                }
            }
        }
        if (reach) return answer;
        else return -1;

    }

    public static boolean possible(int x, int y) {
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++) {
                if (map[x + i][y + j] == 1) return false;
            }
        return true;
    }
} // end of Class
