N = int(input())
card = list(map(int, input().split()))
M = int(input())
find = list(map(int, input().split()))

card = sorted(card)


def binSearchFindFirst(cards, start, end, target):
    while start <= end:
        mid = (start + end) // 2
        if target <= cards[mid]:
            end = mid - 1
        elif cards[mid] < target:
            start = mid + 1
    if cards[start] == target:
        return start
    else:
        return -1


lis = []

for i in range(M):
    count = 0
    idx = binSearchFindFirst(card, 0, N - 1, find[i])

    if idx != -1:
        for i in range(idx, N - 1):
            count += 1
            # print(card[i], card[idx])
            if card[i] != card[idx]:
                print(count)
                lis.append(count)
                break
    else:
        lis.append(count)
