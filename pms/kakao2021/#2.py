from collections import deque
        
    
def check_map(map):
    for i in range(5):
        for j in range(5):
            if map[i][j] == 'P':
                visit = [[False for _ in range(5)] for _ in range(5)]
                queu = deque([])
                dx=[1,-1,0,0]
                dy=[0,0,1,-1]
                
                queu.append([i,j,0])
                visit[i][j]=True
                
                while queu:
                    x,y,dist = queu.popleft()
                    for d in range(4):
                        nx=x+dx[i]
                        ny=y+dy[i]
                        
                        if 0<=nx<5 and 0<=ny<5 and not visit[nx][ny]:
                            if map[nx][ny] == "P":
                                return 0
                                            
                            if map[nx][ny] == "O":
                                visit[nx][ny] = True
                                if dist <= 1:
                                    queu.append([nx,ny,dist+1])
    return 1


map1 = ["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"]
map2=["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"]
print(check_map(map1))
print(check_map(map2))

