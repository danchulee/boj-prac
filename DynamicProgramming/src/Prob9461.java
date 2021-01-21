import java.util.Scanner;

public class Prob9461 {
    static int T, N;
    static long[] dps;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            dps = new long[N + 1];
            dps[1] = 1;
            if (N >= 2) {
                dps[2] = 1;
                if (N >= 3) {
                    dps[3] = 1;
                    if (N >= 4) {
                        dps[4] = 2;
                        if (N >= 5)
                            dps[5] = 2;
                    }
                }
            }
            for (int j = 6; j <= N; j++)
                dps[j] = dps[j - 1] + dps[j - 5];
            System.out.println(dps[N]);
        }
    }
}
