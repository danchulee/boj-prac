package greedy;

import java.io.*;
import java.util.*;

public class Prob1744_수묶기2 {
    static int N;
    static int[] nums;

    // 음수, 0, 1, 그 외
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        int answer = 0, head = 0, tail = N - 1;
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());
        Arrays.sort(nums);
        while (head <= tail && nums[head] < 0) {
            if (head == tail || nums[head + 1] > 0) {
                answer += nums[head--];
            } else if (nums[head + 1] < 0) {
                answer += nums[head] * nums[head + 1];
            }
            head += 2;
        }
        while (head <= tail && nums[head] <= 1) answer += nums[head++];
        while (tail >= head) {
            if (tail - 1 < head)
                answer += nums[tail];
            else answer += nums[tail] * nums[tail - 1];
            tail -= 2;
        }
        System.out.print(answer);
    }
}
