import java.io.*;

public class Prob3040_백설공주와일곱난쟁이 {
    static StringBuilder sb = new StringBuilder();
    static int[] hats = new int[9];
    static int target = -100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            hats[i] = Integer.parseInt(br.readLine());
            target += hats[i];
        }
        select(0, 0, 0);
        System.out.print(sb);
    }

    public static void select(int cnt, int index, int sum) {
        if (cnt == 2) {
            if (sum == target)
                for (int i = 0; i < 9; i++)
                    if (hats[i] != -1) sb.append(hats[i]).append("\n");
            return;
        }
        for (int i = index; i < 9; i++) {
            int tmp = hats[i];
            hats[i] = -1;
            select(cnt + 1, i + 1, sum + tmp);
            hats[i] = tmp;
        }
    }
}
