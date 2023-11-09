T = int(input())
test = []

for _ in range(T):
    test.append(int(input()))

n = max(max(test), 5)

DP = [0 for _ in range(n + 1)]

DP[1] = 1
DP[2] = 1
DP[3] = 1
DP[4] = 2
DP[5] = 2

for i in range(6, n + 1):
    DP[i] = DP[i - 1] + DP[i - 5]

for number in test:
    print(DP[number])
