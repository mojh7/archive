/*
 * 2021-01-08
 * https://www.acmicpc.net/problem/1516
 * 백준 트리 골드3
 * 
 * 위상 정렬 해서 풀어야 겠다는 생각 까진 했으나 먼저 지어져야 하는 건물들 시간 계산하는 것을
 * 잘 못 생각해냄.
4
1 -1
2 1 3 -1
3 4 -1
4 -1
일 때 2가 1번 노드의 시간 + 3번 노드의 시간으로 계산해야되나? 생각 했다가 3번 노드에서 1번 노드
에 대한 처리가 겹친다고 착각하여 헤맴
풀이 참고하여 풀음.
어떤 특정 건물이 지어지기 전에 필요한 먼저 지어져야 하는 건물들 중에서 가장 오래걸리는 것만
찾아서 시간을 더해주면 됨.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int prev;
        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n + 1];
        for (int idx = 1; idx <= n; idx++) {
            nodes[idx] = new Node();
        }

        for (int cur = 1; cur <= n; cur++) {
            st = new StringTokenizer(br.readLine());
            nodes[cur].time = Integer.parseInt(st.nextToken());
            prev = Integer.parseInt(st.nextToken());
            while (prev != -1) {
                nodes[prev].next.add(nodes[cur]);
                nodes[cur].prev.add(nodes[prev]);
                nodes[cur].indegree += 1;
                prev = Integer.parseInt(st.nextToken());
            }
        }

        topologicalSort(nodes, n);

        for (int idx = 1; idx <= n; idx++) {
            bw.write(Integer.toString(nodes[idx].time));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static void topologicalSort(Node[] nodes, int n) {
        Queue<Node> q = new LinkedList<>();
        Node cur;

        for(int idx = 1; idx <= n; idx++) {
            cur = nodes[idx];
            if(cur.indegree == 0) {
                q.add(cur);
            }
        }

        while (!q.isEmpty()) {
            cur = q.poll();
            cur.calcCompletedTime();
            for(Node nextNode : cur.next) {
                if(--nextNode.indegree == 0) {
                    q.add(nextNode);
                };
            }
        }
    }
}

class Node {
    int time;
    int indegree;
    LinkedList<Node> prev;
    LinkedList<Node> next;

    public Node() {
        indegree = 0;
        prev = new LinkedList<>();
        next = new LinkedList<>();
    }

    public void calcCompletedTime() {
        int prevMaxTime = 0;
        for(Node prevNode : prev) {
            if(prevMaxTime < prevNode.time) {
                prevMaxTime = prevNode.time;
            }
        }
        time += prevMaxTime;
    }
}