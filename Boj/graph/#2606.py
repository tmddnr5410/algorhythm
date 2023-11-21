import sys
from collections import deque

input = sys.stdin.readline


N = int(input())
E = int(input())

graph = [[] for _ in range(N + 1)]
visit = [False] * (N + 1)
ans = 0
queu = deque()

for _ in range(E):
    start, end = map(int, input().split())
    graph[start].append(end)
    graph[end].append(start)

queu.append(1)
visit[1] = True
while queu:
    now = queu.popleft()
    for node in graph[now]:
        if not visit[node]:
            visit[node] = True
            queu.append(node)
            ans += 1

print(ans)
