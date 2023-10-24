A, B = map(int, input().split())
C = int(input())
cooktime = B + C
print((A + (cooktime) // 60) % 24, (cooktime) % 60)
