package greedy;

import java.io.*;

public class Prob2138_전구와스위치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder start = new StringBuilder(br.readLine());
        StringBuilder start2 = new StringBuilder(start);
        String end = br.readLine();
        int ans = 0, ans2 = 1;

        start2.setCharAt(0, (char) ((1 - (start.charAt(0) - '0')) + '0'));
        start2.setCharAt(1, (char) ((1 - (start.charAt(1) - '0')) + '0'));

        for (int i = 1; i < N; i++) {
            if (start.charAt(i - 1) != end.charAt(i - 1)) {
                start.setCharAt(i - 1, (char) ((1 - (start.charAt(i - 1) - '0')) + '0'));
                start.setCharAt(i, (char) ((1 - (start.charAt(i) - '0')) + '0'));
                if (i != N - 1)
                    start.setCharAt(i + 1, (char) ((1 - (start.charAt(i + 1) - '0')) + '0'));
                ans++;
            }
            if (start2.charAt(i - 1) != end.charAt(i - 1)) {
                start2.setCharAt(i - 1, (char) ((1 - (start2.charAt(i - 1) - '0')) + '0'));
                start2.setCharAt(i, (char) ((1 - (start2.charAt(i) - '0')) + '0'));
                if (i != N - 1)
                    start2.setCharAt(i + 1, (char) ((1 - (start2.charAt(i + 1) - '0')) + '0'));
                ans2++;
            }
        }
        if (start.toString().equals(end)) {
            System.out.print(ans);
        } else if (start2.toString().equals(end)) {
            System.out.print(ans2);
        } else {
            System.out.print(-1);
        }
    }
}
