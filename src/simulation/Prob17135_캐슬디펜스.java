package simulation;

import java.io.*;
import java.util.*;

public class Prob17135_캐슬디펜스 {
    static int N, M, D, answer;
    static int[] archers = new int[3];
    static ArrayList<Enemy> realEnemies = new ArrayList<>();
    static HashSet<Integer> targets = new HashSet<>();
    static ArrayList<Enemy> enemies = new ArrayList<>();
    static ArrayList<Enemy> candidates = new ArrayList<>();
    static int nowIndex;

    static class Enemy {
        int number, x, y;

        Enemy(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

        Enemy(Enemy enemy) {
            this.number = enemy.number;
            this.x = enemy.x;
            this.y = enemy.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, x, y);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    realEnemies.add(new Enemy(realEnemies.size(), i, j));
            }
        }
        putArcher(0, 0);

        System.out.print(answer);
    }

    public static void putArcher(int number, int index) {
        if (number == 3) {
            answer = Math.max(answer, attacks());
            return;
        }
        for (int i = index; i < M; i++) {
            archers[number] = i;
            putArcher(number + 1, i + 1);
        }

    }


    public static int attacks() {
        int res = 0;
        enemies.clear();
        for (Enemy enemy : realEnemies)
            enemies.add(new Enemy(enemy));
        Enemy enemy;
        do {
            res += attack();
            Iterator<Enemy> iter = enemies.iterator();
            while (iter.hasNext()) { // 이
                enemy = iter.next();
                enemy.x++;
                if (enemy.x >= N) iter.remove();
            }
        } while (!enemies.isEmpty());
        return res;
    }




    public static int attack() {
        targets.clear();

        for (nowIndex = 0; nowIndex < 3; nowIndex++) {
            candidates.clear();
            for (Enemy enemy : enemies)
                if (distance(enemy.x, enemy.y, N, archers[nowIndex]) <= D)
                    candidates.add(enemy);
            if (!candidates.isEmpty()) {
                candidates.sort(new Comparator<Enemy>() {
                    @Override
                    public int compare(Enemy o1, Enemy o2) {
                        int dist1 = distance(o1.x, o1.y, N, archers[nowIndex]);
                        int dist2 = distance(o2.x, o2.y, N, archers[nowIndex]);
                        if (dist1 != dist2) return dist1 - dist2;
                        else return o1.y - o2.y;
                    }
                });
                targets.add(candidates.get(0).number);
            }
        }
        int delNum, res = targets.size();

        Iterator<Integer> iter = targets.iterator();
        while (iter.hasNext()) {
            delNum = iter.next();
            for (int i = 0; i < enemies.size(); i++) {
                if (delNum == enemies.get(i).number) {
                    enemies.remove(i);
                    break;
                }
            }
        }
        return res;
    }

    public static int distance(int a, int b, int x, int y) {
        return Math.abs(a - x) + Math.abs(b - y);
    }

}
