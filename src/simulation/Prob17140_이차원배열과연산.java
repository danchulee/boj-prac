package simulation;

import java.io.*;
import java.util.*;

public class Prob17140_이차원배열과연산 {
    static int r, c, k, max_col, max_row;
    static int[][] map = new int[100][100];
    static ArrayList<HashMap<Integer, Integer>> hms = new ArrayList<>();
    static List<Integer> keys;
    static Iterator<Integer> iter;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < 100; i++)
            hms.add(new HashMap<>());

        int time = 0;
        boolean fail = false;
        max_col = 3;
        max_row = 3;
        while (map[r][c] != k) {
            if (max_row >= max_col) {
                countRow();
                changeRow();
            } else {
                countCol();
                changeCol();
            }
            if (time++ == 100) {
                fail = true;
                break;
            }
        }
        if (fail) System.out.print(-1);
        else System.out.print(time);
    }

    public static void countRow() {
        for (int i = 0; i < 100; i++)
            hms.get(i).clear();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < max_col; j++) {
                if (map[i][j] == 0) continue;
                if (!hms.get(i).containsKey(map[i][j]))
                    hms.get(i).put(map[i][j], 1);
                else hms.get(i).put(map[i][j], hms.get(i).get(map[i][j]) + 1);
            }
        }
    }

    public static void changeRow() {
        int index, key;
        for (int i = 0; i < 100; i++) {
            if (hms.get(i).isEmpty()) break;
            index = 0;

            keys = new ArrayList<>(hms.get(i).keySet());
            int finalI = i;
            keys.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int cnt1 = hms.get(finalI).get(o1);
                    int cnt2 = hms.get(finalI).get(o2);
                    if (cnt1 != cnt2) return cnt1 - cnt2;
                    else return o1 - o2;
                }
            });
            iter = keys.iterator();
            while (iter.hasNext()) {
                key = iter.next();
                map[i][index++] = key;
                map[i][index++] = hms.get(i).get(key);
                if (index == 100)
                    break;
            }
            max_col = Math.max(max_col, index);
            if (index != 100)
                Arrays.fill(map[i], index, 99, 0);
        }
    }

    public static void countCol() {
        for (int i = 0; i < 100; i++)
            hms.get(i).clear();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < max_row; j++) {
                if (map[j][i] == 0) continue;
                if (!hms.get(i).containsKey(map[j][i]))
                    hms.get(i).put(map[j][i], 1);
                else hms.get(i).put(map[j][i], hms.get(i).get(map[j][i]) + 1);
            }
        }
    }

    private static void changeCol() {
        int index, key;
        for (int i = 0; i < 100; i++) {
            if (hms.get(i).isEmpty()) break;
            index = 0;

            keys = new ArrayList<>(hms.get(i).keySet());
            int finalI = i;
            keys.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int cnt1 = hms.get(finalI).get(o1);
                    int cnt2 = hms.get(finalI).get(o2);
                    if (cnt1 != cnt2) return cnt1 - cnt2;
                    else return o1 - o2;
                }
            });
            iter = keys.iterator();
            while (iter.hasNext()) {
                key = iter.next();
                map[index++][i] = key;
                map[index++][i] = hms.get(i).get(key);
                if (index == 100)
                    break;
            }
            max_row = Math.max(max_row, index);
            if (index != 100) // index ~ 99
                for (int j = index; j < 100; j++)
                    map[j][i] = 0;
        }
    }
}
