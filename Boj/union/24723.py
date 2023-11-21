def pow_2(depth, n):
    if depth == n:
        return 1
    return 2 * pow_2(depth + 1, n)


print(pow_2(0, int(input())))
