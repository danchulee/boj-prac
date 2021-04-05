package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob19236_청소년상어2 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[][][] map = new int[4][4][2]; // 0 번에 물고기 번호, 1번에 dir
    static int[][] fishPos = new int[16][2]; // 0 번에 x, 1번에 y
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num, dir;
        for (int i = 0; i < 4; i++) { // -1 빈칸 -2 상어
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                num = Integer.parseInt(st.nextToken()) - 1;
                dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j][0] = num;
                map[i][j][1] = dir;
                fishPos[num][0] = i;
                fishPos[num][1] = j;
            }
        }
        printMap();
        sharkMove(0, 0, map[0][0][1], sharkEat(0, 0, 0));
    }

    // map 에 물고기 정보, 방향 fishPos 에 물고기 좌표
    public static void sharkMove(int x, int y, int dir, int score) {
        fishMove();

        int[][][] tmpMap = new int[4][4][2];
        boolean in = false;
        int nx = x, ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) break;
            if (map[nx][ny][0] >= 0) { // 물고기 있을 때만
                in = true;
                int fish_num = map[nx][ny][0];
                copyMap(map, tmpMap);
                map[x][y][0] = -1;
                map[x][y][1] = -1;

                sharkMove(nx, ny, map[nx][ny][1], sharkEat(nx, ny, score));
                copyMap(tmpMap, map);
                fishPos[fish_num][0] = nx;
                fishPos[fish_num][1] = ny;
            }
        }
        if (!in) answer = Math.max(answer, score);
    }

    public static int sharkEat(int x, int y, int score) {
        int ateFishNum = map[x][y][0];
        map[x][y][0] = -2;
        fishPos[ateFishNum][0] = -1;
        fishPos[ateFishNum][1] = -1;

        return score + ateFishNum;
    }


    //map 0 빈칸 -1 상
    public static void fishMove() {
        int x, y, dir, nx, ny, cnt;

        int moveCheck;
        boolean[] moved = new boolean[16];
        int fish1_num, fish1_dir, fish2_num, fish2_dir;
        for (int i = 0; i < 16; i++) {
            moveCheck = 0;

            if ((fishPos[i][0] == -1 && fishPos[i][1] == -1) || fishPos[i][0] == 2) {
                moved[i] = true;
                continue;
            }
            for (int j = 0; j < 16; j++)
                if (moved[j]) moveCheck++;
            if (moveCheck == 16)
                break;
            moved[i] = true;
            cnt = 0;
            x = fishPos[i][0];
            y = fishPos[i][1];
            dir = map[x][y][1];

            nx = x + dx[dir];
            ny = y + dy[dir];
            while (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny][0] == -2) {
                dir = (dir + 1) % 8;
                nx = x + dx[dir];
                ny = y + dy[dir];
                if (++cnt == 8) break;
            }
            if (cnt < 8) {
                map[x][y][1] = dir;
                fish1_num = map[x][y][0];
                fish1_dir = map[x][y][1];

                fish2_num = map[nx][ny][0];
                fish2_dir = map[nx][ny][1];

                map[nx][ny][0] = fish1_num;
                map[nx][ny][1] = fish1_dir;

                fishPos[fish1_num][0] = nx;
                fishPos[fish1_num][1] = ny;
                if (fish2_num == -1) { // 빈 공간
                    map[x][y][0] = -1;
                    map[x][y][1] = -1;
                } else { // 물고기 있음
                    moved[fish2_num] = true;
                    map[x][y][0] = fish2_num;
                    map[x][y][1] = fish2_dir;
                    fishPos[fish2_num][0] = x;
                    fishPos[fish2_num][1] = y;
                }
            }

            System.out.println(i + "번이동 ");
            printMap();
        }
    }

    public static void printMap() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j][0] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void copyMap(int[][][] src, int[][][] dst) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                System.arraycopy(src[i][j], 0, dst[i][j], 0, 2);
    }

}
