import sys

input = sys.stdin.readline
sdoque = []
for _ in range(9):
    sdoque.append(list(map(int, input().split())))

ver = [i for i in range(9)]
hoz = [i for i in range(9)]
square = [i for i in range(9)]

while ver or hoz or square:
    for i in hoz:
        flag = False
        sum = 0
        zero_idx = -1
        for j in range(9):
            sum += sdoque[i][j]
            if sdoque[i][j] == 0:
                if zero_idx != -1:
                    flag = True
                    break
                zero_idx = j
        if flag:
            continue
        zero_cnt = 45 - sum
        if zero_cnt == 0:
            hoz.remove(i)
        elif 1 <= zero_cnt <= 9:
            sdoque[i][zero_idx] = zero_cnt

    for i in ver:
        flag = False
        sum = 0
        zero_idx = -1
        for j in range(9):
            sum += sdoque[j][i]
            if sdoque[j][i] == 0:
                if zero_idx != -1:
                    flag = True
                    break
                zero_idx = j
        if flag:
            continue
        zero_cnt = 45 - sum
        if zero_cnt == 0:
            ver.remove(i)
        elif 1 <= zero_cnt <= 9:
            sdoque[zero_idx][i] = zero_cnt

    for i in square:
        x = (i // 3) * 3
        y = (i % 3) * 3
        flag = False
        sum = 0
        zero_x = -1
        zero_y = -1
        for j in range(3):
            for k in range(3):
                sum += sdoque[x + j][y + k]
                if sdoque[x + j][y + k] == 0:
                    if zero_x != -1:
                        flag = True
                        break
                    zero_x = x + j
                    zero_y = y + k
        if flag:
            continue
        zero_cnt = 45 - sum
        if zero_cnt == 0:
            square.remove(i)
        elif 1 <= zero_cnt <= 9:
            sdoque[zero_x][zero_y] = zero_cnt

for line in sdoque:
    for num in line:
        print(num, end=" ")
    print()
