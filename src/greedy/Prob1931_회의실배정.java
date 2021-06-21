package greedy;

import java.io.*;
import java.util.*;

// 빨리 끝나는 것 순으로 정렬
// < 와 <= 잘 구분할 것 0초도 가능 시작시간 끝나는 시간 겹쳐도 ㄱㅊ
//      반례
//        3
//        2 2
//        1 2
//        2 3
//        3

public class Prob1931_회의실배정 {
    static int N;

    static class Time {
        int start, end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Time[] rooms = new Time[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            rooms[i] = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(rooms, (o1, o2) -> {
            if (o1.end != o2.end) return o1.end - o2.end;
            else return o1.start - o2.start;
        });
        int cnt = 1;
        Time before = rooms[0];
        for (int i = 1; i < N; i++) {
            if (rooms[i].start < before.end) continue;
            else {
                cnt++;
                before = rooms[i];
            }
        }
        System.out.print(cnt);
    }
}
