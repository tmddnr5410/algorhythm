from collections import deque
import sys

input = sys.stdin.readline

N, M, R = map(int, input().split())

graph = [[] for _ in range(N + 1)]

for i in range(M):
    start, end = map(int, input().split())
    graph[start].append(end)
    graph[end].append(start)

visited = [False] * (N + 1)
ans = [0] * (N + 1)
cnt = 1

queu = deque()
queu.append(R)

visited[R] = True
ans[R] = cnt

while queu:
    now = queu.popleft()
    graph[now].sort(reverse=True)
    for end in graph[now]:
        if not visited[end]:
            cnt += 1
            visited[end] = True
            ans[end] = cnt
            queu.append(end)

for ch in ans[1:]:
    print(ch)
