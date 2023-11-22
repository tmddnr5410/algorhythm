import sys

input = sys.stdin.readline

n=int(input())
m=0
i=665
while True:
    i+=1
    if '666' in str(i):
        m+=1
        if m==n:
            print(i)
            break
    