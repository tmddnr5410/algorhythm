answer = 0


def DFS(idx, numbers, num, sum, target):
    global answer
    N = len(numbers)
    sum += num

    if idx == N:
        if sum == target:
            answer += 1
        return

    DFS(idx + 1, numbers, numbers[idx], sum, target)
    DFS(idx + 1, numbers, numbers[idx] * -1, sum, target)


def solution(numbers, target):
    DFS(0, numbers, 0, 0, target)
    return answer


print(solution([4, 1, 2, 1], 4))
