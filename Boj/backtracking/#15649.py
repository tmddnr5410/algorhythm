N, M = map(int, input().split())
visit = [0] * N
result = []


def DFS(depth):
    if depth == M:
        for ch in result:
            print(ch, end=" ")
        print()
        return

    for i in range(N):
        if visit[i] == 0:
            visit[i] = 1
            result.append(i + 1)
            DFS(depth + 1)
            result.pop()
            visit[i] = 0


DFS(0)
