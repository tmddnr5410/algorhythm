N, M = map(int, input().split())

stack = []


def DFS(depth, now):
    if depth == M:
        for ch in stack:
            print(ch, end=" ")
        print()
        return
    for i in range(1, N + 1):
        if now <= i:
            stack.append(i)
            DFS(depth + 1, i)
            stack.pop()


DFS(0, 0)
