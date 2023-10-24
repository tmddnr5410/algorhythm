def solution(n, results):
    answer = 0
    graph = [[] for _ in range(n + 1)]
    rank = [0] * (n + 1)

    for win, lose in results:
        graph[win].append(lose)

    # 각 정점에서 target까지 경로 탐색
    for start in range(1, n + 1):
        dist = [-1] * (n + 1)
        # 시작정점을 방문했다고 가정하고 시작
        stack = []
        stack.append(start)
        now = start
        dist[start] = 0
        while stack:
            for i in graph[now]:
                if dist[i] == -1:
                    dist[i] = dist[now] + 1
                    stack.append(i)
                    now = i
                    print(i, "진입")
                    continue

            now = stack.pop()
            print("경로없음 pop", now)
        print(dist)
        for i in range(1, len(dist)):
            if dist[i] > 0:
                rank[i] += 1
    return rank.index(max(rank))
