package backtracking;

import java.io.*;
import java.util.*;

public class Prob12886_돌그룹 {
    static int A, B, C;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int MAX = Math.max(A, Math.max(B, C));
        if ((A + B + C) % 3 != 0)
            System.out.print(0);
        else {
            if (dfs(new int[]{A, B, C})) System.out.print(1);
            else System.out.print(0);
        }
    }

    public static boolean dfs(int[] stones) {
        if (set.contains(stones[0] * stones[1] * stones[2])) return false;
        if (stones[0] <= 0 || stones[1] <= 0 || stones[2] <= 0)
            return false;
        if (stones[0] == stones[1] && stones[1] == stones[2])
            return true;
        set.add(stones[0] * stones[1] * stones[2]);
        int one, two;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                one = stones[i];
                two = stones[j];
                if (i != j && one > two) {
                    stones[i] -= two;
                    stones[j] += two;
                    if (dfs(stones)) return true;
                    stones[i] += two;
                    stones[j] -= two;
                }
            }
        return false;
    }

}
