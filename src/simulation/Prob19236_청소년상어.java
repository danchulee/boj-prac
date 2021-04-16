package simulation;

import java.io.*;
import java.util.*;

public class Prob19236_청소년상어 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static Fish[][] map = new Fish[4][4]; // 0 빈칸, 1 ~ 16 물고기, -1 상어
    static int[] shark;
    static int answer;
    static HashMap<Integer, int[]> fishPos = new HashMap<>();

    static class Fish {
        int num, dir;

        Fish(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num, dir;
        for (int i = 0; i < 4; i++) { // -1 빈칸 -2 상어
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                num = Integer.parseInt(st.nextToken());
                dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = new Fish(num, dir);
                fishPos.put(num, new int[]{i, j});
            }
        }
        shark = new int[]{0, 0, -1};
        move(0);
        System.out.print(answer);
    }

    public static void move(int sum) {
        sum += sharkEat();
        int size = fishPos.size();
        ArrayList<int[]> res;
        int[] before_shark = new int[3];
        before_shark[0] = shark[0];
        before_shark[1] = shark[1];
        before_shark[2] = shark[2];
        Fish[][] before_map = new Fish[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (map[i][j] != null)
                    before_map[i][j] = new Fish(map[i][j].num, map[i][j].dir);

        HashMap<Integer, int[]> before_fishes = new HashMap<>();
        before_fishes.putAll(fishPos);

        Fish fish;
        int[] pos;
        while (true) {
            for (int i = 1; i <= size; i++) { // 모든 fish move
                pos = fishPos.get(i);
                if(pos == null)
                    continue;
                fish = map[pos[0]][pos[1]];
                for (int m = 0; m < 8; m++)
                    if (fishMove(fish, pos[0], pos[1])) break;
            }
            res = canMove();
            if (res.isEmpty()) {
                answer = Math.max(answer, sum);
                return;
            } else {
                for (int[] x : res) {
                    shark[0] = x[0];
                    shark[1] = x[1];
                    shark[2] = x[2];
                    move(sum);
                    // 원복
                    for (int i = 0; i < 4; i++)
                        for (int j = 0; j < 4; j++)
                            if (before_map[i][j] != null)
                                map[i][j] = new Fish(before_map[i][j].num, before_map[i][j].dir);
                    fishPos.putAll(before_fishes);
                }
                shark[0] = before_shark[0];
                shark[1] = before_shark[1];
                shark[2] = before_shark[2];
            }
        }

    }

    public static ArrayList<int[]> canMove() {
        ArrayList<int[]> res = new ArrayList<>();
        int nx = shark[0];
        int ny = shark[1];
        int dir = shark[2];
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                break;
            if (map[nx][ny] != null) {
                res.add(new int[]{nx, ny, map[nx][ny].dir});
            }
        }
        return res;
    }

    public static boolean fishMove(Fish fish, int r, int c) {
        int nx, ny;

        int[] pos;
        nx = r + dx[fish.dir];
        ny = c + dy[fish.dir];
        if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || (nx == shark[0] && ny == shark[1])) {
            fish.dir = (fish.dir + 1) % 8;
            return false;
        }
        if (map[nx][ny] == null) {
            map[nx][ny] = map[r][c];
            pos = fishPos.get(fish.num);
            pos[0] = nx;
            pos[1] = ny;
        } else { // fish 끼리 교환해야 함 -> 건드릴 거 1) map 2) hashmap(각 객체)
            Fish target = map[nx][ny];
            int target_num = target.num, target_dir = target.dir;
            target.num = fish.num;
            target.dir = fish.dir;
            fish.num = target_num;
            fish.dir = target_dir;
            // 객체 위치 바꾼 상태
            pos = fishPos.get(target.num);
            pos[0] = nx;
            pos[1] = ny;
            pos = fishPos.get(fish.num);
            pos[0] = r;
            pos[1] = c;
            // map 바꿈
        }
        return true;
    }

    public static int sharkEat() {
        Fish fish = map[shark[0]][shark[1]];
        int fishnum = fish.num;

        shark[2] = fish.dir;            // 상어 방향 설정
        fishPos.remove(fishnum);         // 물고기 없앰
        map[shark[0]][shark[1]] = null;  // 맵에서 물고기 대신 상어 넣음

        return fishnum;
    }
}
