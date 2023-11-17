for i in range(1, 11):
    t = int(input())
    N, M = map(int, input().split())

    def pow(n, m, depth):
        if depth == m:
            return 1
        else:
            return n * pow(n, m, depth + 1)

    print("#%d %d" % (i, pow(N, M, 0)))
