def line_N_pali(k, n, map):
    pali = 0
    for i in range(8 - n + 1):
        pali += 2
        for j in range(0, n // 2):
            if map[k][i + j] != map[k][i + n - 1 - j]:
                pali -= 1
                break
        for j in range(0, n // 2):
            if map[i + j][k] != map[i + n - 1 - j][k]:
                pali -= 1
                break
    return pali


def pali_in_map(N, map):
    ans = 0
    for i in range(8):
        ans += line_N_pali(i, N, map)
    return ans


answer = []
for i in range(10):
    N = int(input())
    map = []
    for _ in range(8):
        map.append(input())
    answer.append([(i + 1), pali_in_map(N, map)])

for t, ans in answer:
    print("#%d %d" % (t, ans))
