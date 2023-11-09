import sys

input = sys.stdin.readline

N = int(input())

DP = []
for _ in range(N):
    DP.append(list(map(int, input().split())))

for depth in range(1, N):
    for row in range(depth + 1):
        if row == depth:
            DP[depth][row] += DP[depth - 1][row - 1]
        elif row == 0:
            DP[depth][0] += DP[depth - 1][0]
        else:
            DP[depth][row] += max(DP[depth - 1][row - 1], DP[depth - 1][row])

print(max(DP[N - 1]))
