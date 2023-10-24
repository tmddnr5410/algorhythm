import copy


def solution(array, commands):
    answer = []
    for arr in commands:
        # 한번만 쓰고 버릴 배열 생성
        temp = copy.deepcopy(array)
        # 잘라낼 arr의 길이 만큼 비교연산을 수행해야함
        for i in range(arr[1] - arr[0]):
            # 잘라낼 시작부터 끝까지 순회, 정렬의 시작 위치가 i를 통해 점점 앞으로
            for j in range(arr[0] - 1 + i, arr[1] - 1):
                if temp[j] > temp[j + 1]:
                    x = temp[j]
                    temp[j] = temp[j + 1]
                    temp[j + 1] = x
        answer.append(temp[arr[0] + arr[2] - 2])
    return answer
