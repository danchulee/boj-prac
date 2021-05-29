package etc;

import java.io.*;
import java.util.*;

/*
    틀린 이유 1 출력순서
    틀린 이유 2 초기 min 값 작게 설정
 */
public class Prob2470_두용액 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long min = Long.MAX_VALUE;
        long[] ans = new long[2];

        ArrayList<Long> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            list.add(Long.parseLong(st.nextToken()));
        list.sort((o1, o2) -> Long.compare(Math.abs(o1), Math.abs(o2)));

        long l1, l2, res;
        for (int i = 1; i < N; i++) {
            l1 = list.get(i);
            l2 = list.get(i - 1);
            res = Math.abs(l1 + l2);
            if (min > res) {
                min = res;
                if (l1 < l2) {
                    ans[0] = l1;
                    ans[1] = l2;
                } else {
                    ans[0] = l2;
                    ans[1] = l1;
                }
            }
        }
        System.out.print(ans[0] + " " + ans[1]);
    }
}
