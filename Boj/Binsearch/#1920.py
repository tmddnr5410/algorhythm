N = int(input())
A = list(map(int, input().split()))
M = int(input())
nums = list(map(int, input().split()))
numin = [0] * M


def binsearch(arr, target, start, end):
    while start <= end:
        # print(start, end)
        mid = (start + end) // 2
        if arr[mid] == target:
            return True
        if arr[mid] < target:
            start = mid + 1
        elif arr[mid] > target:
            end = mid - 1
    else:
        return False


A = sorted(A)
for i in range(len(nums)):
    exists = binsearch(A, nums[i], 0, N - 1)
    if exists:
        print(1)
    elif not exists:
        print(0)
