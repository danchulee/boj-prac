package dfsnbfs;
import java.io.*;
import java.util.*;

public class Prob17836_공주님을구해라 {
    static int N, M, T;
    static int[][][] map; // 마지막 0번칸은 무기없이 방문 1번칸은 무기있이 방

    static class State {
        int x, y;
        boolean weapon;

        State(int x, int y) {
            this.x = x;
            this.y = y;
        }

        State(int x, int y, boolean weapon) {
            this.x = x;
            this.y = y;
            this.weapon = weapon;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[2][N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[0][i][j] = map[1][i][j] = Integer.parseInt(st.nextToken());
        } // end of Input
        int res = bfs();
        if (res == -1) {
            System.out.print("Fail");
        } else System.out.print(res);

    } // end of Main

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static int bfs() {
        boolean found = false;
        int time = 0;
        Queue<State> q = new LinkedList<State>();
        q.offer(new State(0, 0));
        while (!found && !q.isEmpty() && time++ != T) {
            found = false;
            int size = q.size();

            while (!found && size-- > 0) {
                State state = q.poll();
                for (int i = 0; i < 4; i++) {
                    int select = (state.weapon ? 1 : 0);
                    int nx = state.x + dx[i];
                    int ny = state.y + dy[i];
                    if (nx == N - 1 && ny == M - 1) {
                        found = true;
                        break;
                    }
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (map[select][nx][ny] == -1 || (map[select][nx][ny] == 1 && select == 0)) continue;
                    State newState = new State(nx, ny, state.weapon);
                    if (map[select][nx][ny] == 2)
                        newState.weapon = true;
                    map[select][nx][ny] = -1;
                    q.offer(newState);
                }
            }
        }
        if (found) return time;
        else return -1;
    }
} // end of Class
