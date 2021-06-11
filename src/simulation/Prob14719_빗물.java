package simulation;

import java.io.*;
import java.util.*;

public class Prob14719_빗물 {
    static int H, W, ans;
    static int[] rain;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        rain = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++)
            rain[i] = Integer.parseInt(st.nextToken());
        boolean progress = false;
        int left = 0, right = 0;
        int min;
        for (int i = 0; i < W; i++) {
            if (progress) {
                if (rain[i] >= rain[left]) {
                    right = i;
                    min = Math.min(rain[left], rain[right]);
                    for (int j = left + 1; j < right; j++)
                        ans += (min - rain[j]);
                    left = right;
                } else if (i == W - 1) {
                    int maxRight = 0;
                    for (int j = left + 1; j < W; j++)
                        if (maxRight < rain[j]) {
                            maxRight = rain[j];
                            right = j;
                        }
                    min = Math.min(rain[left], rain[right]);
                    for (int j = left + 1; j < right; j++)
                        ans += (min - rain[j]);
                    left = right;
                    i = left + 1;
                }
            } else if (rain[i] != 0) {
                left = i;
                progress = true;
            }
            System.out.print(ans);
        }
        System.out.print(ans);
    }
}
