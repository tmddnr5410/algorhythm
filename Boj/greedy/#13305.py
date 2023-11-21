import sys

input = sys.stdin.readline

N = int(input())
distances = list(map(int, input().split()))
oils = list(map(int, input().split()))

charge = 0
now_oil = oils[0]

for i in range(N - 1):
    charge += distances[i] * now_oil
    if oils[i + 1] < now_oil:
        now_oil = oils[i + 1]

print(charge)
