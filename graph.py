file = open('test_case.txt', 'r')
read = file.readlines()
modified = []
edge = []
weight = []
nodes = []
w1_lists=[]
w2_lists=[]


for line in read:
    v, u, w1, w2 = line.split()
    
    if v not in nodes:
        nodes.append(v)
        if u not in nodes:
            nodes.append(u)


    edge.append([v,u])
    #if v not in nodes:
    #nodes = nodes.append(v)
    

    w1 = int(w1)
    w2 = int(w2)
    w1_lists.append(w1)
    w2_lists.append(w2)

    weight.append([w1,w2])
    
max_w1 = max(w1_lists)
min_w1 = min(w1_lists)
max_w2 = max(w2_lists)
min_w2 = min(w2_lists)
print(edge)
print(weight)
print(nodes)


w1_dist_cost = input(f"From 0-10, how crucial is the distance:")
w1_dist_cost = int(float(w1_dist_cost))

while w1_dist_cost<0 or w1_dist_cost>10:
    print('Input not valid. TRY AGAIN.')
    w1_dist_cost = input(f"From 0-10, how crucial is the distance:")



w1_dist_cost = w1_dist_cost /10
w2_dist_cost = 1- w1_dist_cost

cost_weight = []
for p1,p2 in weight:
    p1_norm = (p1-min_w1)/(max_w1-min_w1)
    p2_norm = (p2-min_w2)/(max_w2-min_w2)
    print('p1_norm: ', p1_norm)
    w = (w1_dist_cost * p1_norm) + (w2_dist_cost * p2_norm)
    w = f"{w:.2f}"
    print(w)
    cost_weight.append(w)

print(cost_weight)   



#unvisited = nodes   


# ready = False
# for idx, w in enumerate(weight):
#     if w<0:
#         print(f"Cannot perform Dijkstra because the following graph has a negative weight of {w} for node pair {edge[idx]}.")
#         ready = False
#     else:
#         ready = True

# if(ready):
#     print("READY TO PERFORM DIJKSTRA'S!")

# source = input(f"Please enter the source node you would like to start at from the following list {nodes}:").upper()
# while source not in nodes:
#     print('Input not valid. TRY AGAIN')
#     source = input(f"Please enter the source node you would like to start at from the following list {nodes}:").upper()

# #Algorithm officially started here!
# unvisited.remove(source)
# print(unvisited)
# visited = [source]
# print(visited)
# distance = 0
# current = source

'''
while len(unvisited) != 0:
    for idx, pair in edge:
        if current in pair:
            print('to be continued') 

'''

