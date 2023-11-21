def DFS(depth):
    global max_num, end
    now = int("".join(nums))
    if now in switched[depth]:
        return
    switched[depth].append(now)

    if depth == switch_max:
        max_num = max(max_num, now)
        if max_num == all_max:
            end = True
        return

    for i in range(l):
        for j in range(i + 1, l):
            nums[i], nums[j] = nums[j], nums[i]

            DFS(depth + 1)
            if end:
                return
            nums[i], nums[j] = nums[j], nums[i]


tc = int(input())
for i in range(tc):
    d = input().split()

    nums = list(d[0])
    l = len(nums)

    switch_max = int(d[1])

    switched = [[] for _ in range(switch_max + 1)]

    end = False
    max_num = int("".join(sorted(nums)))
    all_max = int("".join(sorted(nums, reverse=True)))

    DFS(0)
    print(f"#{i+1} {max_num}")
