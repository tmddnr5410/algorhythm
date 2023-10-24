N, C = map(int, input().split())
homes = []
for _ in range(N):
    homes.append(int(input()))

homes = sorted(homes)

start = 1
end = homes[-1] - homes[0]

while start <= end:
    mid = (start + end) // 2
    cnt = 1
    curr = homes[0]
    for i in range(1, N):
        if homes[i] >= curr + mid:
            cnt += 1
            curr = homes[i]

    if C <= cnt:
        start = mid + 1
        result = mid
    elif C > cnt:
        end = mid - 1

print(result)
