/*
 * 2021-02-13
 * https://www.acmicpc.net/problem/1939
 * 백준 이분 탐색 골드 4
 * 풀이중, 71% 에서 시간초과

--- 첫 풀이
이분 탐색 문제로 찾았으나 알고리즘 분류에 bfs도 있어서 bfs로 풀어보는 방향을 먼저 생각함.
시작점에서 도착지점까지 bfs 하며 현재까지 지나온 경로에서 최소 중량제한과 현재 위치에서
연결된 다리의 중량제한에서 작은 값이 다음 지점의 지금까지의 중량제한 값 보다 크면
다음 지점을 queue에 넣어서 bfs 진행

 */

// 첫 풀이, 71% 에서 시간 초과
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int n, m, src, dest;
    static ArrayList<Edge>[] bridges;

    public static void main(String[] args) throws IOException {
        int answer = 0;
        int a, b, c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        bridges = new ArrayList[n+1];
        for(int idx = 1; idx <= n; idx++) {
            bridges[idx] = new ArrayList<>();
        }
        for (int idx = 0; idx < m; idx++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(a, b, c);
            bridges[a].add(edge);
            bridges[b].add(edge);
        }
        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dest  = Integer.parseInt(st.nextToken());

        answer = bfs();
        System.out.println(answer);
    }

    // src -> dest 모든 경로에서 경로 마다 중량제한 최소치가 맥스인 값 찾기
    public static int bfs() {
        int[] maxWeights = new int[n+1];
        maxWeights[src] = 1000000001;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{ src, maxWeights[src] });
        int[] current;
        int weightLimit;
        int opposite;
        while (!q.isEmpty()) {
            current = q.poll();
            if(current[1] < maxWeights[dest]) continue;

            for(Edge edge : bridges[current[0]]) {
                opposite = edge.opposite(current[0]);
                weightLimit = Math.min(current[1], edge.c);
                if(weightLimit < maxWeights[opposite]) continue;
                if(maxWeights[opposite] < weightLimit) {
                    maxWeights[opposite] = weightLimit;
                    if(opposite != dest) {
                        q.add(new int[]{ opposite, weightLimit});
                    }
                }
            }
        }
        return maxWeights[dest];
    }
}

class Edge {
    int u;
    int v;
    int c;
    public Edge(int u, int v, int c) {
        this.u = u;
        this.v = v;
        this.c = c;
    }

    public int opposite(int num) {
        return num == u ? v : u;
    }
}