간단 팁, 예시 코드 모음 및 바로 찾기

---

## 바로가기

- [바로가기](#바로가기)
- [two pointers](#two-pointers)
- [binary serach](#binary-serach)
- [divide conquer](#divide-conquer)
- [bfs](#bfs)
- [dfs](#dfs)
- [backtracking](#backtracking)
- [brute force](#brute-force)
- [dijkstra](#dijkstra)
- [floyd warshall](#floyd-warshall)
- [union find](#union-find)
- [prim](#prim)
- [kruskal](#kruskal)
- [trie](#trie)
- [dp](#dp)
- [regular expression](#regular-expression)
- [comparable, comparator](#comparable-comparator)

---

## two pointers

```
```

[맨 위로](#바로가기)

---

## binary serach

```
```

[맨 위로](#바로가기)

---

## divide conquer

```
```

[맨 위로](#바로가기)

---

## bfs

```
```

[맨 위로](#바로가기)

---

## dfs

```
```

[맨 위로](#바로가기)

---

## backtracking

```
```

[맨 위로](#바로가기)

---

## brute force

```
```

[맨 위로](#바로가기)

---

## dijkstra

> 음의 간선 없을 때 정상 동작

```
List<List<Pair<Integer, Integer>>> edges = new ArrayList<>(eSize + 1);
long[] dist = new long[vSize + 1];
Arrays.fill(dist, 1, dist.length, INF_NUM);
dist[s] = 0;
for(int i = 0; i < vSize + 1; i++){
    edges.add(new LinkedList<Pair<Integer, Integer>>());
}

public static void dijkstra(List<List<Pair<Integer, Integer>>> edges, long[] dist, int s){
    PriorityQueue<Pair<Integer, Long>> pq = new PriorityQueue<>(
            (o1, o2) -> o1.second < o2.second ? -1 : 1);
    pq.add(new Pair(s, 0L));
    Pair<Integer, Long> node = null;
    long cost;
    while(!pq.isEmpty()){
        node = pq.poll();

        if(dist[node.first] < node.second){
            continue;
        }


        for(Pair<Integer, Integer> edge : edges.get(node.first)){
            cost = node.second + edge.second;
            if(cost < dist[edge.first]){
                dist[edge.first] = cost;
                pq.add(new Pair(edge.first, cost));
            }
        }
    }
}
```

[맨 위로](#바로가기)

---
## floyd warshall

> 노드 갯수가 n일 때 시간복잡도 o(n^3)
> 
> 노드 갯수가 적을 때 사용

```

public void floydWarshall(int[][] d) {
    int v = d.length;
    for(int k = 0; k < v; k++) {
        for(int i = 0; i < v; i++) {
            for(int j = 0; j < v; j++) {
                d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
            }
        }
    }
}
```

[맨 위로](#바로가기)

---

## union find

```
public void union(int[] parents, int x, int y) {
    x = find(parents, x);
    y = find(parents, y);
    if(x != y) {
        parents[y] = x;
    }
}

public int find(int[] parents, int x) {
    if(x == parents[x]) {
        return x;
    }

    int p = find(parents, parents[x]);
    parents[x] = p;
    return p;
}
```

[맨 위로](#바로가기)

---

## prim

```
```

[맨 위로](#바로가기)

---

## kruskal

```
```

[맨 위로](#바로가기)

---

## trie

```
class TrieNode {
    TrieNode[] childern;
    int cnt = 0;

    public TrieNode() {
        childern = new TrieNode[26];
        cnt = 0;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        int charToIdx = 0;
        for(char c : word.toCharArray()) {
            charToIdx = c - 97;
            if(cur.childern[charToIdx] == null) {
                cur.childern[charToIdx] = new TrieNode();
            }
            cur.cnt++;
            cur = cur.childern[charToIdx];
        }
        cur.cnt = 1;
    }

    public int getMatchedWordsCnt(String query, int wildCardIdx) {
        TrieNode cur = root;
        int charToIdx = 0;
        char c;

        for(int idx = 0; idx < wildCardIdx; idx++) {
            c = query.charAt(idx);
            charToIdx = c - 97;
            if(cur.childern[charToIdx] == null) {
                return 0;
            }
            cur = cur.childern[charToIdx];
        }

        return cur.cnt;
    }
}
```

[맨 위로](#바로가기)

---

## dp

```
```

[맨 위로](#바로가기)

---

## regular expression

```
```

[맨 위로](#바로가기)

---



```
```

[맨 위로](#바로가기)

---

## comparable, comparator

```
```

[맨 위로](#바로가기)

---