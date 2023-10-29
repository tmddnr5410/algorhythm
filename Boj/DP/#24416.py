n = int(input())

pibo = [0] * (n + 1)
pibo[1] = 1
pibo[2] = 1
for i in range(3, n + 1):
    pibo[i] = pibo[i - 1] + pibo[i - 2]

print(pibo[n], n - 2)
