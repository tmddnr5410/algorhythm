N = int(input())
DP = [0 for _ in range(N + 2)]
DP[1] = 1
DP[2] = 2

for i in range(3, N + 1):
    DP[i] = (DP[i - 1] + DP[i - 2]) % 15746
print(DP[N])
