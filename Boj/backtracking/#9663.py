import os
import time

N = int(input())

board = [[0 for _ in range(N)] for _ in range(N)]
ans = 1


def batchQueen(x, y, n):
    temp = board[x][y]
    for i in range(N):
        board[x][i] += n
        board[i][y] += n

        if x + i < N:
            if y + i < N:
                board[x + i][y + i] += n
            if 0 <= y - i:
                board[x + i][y - i] += n

        if 0 <= x - i:
            if y + i < N:
                board[x - i][y + i] += n
            if 0 <= y - i:
                board[x - i][y - i] += n
    board[x][y] = temp + n


def DFS(depth):
    global ans

    # for line in board:
    #     print(line)
    # print()
    if depth == N:
        ans += 1
        return

    for i in range(N):
        for j in range(N):
            if board[i][j] == 0:
                batchQueen(i, j, 1)
                DFS(depth + 1)
                batchQueen(i, j, -1)


batchQueen(1, 1, 1)
DFS(1)
print(ans)
