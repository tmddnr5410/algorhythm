for tc in range(1, 11):
    N = int(input())
    pwd = input().split()
    M = int(input())
    code = input().split()

    code_len = len(code)

    i = 0
    for i in range(code_len):
        if code[i] == "I":
            idx = int(code[i + 1])
            l = int(code[i + 2])
            for j in range(l):
                pwd.insert(idx + j, code[i + j + 3])

    print(f"#{tc}", end=" ")
    for ch in pwd[:10]:
        print(ch, end=" ")
