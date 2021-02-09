package algo.boj;

import java.util.Scanner;

public class Prob1904 {
    //1) 1 하
    //2) 00 한 쌍나
    // N이 주어졌을 때 만들 수 있는 모든 가짓수?
    static int N;
    static long[] dps;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dps = new long[N + 1];
        dps[1] = 1;
        dps[2] = 2;
        for (int i = 3; i <= N; i++)
            dps[i] = (dps[i - 1] + dps[i - 2]) % 15746;
        System.out.println(dps[N]);
    }


}
