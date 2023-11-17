for tc in range(1, 11):
    input()

    nums = list(map(int, input().split()))

    m = min(nums)
    round = m - (m % 15)

    for i in range(8):
        nums[i] = nums[i] - round + 15

    key = 0
    while True:
        key = key % 5 + 1

        temp = nums.pop(0) - key

        if temp <= 0:
            nums.append(0)
            break
        nums.append(temp)

    print("#%d" % (tc), end=" ")
    for num in nums:
        print(num, end=" ")
