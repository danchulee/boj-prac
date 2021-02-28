package simulation;

import java.io.*;
import java.util.*;


// 큐를 써서 뱀, 움직임 관리
public class Prob3190_뱀 {
    static int N, K, L;
    static int[][] map;
    static int time, dir;
    static Queue<String[]> movements = new LinkedList<>();
    static Deque<int[]> snake = new ArrayDeque<>();// 어차피 꼬리랑 머리만 제

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N]; // 사과 2, 뱀 1, 빈칸 0
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 2;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            movements.offer(new String[]{st.nextToken(), st.nextToken()});
        }

        snake.offer(new int[]{0, 0});
        map[0][0] = 1;

        while (move()) ;

        System.out.print(time);
    }

    // L 왼쪽, D 오른쪽

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    public static boolean move() {

        if (!movements.isEmpty() && time == Integer.parseInt(movements.peek()[0])) { // 방향 전환 o
            String[] movement = movements.poll();
            if (movement[1].equals("D"))
                dir = (dir + 1) % 4; //0->1, 1->2, 2->3, 3->4
            else dir = (dir + 3) % 4; //0->3, 3->2, 2->1, 1->0
        }

        time++;

        int[] pos = snake.peekLast(); // 머리 확인
        int nx = pos[0] + dx[dir];
        int ny = pos[1] + dy[dir]; // 새로운 머리

        if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1) return false;
//        if (map[nx][ny] == 2) {
//            snake.offer(new int[]{nx, ny});
//            map[nx][ny] = 1;
//        } else {
//            int[] popTail = snake.poll();
//            map[popTail[0]][popTail[1]] = 0;
//            if (map[nx][ny] == 1) {
//                return false;
//            } else {
//                snake.offer(new int[]{nx, ny});
//                map[nx][ny] = 1;
//            }
//        }
        // 빈 칸일 때 : 꼬리 뽑아서 머리 삽입
        // 사과있을 떄 : 머리만 삽입
        if (map[nx][ny] == 0) { // 빈 칸이거나, 뱀 자리일 때
            int[] popTail = snake.poll();
            map[popTail[0]][popTail[1]] = 0;
        }
        snake.offer(new int[]{nx, ny});
        map[nx][ny] = 1;

        return true;
    }
}
