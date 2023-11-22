


def solution(s):
    answer=""
    numbers = ["zero","one","two","three","four","five","six","seven","eight","nine"]
    string_number=""
    for ch in s:
        if ch.isdigit():
            answer+=ch
        else :
            string_number+=ch
            if string_number in numbers:
                answer+=str(numbers.index(string_number))
                string_number=""
    return int(answer)

print(solution("one4seveneight"))