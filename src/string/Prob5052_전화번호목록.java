package string;

import java.io.*;
import java.util.*;

public class Prob5052_전화번호목록 {
    static int N;
    static ArrayList<String>[] nums = new ArrayList[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String tmp;
        for (int i = 0; i < 10; i++) nums[i] = new ArrayList();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < 10; i++)
                nums[i].clear();
            for (int i = 0; i < N; i++) {
                tmp = br.readLine();
                nums[tmp.charAt(0) - '0'].add(tmp);
            }
            int size;
            boolean found = false;
            for (int i = 0; i < 10 && !found; i++) {
                if (nums[i].isEmpty()) continue;
                found = false;
                Collections.sort(nums[i]);
                size = nums[i].size();
                for (int j = 1; j < size && !found; j++) // 이중 for문 X 사전순 정렬이므로 그냥 앞에거랑 비교
                    if (nums[i].get(j).length() > nums[i].get(j - 1).length() &&
                            nums[i].get(j).indexOf(nums[i].get(j - 1)) == 0)
                        found = true;
            }
            if (found) sb.append("NO").append("\n");
            else sb.append("YES").append("\n");
        }
        System.out.print(sb);
    }
}
