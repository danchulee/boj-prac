import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob7576 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int M, N;
    static int[][] box; // 1익음, 0익지 않음, -1없음(가면 안 됨 = 방문완료)
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];

        int rotten = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    box[i][j] = -1;
                    q.offer(new int[]{i, j});
                } else if (box[i][j] == -1) rotten++; // 처음부터 썩은 토마토 개수 세서 추후에 카운트 연산에 반영
            }
        } // end of Input (x, y 위치 바꿔서 함)

        int count = 0, days = -1;
        while (!q.isEmpty()) { // BFS
            days++; // 첫 날은 0일 경과한 것이므로 days = -1로 초기화해뒀음
            int curr_size = q.size(); // 현재 레벨 개수만큼만 돌기 (하루분)
            while (curr_size-- > 0) { // 하루치 돌기
                count++; // 토마토 개수 카운트 (모두 익지 못 하는 경우 확인해야하므로)
                int[] curr_pos = q.poll(); // 0번 인덱스 : x / 1번 인덱스 : y
                for (int i = 0; i < 4; i++) {
                    int nx = curr_pos[0] + dx[i];
                    int ny = curr_pos[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || box[nx][ny] == -1) continue;
                    box[nx][ny] = -1; // visit 체크
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        if (count == N * M - rotten) // 썩은 거 제외 다 돌았는지
            System.out.print(days);
        else System.out.print(-1);


    } // end of Main
} // end of Class
