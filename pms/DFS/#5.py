from collections import deque


def solution(tickets):
    tickets = sorted(tickets)
    n = len(tickets)

    for i in range(n):
        visit = [0] * n
        curr = i
        visit[curr] = 1
        start, end = tickets[curr]
        # print(tickets[curr], "사용, 현재 start,end :", start, end)
        answer = [start, end]
        # print(start, end)

        while True:
            if 0 not in visit:
                return answer
            x = 0
            for j in range(n):
                if visit[j] == 0 and end == tickets[j][0]:
                    curr = j
                    visit[curr] = 1
                    start, end = tickets[curr]
                    # print(tickets[curr], "사용, 현재 start,end :", start, end)
                    # print(end)
                    answer.append(tickets[curr][1])
                    x += 1
                    break
            if x == 0:
                # print("\n사용할 티켓이 없어 break\n")
                break
    return answer


ans = solution(
    [["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL", "SFO"]]
)
print(ans)
