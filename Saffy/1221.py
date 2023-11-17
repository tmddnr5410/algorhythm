input()

for t in range(1, 11):
    input()
    nums = input().split()
    zero_to_nine = [
        "ZRO",
        "ONE",
        "TWO",
        "THR",
        "FOR",
        "FIV",
        "SIX",
        "SVN",
        "EGT",
        "NIN",
    ]
    sort_dict = {key: 0 for key in zero_to_nine}

    for num in nums:
        sort_dict[num] += 1

    print("#%d" % (t))
    for key, value in sort_dict.items():
        print(f"{key} " * value, end="")
    print()
