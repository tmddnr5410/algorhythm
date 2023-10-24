def solution(n, results):
    answer = 0
    rank = [0] * (n + 1)
    for win, lose in results:
        rank[win] += 1
        rank[lose] += 1

    return answer
