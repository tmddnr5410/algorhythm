from collections import deque
import sys

input = sys.stdin.readline
N, M, R = map(int, input().split())


graph = [[] for _ in range(N + 1)]
visit = [False] * (N + 1)
ans = [0] * (N + 1)
cnt = 0
queu = deque()

for i in range(M):
    start, end = map(int, input().split())
    graph[start].append(end)
    graph[end].append(start)

queu.append(R)
visit[R] = True
ans[R] = 1
while queu:
    now = queu.popleft()
    # print("\n\n현재 방문지점", R)

    graph[now].sort()
    for end in graph[now]:
        if not visit[end]:
            # print("방문 ", end, end=" ")
            queu.append(end)
            visit[end] = True
            cnt += 1
            ans[end] = cnt + 1

for ch in ans[1:]:
    print(ch)
