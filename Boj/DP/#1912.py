N = int(input())
nums = list(map(int, input().split()))
sum = 0
for now in nums:
    if now < 0:
        if sum <= abs(now):
            sum = 0
    elif now >= 0:
        sum += now

print(sum)
