x, y, z = map(int, input().split())
prize = 0
if x == y or y == z or x == z:
    if x == y and y == z:
        print(10000 + x * 1000)
    elif x == y and y != z:
        print(1000 + y * 100)
    elif y == z and x != y:
        print(1000 + y * 100)
    elif x == z and x != y:
        print(1000 + x * 100)
elif x != y and y != z:
    print(max(x, y, z) * 100)
