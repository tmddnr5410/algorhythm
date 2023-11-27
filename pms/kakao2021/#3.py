class Node:
    def __init__(self):
        self.prev = -1
        self.next = -1
        self.is_delete = False


n = 8
k = 2
cmd = ["D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"]

table = [Node() for _ in range(n)]
table_length = n

for i in range(n - 1):
    table[i].next = i + 1
    table[i + 1].prev = i


now = k
stack = []
for do in cmd:
    for i in range(n):
        if table[i].is_delete:
            print("삭제됨", end=" ")
        else:
            print(f"{table[i].prev} {i} {table[i].next}", end=" ")
        print("||", end="")
    print("\n\n")
    if do[0] == "U":
        for i in range(int(do[2])):
            if table[now].next == -1:
                break
            now = table[now].next
    elif do[0] == "D":
        for i in range(int(do[2])):
            if table[now].prev == -1:
                break
            now = table[now].prev
    elif do[0] == "C":
        if table[now].prev != -1:
            table[table[now].prev].next = table[now].next
        if table[now].next != -1:
            table[table[now].next].prev = table[now].prev
        table[now].is_delete = True
        stack.append(now)
        now = table[now].next

    elif do[0] == "Z":
        last_Del = stack.pop()
        table[table[last_Del].prev].next = last_Del
        table[table[last_Del].next].prev = last_Del
        table[last_Del].is_delete = False
for i in range(n):
    if table[i].is_delete:
        print("삭제됨", end=" ")
    else:
        print(f"{table[i].prev} {i} {table[i].next}", end=" ")
    print("|| ", end="")
answer = ""
for node in table:
    if node.is_delete:
        answer += "X"
    else:
        answer += "O"

print(answer)
