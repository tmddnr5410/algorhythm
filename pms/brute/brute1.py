def solution(sizes):
    maxw = 0
    maxh = 0
    for size in sizes:
        if maxw < max(size[0], size[1]):
            maxw = max(size[0], size[1])
        if maxh < min(size[0], size[1]):
            maxh = min(size[0], size[1])
    return maxw * maxh
