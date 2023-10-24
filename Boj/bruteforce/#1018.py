N, M = map(int, input().split())
board = []
for _ in range(N):
    board.append(input())

mincnt = 1e9


def countChange(col, low):
    cnt = 0
    global mincnt
    for k in range(8):
        for t in range(8):
            if (k + t) % 2 == 0 and board[k + col][t + low] == "B":
                cnt += 1
            elif (k + t) % 2 == 1 and board[k + col][t + low] == "W":
                cnt += 1
    mincnt = min(mincnt, cnt, 64 - cnt)


for i in range(N - 7):
    for j in range(M - 7):
        countChange(i, j)

print(mincnt)
