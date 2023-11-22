import sys

input = sys.stdin.readline
n = int(input())
pappers = list(map(int, input().split()))
DP = [[0 for _ in range(n)] for _ in range(n)]

for i in range(n - 1):
    DP[i][i + 1] = pappers[i] + pappers[i + 1]

for i in range(2, n):
    for j in range(0, n - i):
        DP[j][j + i] = min(
            pappers[j] + DP[j + 1][j + i], DP[j][j + i - 1] + pappers[j + i]
        )
        # if i % 2 == 0:
        #     DP[j][i + j] = min(DP[j][i], DP[j][(i // 2) + j] + DP[(i // 2) + 1 + j][i])
for line in DP:
    print(line)
