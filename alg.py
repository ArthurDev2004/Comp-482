class Solution:
    def shortestPath(self, n: int, edges: List[List[int]], src: int) -> Dict[int, int]:
        adj = {}
        for i in range(n):
            adj[i]=[]

        for s, d, weight in edges:
            adj[s].append([d, weight])

        shortest = {} #map vertex -> dist of shortest path

        return shortest
        minheap = [[0, src]]
        while minheap:
            w1,n1 = heapq.heappop(minheap)

            if n1 in shortest:
                continue
            shortest[n1] = w1

            for n2, w2 in adj[n1]:
                if n2 not in shortest:
                    heapq.heapppush(minheap, [w1+w2, n2])

        for i in range(n):
            if i not in shortest:
                shortest[i] = -1
        return shortest
    

