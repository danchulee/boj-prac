import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//중복조합은 뽑은 수 배열에 저장하기 기억...
public class Prob15657_N과M8 {
    static int N, M;
    static int[] nums;
    static int[] picked;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        picked = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        pick(0, 0);
        System.out.println(sb);
    }

    public static void pick(int count, int index) {
        if (count == M) {
            for (int p : picked)
                sb.append(p).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = index; i < N; i++) {
            picked[count] = nums[i];
            pick(count + 1, i);
        }
    }
}
