import sys

N, M = map(int, input().split())
num = []


def DFS(depth):
    if depth == M:
        for ch in num:
            print(ch, end=" ")
        print()
        return
    for i in range(1, N + 1):
        num.append(i)
        DFS(depth + 1)
        num.pop()


DFS(0)
