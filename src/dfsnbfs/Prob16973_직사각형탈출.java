package dfsnbfs;
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
        // 여기까지 input

        System.out.print(bfs());
    } // end of Main

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    // 벽이랑 방문완료를 구분해야 함, 방문완료는 -1로 세팅
    public static int bfs() {
        int size, nx, ny, answer = 0;
        boolean reach = false;
        map[q.peek()[0]][q.peek()[1]] = -1; // 첫 지점 방문완료 표시
        while (!reach && !q.isEmpty()) {
            answer++;
            size = q.size();
            while (!reach && size-- > 0) {
                int[] pos = q.poll();
                for (int i = 0; i < 4; i++) {
                    nx = pos[0] + dx[i];
                    ny = pos[1] + dy[i];
                    // 1) 범위초과 2) 벽 3) 방문완료 4) 도형 놓을 위치에 벽 존재
                    if (nx < 0 || ny < 0 || nx >= N - H + 1 || ny >= M - W + 1 || map[nx][ny] == 1 || map[nx][ny] == -1 || !possible(nx, ny))
                        continue;
                    // 도착 위치 도달
                    if (nx == dest[0] && ny == dest[1]) {
                        reach = true; // 도착 flag
                        break;
                    }
                    map[nx][ny] = -1; // 방문 완료
                    q.offer(new int[]{nx, ny});

                }
            }
        }
        if (reach) return answer;
        else return -1;

    }

    public static boolean possible(int x, int y) {
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (map[x + i][y + j] == 1)
                    return false; // 도형 위치에 벽 있으면 배치 불가능
        return true;
    }
} // end of Class
