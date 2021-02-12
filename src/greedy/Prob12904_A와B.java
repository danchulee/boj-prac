package algo.boj;

import java.util.Scanner;

public class Prob12904_A와B {
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String T = sc.next();

        while (true) {
            if (T.length() == S.length()) {
                if (S.equals(T))
                    ans = 1;
                break;
            }
            char last = T.charAt(T.length() - 1);
            T = T.substring(0, T.length() - 1);
            if (last == 'B')
                T = new StringBuffer(T).reverse().toString();
        }
        System.out.println(ans);
        sc.close();
    } // end of Main
} // end of Class

