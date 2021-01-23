package algo.boj;

import java.util.*;

public class Prob1339 {
    static int N;
    static String[] words;

    public static void main(String[] args) {
        //Map<Character, Integer> letters = new HashMap<>();
        char[] letters = new char[10];
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
            for (int j = 0; j < 8 - words[i].length(); j++)
                words[i] = String.format("%8s", words[i]);
        }
        Map<Character, Integer> used = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            Map<Character, Integer> map = new HashMap<>();
            Pair max_key = new Pair();
            for (int j = 0; j < N; j++) { // 개수 세는 중 (가장 많이 나온 거 찾아야 함)
                if (map.containsKey(words[j].charAt(i))) {
                    map.replace(words[j].charAt(i), map.get(words[j].charAt(i)) + 1);
                } else map.put(words[j].charAt(i), 1);
                if (max_key.appears <= map.get(words[j].charAt(i))) {
                    max_key.appears = map.get(words[j].charAt(i));
                    max_key.letter = words[j].charAt(i);
                }
            }
            // ... 알파벳에 숫자 지정
            for (int j = 9; i >= 0; i--) {
                if (letters[i] == '\u0000' && !used.containsKey(max_key.letter)) {
                    letters[i] = max_key.letter;
                    used.put(letters[i], 1);
                }
            }
        }
        System.out.println("일단락");
        // ... 연산

    } // end of Main
} // end of Class

class Pair {
    Character letter;
    int appears;

    public void plusAppr() {
        this.appears++;
    }
}