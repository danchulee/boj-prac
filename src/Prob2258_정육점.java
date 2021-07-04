import java.io.*;
import java.util.*;

public class Prob2258_정육점 {
    static int N, M;
    static int[][] meat;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        meat = new int[N][];

        for (int i = 0; i < N; i++) { // 무게, 가격
            st = new StringTokenizer(br.readLine());
            meat[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(meat, (o1, o2) -> { // 가격 오름차순, 무게 내림차순 (싸고 많은 것부터)
            if (o1[1] == o2[1]) return o2[0] - o1[0];
            else return o1[1] - o2[1];
        });
        int ans = Integer.MAX_VALUE, sum = 0, duplicate = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            sum += meat[i][0];
            if (i > 0 && meat[i - 1][1] == meat[i][1]) // 가격 겹치면 변수에 저장
                duplicate += meat[i][1];
            else duplicate = meat[i][1];
            if (sum >= M) {
                flag = true;
                ans = Math.min(ans, duplicate);
            }
        }
        System.out.print(flag ? ans : -1);
    }
}
