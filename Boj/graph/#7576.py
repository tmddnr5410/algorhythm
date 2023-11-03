import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
graph = []

for _ in range(M):
    graph.append(list(map(int, input().split())))

queu = deque()

for i in range(M):
    for j in range(N):
        if graph[i][j] == 1:
            queu.append([i, j])

x, y = 0, 0
d = [[1, 0], [-1, 0], [0, 1], [0, -1]]
while queu:
    x, y = queu.popleft()
    for dx, dy in d:
        if 0 <= x + dx < M and 0 <= y + dy < N and graph[x + dx][y + dy] == 0:
            graph[x + dx][y + dy] = graph[x][y] + 1
            queu.append([x + dx, y + dy])

ans = graph[x][y] - 1

for i in range(M):
    for j in range(N):
        if graph[i][j] == 0:
            ans = -1
            break

print(ans)
