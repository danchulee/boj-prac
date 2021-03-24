package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob1074_Z {
    static int N, r, c;

    // math.pow 대신 분할정복으로 저장해서 사용하는 것 또한 방법이다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        quarter(0, 0, N, 0);
    }

    public static void quarter(int x, int y, int num, int count) {
        if (num == 0) {
            if (x == r && y == c)
                System.out.print(count);
            return;
        }
        int val = (int) Math.pow(2, num - 1);
        int move_val = (int) Math.pow(2, 2 * (num - 1));

        if (r >= x && r < x + val && c >= y && c < y + val)
            quarter(x, y, num - 1, count);
        else if (r < x + val && c >= y + val)
            quarter(x, y + val, num - 1, count + move_val);
        else if (r >= x + val && c < y + val)
            quarter(x + val, y, num - 1, count + move_val * 2);
        else
            quarter(x + val, y + val, num - 1, count + move_val * 3);

    }
}
