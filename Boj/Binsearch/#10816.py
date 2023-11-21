import sys

input = sys.stdin.readline

N = int(input())
card = list(map(int, input().split()))
M = int(input())
find = list(map(int, input().split()))

card = sorted(card)


def binsearch_upper(target, arr):
    start = 0
    end = len(arr)
    while start < end:
        mid = (start + end) // 2
        if arr[mid] <= target:
            start = mid + 1
        else:
            end = mid
    return start


def binsearch_lower(target, arr):
    start = 0
    end = len(arr)
    while start < end:
        mid = (start + end) // 2
        if target <= arr[mid]:
            end = mid
        else:
            start = mid + 1
    return start


for num in find:
    lower = binsearch_lower(num, card)
    upper = binsearch_upper(num, card)
    print(upper - lower, end=" ")
