w = [[[1 for _ in range(21)] for _ in range(21)] for _ in range(21)]

for i in range(21):
    for j in range(21):
        for k in range(21):
            if i <= 0 or j <= 0 or k <= 0:
                continue
            elif i < j and j < k:
                # print(f"w({i}, {j}, {k-1}) = {w[i][j][k-1]}")
                # print(f"w({i}, {j-1}, {k-1}) = {w[i][j-1][k-1]}")
                # print(f"w({i}, {j-1}, {k}) = {w[i][j-1][k]}")
                w[i][j][k] = w[i][j][k - 1] + w[i][j - 1][k - 1] - w[i][j - 1][k]
            else:
                # print(f"w({i-1}, {j}, {k}) = {w[i-1][j][k]}")
                # print(f"w({i-1}, {j-1}, {k}) = {w[i-1][j-1][k]}")
                # print(f"w({i-1}, {j}, {k-1}) = {w[i-1][j][k-1]}")
                # print(f"w({i-1}, {j-1}, {k-1}) = {w[i-1][j-1][k-1]}")
                w[i][j][k] = (
                    w[i - 1][j][k]
                    + w[i - 1][j - 1][k]
                    + w[i - 1][j][k - 1]
                    - w[i - 1][j - 1][k - 1]
                )


def print_result(x, y, z):
    if a <= 0 or b <= 0 or c <= 0:
        print(f"w({a}, {b}, {c}) = {1}")

    elif a > 20 or b > 20 or c > 20:
        print(f"w({a}, {b}, {c}) = {1048576}")

    else:
        print(f"w({a}, {b}, {c}) = {w[a][b][c]}")


abc = []
while True:
    x, y, z = map(int, input().split())
    if x == -1 and y == -1 and z == -1:
        break
    abc.append([x, y, z])

for a, b, c in abc:
    print_result(a, b, c)
