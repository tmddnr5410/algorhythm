import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, M, R = map(int, input().split())
graph = [[] for _ in range(N + 1)]

for i in range(M):
    start, end = map(int, input().split())
    graph[start].append(end)
    graph[end].append(start)


order = [0] * (N + 1)


def DFS(now):
    global seq
    graph[now].sort()
    for end in graph[now]:
        if order[end] == 0:
            seq += 1
            order[end] = seq
            DFS(end)


seq = 1
order[R] = seq
DFS(R)

for i in range(1, N + 1):
    print(order[i])
