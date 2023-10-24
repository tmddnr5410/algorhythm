N, M = map(int, input().split())
visit = [False] * N
answer = []


def DFS(now, depth):
    if depth == M:
        for ch in answer:
            print(ch, end=" ")
        print()
        return
    for i in range(N):
        if not visit[i] and now < i + 1:
            visit[i] = True
            answer.append(i + 1)
            DFS(i + 1, depth + 1)
            answer.pop()
            visit[i] = False


DFS(0, 0)
