N = int(input())
nums = list(map(int, input().split()))
max_len = 0
for i in range(N):
    temp = nums[i]

    cnt = 1

    for j in range(i, N):
        if temp < nums[j]:
            cnt += 1
            temp = nums[j]

    if max_len < cnt:
        max_len = cnt

print(max_len)
# N = int(input())
# nums = []
# for _ in range(N):
#     nums.append(int(input()))
# start = 1
# end = N
# temp = 0
# while start <= end:
#     mid = (start + end) // 2
#     cnt = 0
#     for num in nums:
#         if temp < num:
#             cnt += 1
#             temp = num
#     print(temp)
