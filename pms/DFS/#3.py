def solution(maps):
    answer = 1e9
    stack = [[0, 0]]
    maps[0][0] = 0

    m = len(maps[0])
    n = len(maps)

    dist = 2
    x, y = 0, 0

    while stack:
        print(x, y)
        if x == n - 1 and y == m - 1 and answer > dist:
            answer = dist
            x, y = stack.pop()
            continue
        if y < n - 1 and maps[x][y + 1]:
            dist += 1
            y += 1
            maps[x][y] = 0
            stack.append([x, y])
        elif x < m - 1 and maps[x + 1][y]:
            dist += 1
            x += 1
            maps[x][y] = 0
            stack.append([x, y])
        elif y > 0 and maps[x][y - 1]:
            dist += 1
            y -= 1
            maps[x][y] = 0
            stack.append([x, y])
        elif x > 0 and maps[x - 1][y]:
            dist += 1
            x -= 1
            maps[x][y] = 0
            stack.append([x, y])
        else:
            x, y = stack.pop()
            dist -= 1

    return answer


ans = solution(
    [
        [1, 0, 1, 1, 1],
        [1, 0, 1, 0, 1],
        [1, 0, 1, 1, 1],
        [1, 1, 1, 0, 1],
        [0, 0, 0, 0, 1],
    ]
)
print(ans)
