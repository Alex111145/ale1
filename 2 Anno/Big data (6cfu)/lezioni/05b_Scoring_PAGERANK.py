import networkx as nx
import wikipediaapi
import matplotlib.pyplot as plt

# Creiamo un wikipedia api wrapper
wiki = wikipediaapi.Wikipedia(
        language='it',
        extract_format=wikipediaapi.ExtractFormat.WIKI
)

# Creiamo un grafo vuoto
G = nx.DiGraph()

# Recuperiamo la pagina di Wikipedia per "Leonardo Da Vinci"
page = wiki.page("Leonardo Da Vinci")

# Aggiungiamo i nodi
G.add_nodes_from([page.title])

# Recuperiamo i link interni alla pagina
for link in page.links.items():
    G.add_nodes_from([link[0]])
    G.add_edge(page.title, link[0])

# Calcoliamo il PageRank
pr = nx.pagerank(G)

# Stampiamo il PageRank per ciascun nodo
for node, score in pr.items():
    print(f"Node {node} ha un PageRank di {score}")


