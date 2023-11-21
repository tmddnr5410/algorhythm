n = int(input())
for _ in range(n):
    N, M = map(int, input().split())
    M_P = 1
    MN_P = 1
    N_P = 1
    for i in range(1, M + 1):
        M_P = M_P * i
        if i == N:
            N_P = M_P
        if i == M - N:
            MN_P = M_P
    print(M_P // (MN_P * N_P))
