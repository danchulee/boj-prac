package algo.boj;

import java.util.*;

public class Prob1339 {
    static int N;
    static String[] words;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
            for (int j = 0; j < 8 - words[i].length(); j++)
                words[i] = " " + words[i];
        }
        for (int i = 0; i < 8; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int max_key = 0;
            for (int j = 0; j < N; j++) {
                if (map.containsKey(words[j].charAt(i))) {
                    map.replace(words[j].charAt(i), map.get(words[j].charAt(i)) + 1);
                } else map.put(words[j].charAt(i), 1);
                max_key = Math.max(max_key, map.get(words[j].charAt(i)));
            }

        }

    }
}
