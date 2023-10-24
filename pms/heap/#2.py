import heapq


def solution(jobs):
    answer = 0

    # 힙생성
    heap = []

    TIME = 1
    IN = 0

    # 정렬하여 가장 적은 시간이 드는 작업을 먼저 실행하겠다 가정
    sorted(jobs)
    heapq.heappush(heap, 0)

    idx = 0
    allTime = 0
    processTime = 0
    while len(heap) != 0:
        # 실행할수 있는 가장 적은 시간의 작업을 실행
        processTime = heapq.heappop(heap)
        print("현재 실행시간", processTime)
        # 해당시간동안 들어오는 작업만 모두 힙에 저장
        for i in range(idx, len(jobs)):
            # 현재 실행시간안에 요청되지 않는 작업은 아직 실행할수 없음
            if jobs[i][IN] > allTime + processTime:
                idx = i
                break
            else:
                heapq.heappush(heap, jobs[i][TIME])

                print(jobs[i][IN], "에 들어온 작업 힙에 푸시", jobs[i][TIME])
                idx = i

        # 작업 실행을 마치면 총 실행시간을 갱싱
        allTime += processTime

    return allTime // len(jobs)


j = [[0, 3], [1, 9], [2, 6]]

print(solution(j))
