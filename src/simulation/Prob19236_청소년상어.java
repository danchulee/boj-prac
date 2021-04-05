package simulation;

import java.io.*;
import java.util.*;

public class Prob19236_청소년상어 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    //static HashMap<Integer, int[]> fishPos = new HashMap<>();
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
        fishPos[map[0][0][0]][0] = -2;
        fishPos[map[0][0][0]][1] = -2;
        map[0][0][0] = -2;

        sharkMove(0, 0, map[0][0][1], map[0][0][0]);

        System.out.print(answer);
    }

    public static void sharkMove(int x, int y, int dir, int score) {
        fishMove();

        boolean in = false;
        int tmp;
        int nx = x, ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) break;
            if (map[nx][ny][0] != 0) {
                in = true;
                tmp = map[nx][ny][0];

                map[x][y][0] = 0;

                map[nx][ny][0] = -1;
                fishPos[tmp][0] = -1;
                fishPos[tmp][1] = -1;
                sharkMove(nx, ny, map[nx][ny][1], score + map[nx][ny][0]);
                fishPos[tmp][0] = nx;
                fishPos[tmp][1] = ny;
                map[nx][ny][0] = tmp;
            }
        }
        if (!in) answer = Math.max(answer, score);
    }

    //map 0 빈칸 -1 상
    public static void fishMove() {
        boolean[] moved = new boolean[16];

        int turn = 0;
        int x, y, dir;
        boolean out;
        while (true) {
            out = true;
            if (turn >= 16) break;
            if (fishPos[turn][0] == -2) {
                turn++;
                continue;
            }
            for (int i = 0; i < 16; i++) {
                if (!moved[i] && fishPos[i][0] >= 0) {
                    out = false;
                    break;
                }
            }
            if (out) break;

            x = fishPos[turn][0];
            y = fishPos[turn][1];
            dir = map[x][y][1];
            move(x, y, dir, moved);
            turn++;
        }
    }

    public static boolean move(int x, int y, int dir, boolean[] moved) {
        int turnCnt = 0;
        int nx, ny;
        while (true) {
            if (turnCnt == 8) return false;
            nx = x + dx[dir];
            ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny][0] == -2) {
                turnCnt++;
                dir = (dir + 1) % 8;
            } else break;
        }
        moved[map[x][y][0]] = true;
        if (map[nx][ny][0] >= 0)
            moved[map[nx][ny][0]] = true;
        swapFish(x, y, nx, ny);
        return true;
    }


    // map, fishpos 수정
    public static void swapFish(int x, int y, int nx, int ny) {
        int[] one = map[x][y];
        int[] two = map[nx][ny];
        map[nx][ny] = one;
        map[x][y] = two;

        fishPos[one[0]][0] = nx;
        fishPos[one[0]][1] = ny;
        fishPos[two[0]][0] = x;
        fishPos[two[0]][1] = y;
    }
}
