package etc;

import java.io.*;
import java.util.*;

public class Prob5545_최고의피자 {
    static int N, A, B, C, ans;
    static int[] kcal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(br.readLine());

        kcal = new int[N];
        for (int i = 0; i < N; i++)
            kcal[i] = Integer.parseInt(br.readLine());
        Arrays.sort(kcal);

        int new_price, price = A;
        int new_kcals, kcals = C;
        int new_ans, ans = kcals / price;
        for (int i = N - 1; i >= 0; i--) {
            new_price = price + B;
            new_kcals = kcals + kcal[i];
            new_ans = new_kcals / new_price;
            if (ans <= new_ans) {
                price = new_price;
                kcals = new_kcals;
                ans = new_ans;
            } else break;
        }

        System.out.print(ans);
    }
}
