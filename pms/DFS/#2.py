numbers = [3, 30, 34, 5, 9]
maxn = -1
maxlen = len(numbers)
numbers = list(map(str, numbers))
numbers.sort(reverse=True)
print(str(int("".join(numbers))))
