def solution(n, lost, reserve):
    spare_stolen = set(lost) & set(reserve)

    lost = sorted(list(filter(lambda x: x not in spare_stolen, lost)))
    reserve = sorted(list(filter(lambda x: x not in spare_stolen, reserve)))

    for i in lost:
        if (i - 1) in reserve:
            reserve.remove(i - 1)

        elif (i + 1) in reserve:
            reserve.remove(i + 1)

        else:
            n -= 1

    return n
