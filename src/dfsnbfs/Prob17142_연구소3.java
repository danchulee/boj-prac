package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob17142_연구소3 {
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> viruses = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    static boolean[] choose;
    static int answer = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        boolean filled = true;
        for (int i = 0; i < N; i++) { // 0 빈 칸, -1 벽, -2 바이러
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp.charAt(2 * j) - '0';
                if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                    map[i][j] = -1;
                } else if (map[i][j] == 0) filled = false;
            }
        } // end of Input
        choose = new boolean[viruses.size()];
        if (!filled) pickBFS(0, 0);
        else answer = 0;
        System.out.print(answer);
    }

    public static void pickBFS(int index, int cnt) {
        if (cnt == M) {
            q.clear();
            for (int i = 0; i < viruses.size(); i++)
                if (choose[i]) q.offer(viruses.get(i));
            BFS();
        }
        for (int i = index; i < viruses.size(); i++) {
            int[] tmp = viruses.get(i);
            choose[i] = true;
            map[tmp[0]][tmp[1]] = 1;
            pickBFS(i + 1, cnt + 1);
            choose[i] = false;
            map[tmp[0]][tmp[1]] = -1;
        }
    }


    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void BFS() {
        int[][] copy_map = new int[N][N];
        for (int i = 0; i < N; i++)
            copy_map[i] = Arrays.copyOf(map[i], N);
        int[] virus;
        int size, nx, ny, time = 0;
        while (!q.isEmpty()) {
            size = q.size();
            time++;
            if(answer != -1 && time > answer) break;
            while (size-- > 0) {
                virus = q.poll();
                for (int i = 0; i < 4; i++) {
                    nx = virus[0] + dx[i];
                    ny = virus[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || copy_map[nx][ny] == 1) continue;
                    copy_map[nx][ny] = 1;
                    q.offer(new int[]{nx, ny});
                }
            }
            if (allDone(copy_map)) {
                answer = (answer == -1 ? time : Math.min(answer, time));
                break;
            }
        }
    }

    public static boolean allDone(int[][] copy_map) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (copy_map[i][j] == 0) return false;
        return true;
    }
}
