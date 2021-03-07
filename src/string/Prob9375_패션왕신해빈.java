package string;

import java.io.*;
import java.util.*;

public class Prob9375_패션왕신해빈 {
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String type;
        int answer, N, T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            answer = 1;
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                type = st.nextToken();
                if (map.containsKey(type))
                    map.put(type, map.get(type) + 1);
                else map.put(type, 1);


            }// end of Input
            for (int i : map.values())
                answer *= (i + 1);
            sb.append(answer - 1).append("\n");

            map.clear();
        }
        System.out.print(sb);
    }


}
