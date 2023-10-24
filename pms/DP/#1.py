number = 12
N = 5
answer = 0
list = [0, set([N])]
i = 2
while i < 9:
    allcase = set()
    NNN = int(str(N) * i)
    allcase.add(NNN)

    for j in range(1, i):
        for x in list[j]:
            for y in list[i - j]:
                allcase.add(x + y)
                allcase.add(x - y)
                allcase.add(x * y)
                if y != 0:
                    allcase.add(x // y)
    if number in allcase:
        answer = i
        break
    list.append(allcase)
    i += 1
print(answer)
