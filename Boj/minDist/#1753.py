import heapq
import sys

input = sys.stdin.readline
INF = int(1e9)

V, E = map(int, input().split())
start_v = int(input())
graph = [[] for _ in range(V + 1)]
dist = [INF] * (V + 1)

for _ in range(E):
    start, end, d = map(int, input().split())
    graph[start].append((end, d))

queu = []
dist[start_v] = 0
heapq.heappush(queu, (start_v, 0))

while queu:
    now, min_d = heapq.heappop(queu)
    if dist[now] < min_d:
        continue
    for i in graph[now]:
        cost = min_d + i[1]
        if dist[i[0]] > cost:
            dist[i[0]] = cost
            heapq.heappush(queu, (i[0], dist[i[0]]))

for i in range(1, V + 1):
    if dist[i] == INF:
        print("INF")
    else:
        print(dist[i])
