def solution(k, dungeons):
    answer = -1
    n = len(dungeons)

    def DFS(visit, cnt, remain):
        nonlocal answer
        if answer < cnt:
            answer = cnt
        for i in range(n):
            if visit[i] == 0 and dungeons[i][0] <= remain:
                visit[i] = 1
                DFS(visit, cnt + 1, remain - dungeons[i][1])
                visit[i] = 0
        return

    visit = [0] * n
    DFS(visit, 0, k)
    if answer == 0:
        answer = -1
    return answer


print(solution(80, [[80, 20], [50, 40], [30, 10]]))
