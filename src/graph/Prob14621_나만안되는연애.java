package graph;

import java.io.*;
import java.util.*;


/*
4 3
M W M W
1 2 1
3 4 2
1 4 2

4 2
M W M W
1 2 3
3 4 4
 */

public class Prob14621_나만안되는연애 {
    static int N, M;
    //static School[] schools;
    static ArrayList<Edge> edgeLists = new ArrayList<>();
    static int[] parents;

    //static boolean[] visit;
    static int answer;

    static class Edge implements Comparable<Edge> {
        int from, to, dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

//    static class School {
//        char type;
//        int vertex;
//        int distance;
//        School next;
//
//
//        School(char type, int vertex, int distance, School next) {
//            this.type = type;
//            this.vertex = vertex;
//            this.distance = distance;
//            this.next = next;
//        }
//    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //schools = new School[N];
        parents = new int[N];
        //visit = new boolean[N];
        for (int i = 0; i < N; i++) parents[i] = i;

        char[] types = new char[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) // head만 생성
            types[i] = st.nextToken().charAt(0);
        int U, V, D;
        for (int i = 0; i < M; i++) { // 연결 요소 생성
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken()) - 1;
            V = Integer.parseInt(st.nextToken()) - 1;
            D = Integer.parseInt(st.nextToken());
            if (types[U] == types[V]) continue;
            edgeLists.add(new Edge(U, V, D));
//            if (schools[U] == null) {
//                schools[U] = new School(types[V], V, D, schools[U]);
//            } else {
//                schools[U].next = new School(types[V], V, D, schools[U].next);
//            }
//            if (schools[V] == null) {
//                schools[V] = new School(types[U], U, D, schools[V]);
//            } else {
//                schools[V].next = new School(types[U], U, D, schools[V].next);
//            }
        } // end of Input
//        boolean connect = true;
//        int compare = parents[0];
//        for (int i = 1; i < N; i++)
//            if (compare != parents[i]) {
//                connect = false;
//                break;
//            }
//        if (!connect) System.out.print(-1);
//        else {
//            // prim 사용
//            prim();
//            System.out.println(answer);
//        }
        Collections.sort(edgeLists);
        for (Edge edge : edgeLists) {
            if (union(edge.from, edge.to)) {
                answer += edge.dist;
            }
        }
        for (int i = 1; i < N; i++)
            if (findSet(i) != findSet(i - 1)) { // 이 부분을 꼭 할 수 밖에 없나? 다른 방법?
                answer = 0;
                break;
            }
        System.out.print(answer == 0 ? -1 : answer);
    }

//    public static void prim() {
//        int[] minEdge = new int[N];
//        Arrays.fill(minEdge, Integer.MAX_VALUE);
//        minEdge[0] = 0;
//        int min, minVertex;
//        for (int i = 0; i < N; i++) {
//            min = Integer.MAX_VALUE;
//            minVertex = 0;
//            for (int j = 0; j < N; j++) { // 가장 가까운 노드
//                if (!visit[j] && minEdge[j] < min) {
//                    minVertex = minEdge[j];
//                    min = j;
//                }
//            }
//            visit[min] = true;
//            answer += minVertex;
//
//            // 그 가까운 노드로부터 가까운 노드
//            for (School school = schools[min]; school != null; school = school.next) {
//                if (!visit[school.vertex] && minEdge[school.vertex] > school.distance)
//                    minEdge[school.vertex] = school.distance;
//            }
//
//        }
//
//    }

    public static int findSet(int a) {
        if (parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
//        int tmp = parents[bRoot];
//        for (int i = 0; i < N; i++) // 이거 생략하려면 어떻게..?
//            if (parents[i] == tmp) parents[i] = aRoot;
        parents[bRoot] = aRoot;
        return true;
    }
}
