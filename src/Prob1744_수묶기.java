import java.io.*;
import java.util.*;

public class Prob1744_수묶기 {
    static int N;
    static Integer[] nums;

    // 음수, 0, 1, 그 외
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new Integer[N];

        int answer = 0;
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());
        if (N == 1) answer = nums[0];
        else {
            Arrays.sort(nums, (o1, o2) -> {
                if (o1 > 0 && o2 > 0) return o2 - o1;
                else return o1 - o2;
            });
            int index = 0;
            for (; index < N && nums[index] < 0; index += 2) {
                if (index == N - 1) {
                    answer += nums[index];
                } else if (nums[index + 1] == 0) {
                    index += 2;
                    break;
                } else if (nums[index + 1] > 0) {
                    answer += nums[index++];
                    break;
                } else {
                    answer += nums[index] * nums[index + 1];
                }
            }
            while (index < N && nums[index] == 0) index++;
            for (; index < N; index += 2) {
                if (index == N - 1) {
                    answer += nums[index];
                } else if (nums[index] == 1 || nums[index + 1] == 1) {
                    answer += nums[index] + nums[index + 1];
                } else answer += nums[index] * nums[index + 1];
            }
        }
        System.out.print(answer);
    }
}
