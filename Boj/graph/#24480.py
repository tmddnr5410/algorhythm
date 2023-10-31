import sys

sys.setrecursionlimit(10**8)


def DFS(now):
    global depth
    visited[now] = True
    answer[now] = depth
    graph[now].sort(reverse=True)
    for end in graph[now]:
        if not visited[end]:
            depth += 1
            DFS(end)


N, M, R = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N + 1)]
visited = [False] * (N + 1)
answer = [0] * (N + 1)
depth = 1

for _ in range(M):
    start, end = map(int, sys.stdin.readline().split())
    graph[start].append(end)
    graph[end].append(start)


DFS(R)

for ch in answer[1:]:
    print(ch)
