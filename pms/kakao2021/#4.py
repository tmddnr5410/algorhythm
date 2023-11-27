n = 3
start = 1
end = 3
roads = [[1, 2, 1], [3, 2, 1], [2, 4, 1]]
traps = [2, 3]


graph = [[] for _ in range(n + 1)]

visit = [1 for _ in range(n + 1)]

for trap in traps:
    visit[trap] = 2

for start, end, dist in roads:
    graph[start].append([end, dist])

sum_dist = 0
min_dist = 1e9


def DFS(now):
    global sum_dist

    if now == end:
        min_dist = min(min_dist, sum_dist)

    for end, dist in graph[now]:
        if visit[end] > 0:
            while graph[end]:
                reverse_end, reverse_dist = graph[end].pop(0)
                graph[reverse_end]
            visit[end] -= 1
            sum_dist += dist
            DFS(end)
            visit[end] += 1
            sum_dist -= dist
