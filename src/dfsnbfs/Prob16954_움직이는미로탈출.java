import java.io.*;
import java.util.*;



// 방문체크 x
// 벽 한 번에 이동

public class Prob16954_움직이는미로탈출 {
    static ArrayList<int[]> walls = new ArrayList<>();
    static Queue<int[]> wook = new LinkedList<>();
    static char[][] map = new char[8][8];
    static int answer = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt;
        boolean exit = false;
        for (int i = 0; i < 8; i++) {
            cnt = 0;
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#') {
                    cnt++;
                    walls.add(new int[]{i, j});
                }
            }
            if (cnt == 8) {
                answer = 0;
                exit = true;
                break;
            }
        }
        if (!exit && !walls.isEmpty())
            answer = bfs();
        System.out.print(answer);
    }

    static final int[] dx = {-1, 1, 0, 0, 0, -1, -1, 1, 1};
    static final int[] dy = {0, 0, -1, 1, 0, -1, 1, -1, 1};

    public static int bfs() {
        boolean dest = false;
        int size, nx, ny;
        int[] pos;

        wook.offer(new int[]{7, 0});
        while (!dest && !wook.isEmpty()) {
            size = wook.size();
            while (!dest && size-- > 0) {
                pos = wook.poll();
                if (map[pos[0]][pos[1]] == '#') continue;

                for (int d = 0; d < 9; d++) {
                    nx = pos[0] + dx[d];
                    ny = pos[1] + dy[d];
                    if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;
                    if (map[nx][ny] == '#') continue;
                    if (nx == 0) {
                        dest = true;
                        break;
                    }
                    //map[nx][ny] = 'x';
                    wook.offer(new int[]{nx, ny});
                }
            }
            wallMove();
        }
        if (dest) return 1;
        else return 0;
    }

    // 한꺼번에 옮기고 한꺼번에 해야할
    public static void wallMove() {
//        int size = walls.size();
//        while (size-- > 0) {
//            int[] tmp = walls.poll();
//            if (tmp[0] == 7) continue;
//            map[tmp[0]][tmp[1]] = '.';
//            map[tmp[0] + 1][tmp[1]] = '#';
//            walls.offer(new int[]{tmp[0] + 1, tmp[1]});
//        }

        Iterator<int[]> iter = walls.iterator();
        while (iter.hasNext()) {
            int[] wall = iter.next();
            if (wall[0] == 7) iter.remove();
            map[wall[0]][wall[1]] = '.';
            wall[0]++;
        }
        for (int[] wall : walls)
            map[wall[0]][wall[1]] = '#';

    }

}
