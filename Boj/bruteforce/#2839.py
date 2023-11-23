sugar=int(input())
max_five = sugar//5
answer=-1
for i in range(max_five,-1,-1):
    n=sugar - (5*i)
    max_three = n%3
    if max_three == 0:
        answer=i+(n//3)
        break
        
print(answer)