package simulation;

import java.io.*;
import java.util.*;

public class Prob19238_스타트택시 {
    static int N, M, oil;
    static int[][] map;
    static int[] taxistart = new int[2];
    static ArrayList<int[]> customers = new ArrayList<>();
    static HashSet<Integer>[][] dest;
    static boolean[][] visit;
    static int[] dx = {0, -1, 1, 0, 0}; // 출발지 = 목적지인 경우를 처리해줘야 하기 때문에 0,0 도 넣어줌
    static int[] dy = {0, 0, 0, -1, 1};
    static int success;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        oil = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N][N];
        dest = new HashSet[N][N]; // 목적지 여러개 처리 위해 set
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dest[i][j] = new HashSet<>(); // 맵 전체 도는 김에 같이 객체 생성
            }
        }
        st = new StringTokenizer(br.readLine());
        taxistart[0] = Integer.parseInt(st.nextToken()) - 1;
        taxistart[1] = Integer.parseInt(st.nextToken()) - 1;

        int start_x, start_y, end_x, end_y;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start_x = Integer.parseInt(st.nextToken()) - 1;
            start_y = Integer.parseInt(st.nextToken()) - 1;
            end_x = Integer.parseInt(st.nextToken()) - 1;
            end_y = Integer.parseInt(st.nextToken()) - 1;
            map[start_x][start_y] = -(i + 1);   // 출발지 (음수)
            dest[end_x][end_y].add(i + 1);      // 목적
        }
        int res;
        for (int i = 0; i < M; i++) {
            res = takeup();   // 손님 태우기
            if (res == -1) {
                System.out.print(-1);
                break;
            } else oil += res;
        }
        if (success == M)
            System.out.print(oil);
    }

    public static int takeup() {
        customers.clear();
        for (int i = 0; i < N; i++)
            Arrays.fill(visit[i], false);

        Queue<int[]> taxi = new LinkedList<>();
        taxi.offer(taxistart);

        boolean found = false;
        int nx, ny, size, tmp_oil = oil;
        int[] curr;

        // 1. 찾을 때까지
        // 2. 기름 남아있을 때까지
        while (!taxi.isEmpty() && !found && tmp_oil > 0) {
            tmp_oil--;
            size = taxi.size();
            while (size-- > 0) {
                curr = taxi.poll();
                for (int i = 0; i < 5; i++) {
                    nx = curr[0] + dx[i];
                    ny = curr[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 || visit[nx][ny])
                        continue;
                    visit[nx][ny] = true;
                    if (map[nx][ny] < 0) {
                        found = true;   // 이번 단계 끝나고 out (같은 거리 찾아야하므로 바로 안 빠져나감)
                        customers.add(new int[]{nx, ny});   // 출발지
                        if (i == 0) {   // 현재 위치에서 찾음 (기름 안 닳음)
                            tmp_oil++;
                            break;
                        }
                    } else taxi.offer(new int[]{nx, ny}); // 이동중
                }
            }
        }

        if (!customers.isEmpty()) {
            customers.sort(new Comparator<int[]>() {    // 우선순위 정렬
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] != o2[0]) return o1[0] - o2[0];
                    else return o1[1] - o2[1];
                }
            });

            int res;
            int[] first = customers.get(0);
            int num = -(map[first[0]][first[1]]);
            taxistart[0] = first[0];    // 택시 시작점 변경
            taxistart[1] = first[1];
            // 손님 태움
            map[first[0]][first[1]] = 0;  // 시작 위치 지움
            // 데려다 줄 수 있는가?
            oil = tmp_oil; // 기름값 자체를 갱신
            if ((res = takedown(oil, num)) != -1) { // 손님 내려주러 감 (반환: 남은 기름값 / -1 = 못 내려줌)
                success++;
                return oil - res; // 사용한 기름값 반환
            }
        }
        return -1; // 태울 사람 없으면 바로 return
    }

    public static int takedown(int tmp_oil, int num) {
        for (int i = 0; i < N; i++)
            Arrays.fill(visit[i], false);

        Queue<int[]> taxi = new LinkedList<>();
        taxi.offer(taxistart);

        int nx, ny, size;
        int[] curr;
        while (!taxi.isEmpty() && tmp_oil > 0) {
            tmp_oil--;
            size = taxi.size();
            while (size-- > 0) {
                curr = taxi.poll();
                for (int i = 0; i < 5; i++) {
                    nx = curr[0] + dx[i];
                    ny = curr[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 || visit[nx][ny])
                        continue;
                    visit[nx][ny] = true;
                    if (dest[nx][ny].contains(num)) { // 도착점
                        if (i == 0)     // 기름 안 씀 현재 위치 = 도착점
                            tmp_oil++;
                        taxistart[0] = nx;
                        taxistart[1] = ny;
                        return tmp_oil; // 목적지 찾음
                    } else taxi.offer(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }
}
