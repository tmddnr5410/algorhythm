def haveMagnet(map):
    for line in map:
        for num in line:
            if num == 1 or num == 2:
                return True
    return False


answer = []
for test_case in range(10):
    N = int(input())

    board = []

    for i in range(N):
        board.append(list(map(int, input().split())))

    lock = 0

    while haveMagnet(board):
        for i in range(N):
            for j in range(N):
                if board[i][j] == 0 or board[i][j] == 3:
                    continue
                if board[i][j] == 1:
                    if i + 1 == N:
                        board[i][j] = 0
                    elif i + 1 < N:
                        if board[i + 1][j] == 0:
                            board[i][j] = 0
                            board[i + 1][j] = 1
                        elif board[i + 1][j] == 3:
                            board[i][j] = 3
                        elif board[i + 1][j] == 2:
                            board[i][j] = 3
                            board[i + 1][j] = 3
                            lock += 1

                elif board[i][j] == 2:
                    if i - 1 == -1:
                        board[i][j] = 0
                    elif 0 <= i - 1:
                        if board[i - 1][j] == 0:
                            board[i][j] = 0
                            board[i - 1][j] = 2
                        elif board[i - 1][j] == 3:
                            board[i][j] = 3
                        elif board[i - 1][j] == 1:
                            board[i][j] = 3
                            board[i - 1][j] = 3
                            lock += 1
    print("#%d %d" % (test_case + 1, lock))
