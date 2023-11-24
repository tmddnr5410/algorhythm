from itertools import permutations


exp = "100-200*300-500+20"

operator = ["+", "-", "*"]
all_priority = list(map(list, permutations(operator)))
max_result = 0

for priority in all_priority:
    first = exp.split(priority[0])

    ans = []
    for val in first:
        second = val.split(priority[1])
        thd_exp = []
        for third in second:
            thd_exp.append(str(eval(third)))
        ans.append(str(eval(priority[1].join(thd_exp))))

    result = eval((priority[0].join(ans)))

    max_result = max(max_result, abs(result))
