import networkx as nx
import matplotlib.pyplot as plt

G = nx.Graph()
G.add_edges_from([
    ('A', 'B', {'distance': 4, 'time': 7}),
    ('A', 'C', {'distance': 2, 'time': 4}),
    ('B', 'C', {'distance': 5, 'time': 6}),
    ('B', 'D', {'distance': 10, 'time': 12}),
    ('C', 'E', {'distance': 3, 'time': 5}),
    ('E', 'D', {'distance': 4, 'time': 6}),
    ('D', 'F', {'distance': 11, 'time': 15}),
])
'''
# Use 'distance' to influence the spring layout
pos = nx.spring_layout(G, seed=42, weight='distance')

nx.draw(G, pos, with_labels=True, node_color='lightblue', node_size=2000, font_weight='bold')

# Show both weights on each edge
edge_labels = {(u, v): f"{d['distance']} km , {d['time']} min" for u, v, d in G.edges(data=True)}
nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_color='darkgreen')

plt.title("Graph (labels: distance , time)")
plt.show()
'''

a = float(input("Enter weight for distance: "))
b = 1-a
# --- Iterate over edges and modify them ---
for u, v, data in G.edges(data=True):
    # Compute combined weight
    combined = a * data['distance'] + b * data['time']

    # Store it as a new attribute
    data['combined'] = combined

# --- Print updated edges ---
print("\nUpdated edges with combined weight:")
for u, v, data in G.edges(data=True):
    print(f"{u} - {v}: distance={data['distance']}, time={data['time']}, combined={data['combined']:.2f}")

pos = nx.spring_layout(G, seed=42, weight='distance')

# Draw nodes
nx.draw(G, pos, with_labels=True, node_color='lightblue', node_size=2000, font_weight='bold')

# Label edges with all three weights
edge_labels = {}
for u, v, d in G.edges(data=True):
    edge_labels[(u, v)] = f"{d['combined']:.1f}"

nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_color='darkgreen')

plt.title("Graph with Distance / Time / Combined Weights")
plt.show()