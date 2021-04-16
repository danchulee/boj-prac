package etc;

import java.io.*;
import java.util.*;

public class Prob1629_곱셈 {
    static int A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.print(power(A, B));
    }

    // int 하면 틀림
    public static long power(int n, int k) {
        if (k == 0) return 1;

        long tmp = power(n, k / 2);
        long res = tmp * tmp % C;
        if (k % 2 == 1)
            res = res * n % C;
        return res;
    }
}
