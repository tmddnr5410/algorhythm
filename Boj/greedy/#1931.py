import sys

input = sys.stdin.readline

N = int(input())
timeline = []
for _ in range(N):
    start, end = map(int, input().split())
    timeline.append([start, end])

timeline.sort(key=lambda x: (x[1], x[0]))
preend = timeline[0][1]
answer = 1

for i in range(1, N):
    if preend <= timeline[i][0]:
        preend = timeline[i][1]
        answer += 1

print(answer)
