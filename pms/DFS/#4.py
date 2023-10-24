from collections import deque


def solution(begin, target, words):
    answer = 0
    queu = deque()
    n = len(words)
    word_len = len(begin)
    visit = [0] * n
    
    
    def cntdiff(src, tar):
        temp = 0
        for i in range(word_len):
            if src[i] != tar[i]:
                temp += 1
        return temp


    queu.append(begin)
    while queu:
        curr = queu.popleft()
        if curr == target:
            answer = cntdiff(begin,target)
            break
        for i in range(n):
            if visit[i] == 0 and cntdiff(curr, words[i]) == 1:
                queu.append(words[i])
                visit[i] = 1
    return answer


ans = solution("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"])
print(ans)
