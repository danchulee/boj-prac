package algo.boj;

import java.util.*;

public class Prob1339 {
    static int N, ans;
    static String[] words;
    static Map<Character, Integer> scores = new HashMap<>();

    public static void main(String[] args) {
        char[] letters = new char[10];
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
            words[i] = String.format("%8s", words[i]);
            for (int j = 0; j < 8; j++) {
                if (words[i].charAt(j) != ' ') {
                    int score = (int) Math.pow(10, 8 - j);
                    if (scores.containsKey(words[i].charAt(j))) {
                        scores.replace(words[i].charAt(j), scores.get(words[i].charAt(j)) + score);
                    } else scores.put(words[i].charAt(j), score);
                }
            }
        }

        List<Map.Entry<Character, Integer>> list_entries = new ArrayList<>(scores.entrySet());

        Collections.sort(list_entries, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> obj1, Map.Entry<Character, Integer> obj2) {
                // 오름차순 정렬
                return obj1.getValue().compareTo(obj2.getValue());
            }
        });
        Collections.reverse(list_entries);

        int letter_num = 9;
        for (Map.Entry<Character, Integer> entry : list_entries) {
            letters[letter_num--] = entry.getKey();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++)
                if (letters[j] != '\u0000')
                    words[i] = words[i].replace(Character.toString(letters[j]), Integer.toString(j));
            ans += Integer.parseInt(words[i].trim());
        }

        System.out.println(ans);
    }
}
