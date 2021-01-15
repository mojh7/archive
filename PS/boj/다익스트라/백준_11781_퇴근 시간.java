/*
 * 2021-01-15
 * https://www.acmicpc.net/problem/11781
 * 백준 다익스트라 골드2
 *
 * 1% 부터 틀렸다. 
System.out.println() 로 출력할 때 다익스트라 결과로 가장 늦게 도착하게 되는 지점까지의 도착 시각을 double로 리턴 받아서
출력하니 3.5E9 같은 형태로 출력되는데 다양한 형태의 출력값을 인정해주는 스폐셜져지 문제가 아니라서
4 3 0 1000000000
1 2 1000000000 1 0
2 3 1000000000 0 0
3 4 1000000000 0 0
입력일 때
3.5E9가 아닌 3500000000가 찍혀야 된다.

이렇게 바꾸니 1~7%까진 되고 8%에서 틀렸다.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    // 0 <= s < e <= 10억, 1 <= L <= 10억
    // 15억 + 10억 * (5000 - 2) = 49995억
    private static double INF = 4999500000001L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        double s = Integer.parseInt(st.nextToken());
        double e = Integer.parseInt(st.nextToken());
        int u, v, len, t1, t2;
        Road road;
        ArrayList<Road>[] roads = new ArrayList[n+1];
        for(int idx = 1; idx <= n; idx++) {
            roads[idx] = new ArrayList<>();
        }
        for(int idx = 0; idx < m; idx++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            len = Integer.parseInt(st.nextToken());
            t1 = Integer.parseInt(st.nextToken());
            t2 = Integer.parseInt(st.nextToken());
            road = new Road(u, v, len, t1, t2);
            roads[u].add(road);
            roads[v].add(road);
        }

        double maxDistance = dijkstra(roads, n, s, e);
        if(maxDistance == (long)maxDistance) {
            System.out.printf("%.0f", maxDistance);
        } else {
            System.out.printf("%.1f", maxDistance);
        }
    }

    public static double dijkstra(ArrayList<Road>[] roads, int n, double s, double e) {
        double max = 0l;
        boolean[] isVisited = new boolean[n+1];
        boolean isCongestion;
        double nw = 0L;
        double remainingDist = 0L;
        double congestionEntrytime = 0L;
        Node[] nodes = new Node[n+1];
        for(int idx = 0; idx < nodes.length; idx++) {
            nodes[idx] = new Node(idx, INF);
        }
        nodes[1].time = 0;
        Node cur;
        Node next;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(nodes[1]);

        while (!pq.isEmpty()) {
            cur = pq.poll();
            if(isVisited[cur.id]) continue;
            isVisited[cur.id] = true;

            for(Road road : roads[cur.id]) {
                next = nodes[road.opposite(cur.id)];
                isCongestion = road.isCongestion(cur.id);
                nw = cur.time + road.dist;
                if(isCongestion && s < nw && cur.time < e) {
                    remainingDist = road.dist;
                    if(cur.time < s) {
                        remainingDist -= s - cur.time;
                        congestionEntrytime = s;
                    } else {
                        congestionEntrytime = cur.time;
                    }
                    if(congestionEntrytime + remainingDist * 2L <= e) {
                        nw = congestionEntrytime + remainingDist * 2L;
                    } else {
                        remainingDist -= (e - congestionEntrytime) / 2L;
                        nw = e + remainingDist;
                    }
                }

                if(nw < next.time) {
                    next.time = nw;
                    pq.add(next);
                }
            }
        }
        for(Node node : nodes) {
            if(node.time != INF && max < node.time) {
                max = node.time;
            }
        }
        return max;
    }
}

class Node implements Comparable<Node> {
    int id;
    double time;
    public Node(int id, double w) {
        this.id = id;
        this.time = w;
    }

    @Override
    public int compareTo(Node o) {
        return time - o.time < 0 ? -1 : time == o.time ? 0 : 1;
    }
}

class Road {
    int u;
    int v;
    double dist;
    int t1;
    int t2;
    public Road(int u, int v, double dist, int t1, int t2) {
        this.u = u;
        this.v = v;
        this.dist = dist;
        this.t1 = t1;
        this.t2 = t2;
    }

    public int opposite(int id) {
        if(id == u) {
            return v;
        } else if(id == v) {
            return u;
        }
        return -1;
    }

    public boolean isCongestion(int start) {
        if(t1 == 1 && start == u) {
            return true;
        } else if(t2 == 1 && start == v) {
            return true;
        }
        return false;
    }
}