package algo.boj;

import java.util.*;


public class Prob1339_x {
    static int N, ans;
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
            for (int j = 0; j < N; j++) { // 개수 세는 중 (가장 많이 나온 거 찾아야 함)
                if (words[j].charAt(i) == ' ' || used.containsKey(words[j].charAt(i)))
                    continue;
                if (map.containsKey(words[j].charAt(i))) {
                    map.replace(words[j].charAt(i), map.get(words[j].charAt(i)) + 1);
                } else map.put(words[j].charAt(i), 1);
            }
            // 한 자리당 하나만 부여할 게 아니라 최대한 많이 부여해줘야 한다.
            // ... 알파벳에 숫자 지정
            if (map.isEmpty()) continue;
            List<Pair> pairToList = new ArrayList<>();
            for (Character key : map.keySet()) {
                pairToList.add(new Pair(key, map.get(key)));
            }
            Collections.sort(pairToList);

            int listNum = 0;
            for (int j = 9; j >= 0; j--) {
                if (letters[j] == '\u0000' && !used.containsKey(pairToList.get(listNum))) {
                    letters[j] = pairToList.get(listNum++).letter;
                    used.put(letters[j], 1);
                    break;
                }
            }
//            for (int j = 9; j >= 0; j--) {
//                if (letters[j] == '\u0000' && !used.containsKey(max_key.letter)) {
//                    letters[j] = max_key.letter;
//                    used.put(letters[j], 1);
//                    break;
//                }
//            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++)
                if (letters[j] != '\u0000')
                    words[i] = words[i].replace(Character.toString(letters[j]), Integer.toString(j));
            ans += Integer.parseInt(words[i].trim());
        }
        System.out.println(ans);
        sc.close();
        // ... 연산

    } // end of Main
} // end of Class

class Pair implements Comparable<Pair> {
    Character letter;
    int appears;

    public Pair(Character letter, int appears) {
        this.letter = letter;
        this.appears = appears;
    }

    public void plusAppr() {
        this.appears++;
    }

    @Override
    public int compareTo(Pair o) {
        return o.appears - this.appears;
    }
}