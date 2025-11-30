import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Graph {

    public HashMap<String, HashMap<String, Pair>> graph;

    double maxMinutes;
    double minMinutes;
    double maxMiles;
    double minMiles;

    public Graph() {
        graph = new HashMap<>();
        this.maxMiles = 0.0;
        this.maxMinutes = 0.0;
        this.minMiles = 0.0;
        this.minMinutes = 0.0;
    }

    private void insertNode(String newNode) {
        this.graph.put(newNode, new HashMap<>());
    }

    private void populateFromFile() {
        String filePath = "graph.txt"; 
        boolean firstPass = true;

        try (Scanner fileInput = new Scanner(new File(filePath))) {
            while (fileInput.hasNextLine()) {
                String currentLine = fileInput.nextLine();
                if(currentLine.trim().isEmpty()) continue; 

                String[] partitions = currentLine.split(" ");

                this.graph.putIfAbsent(String.valueOf(partitions[0]), new HashMap<>());

                for (int i = 1; i < partitions.length; i++) {
                    String[] subPartitions = partitions[i].split(",");

                    String neighborName = String.valueOf(subPartitions[0].charAt(1));
                    
                    // make sure the neighbor exists in the map
                    this.graph.putIfAbsent(neighborName, new HashMap<>());

                    String milesStr = "";
                    for (int y = 0; y < (subPartitions[2].length() - 1); y++) {
                        milesStr += subPartitions[2].charAt(y);
                    }

                    double minutesVal = Double.parseDouble(subPartitions[1]);
                    double milesVal = Double.parseDouble(milesStr);

                    // initialize min and max on the first run
                    if (firstPass) {
                        this.maxMiles = milesVal;
                        this.minMiles = milesVal;
                        this.minMinutes = minutesVal;
                        this.maxMinutes = minutesVal;
                        firstPass = false;
                    }

                    Pair newEdge = new Pair(minutesVal, milesVal);

                    // update min and max values for normalization later
                    if (newEdge.getMinutes() > this.maxMinutes) this.maxMinutes = newEdge.getMinutes();
                    if (newEdge.getMiles() > this.maxMiles) this.maxMiles = newEdge.getMiles();
                    if (newEdge.getMinutes() < this.minMinutes) this.minMinutes = newEdge.getMinutes();
                    if (newEdge.getMiles() < this.minMiles) this.minMiles = newEdge.getMiles();

                    HashMap<String, Pair> adjacencyList = this.graph.get(String.valueOf(partitions[0]));
                    adjacencyList.put(neighborName, newEdge);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found: " + filePath);
        }
    }

    public void populate() {
        this.populateFromFile();
    }

    // basic min-max normalization
    private double normalize(double min, double max, double currentValue) {
        // avoid divide by zero if max and min are the same
        if (max - min == 0) return 0.0; 
        return (currentValue - min) / (max - min);
    }

    public void normalizeAllEdges() {
        for (HashMap<String, Pair> adj : graph.values()) {
            for (Pair p : adj.values()) {
                double normMinutes = normalize(minMinutes, maxMinutes, p.getMinutes());
                double normMiles = normalize(minMiles, maxMiles, p.getMiles());

                p.setNormalizedMinutes(normMinutes);
                p.setNormalizedMiles(normMiles);
            }
        }
    }

    private double importanceFunction(double minutesWeight, double minutesNorm,
                                      double milesWeight, double milesNorm) {
        return (minutesWeight * minutesNorm) + (milesWeight * milesNorm);
    }

    public void print() {
        Pair edgePair;
        System.out.println("--- Graph Data ---");
        for (String currentNode : this.graph.keySet()) {
            System.out.print(currentNode + " -> ");
            for (String adjacentNode : this.graph.get(currentNode).keySet()) {
                edgePair = this.graph.get(currentNode).get(adjacentNode);
                System.out.print(adjacentNode + "(min:" + edgePair.getMinutes() + ", mi:" + edgePair.getMiles() + ") ");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println("Max Miles: " + this.maxMiles + " | Min Miles: " + this.minMiles);
        System.out.println("Max Mins:  " + this.maxMinutes + " | Min Mins:  " + this.minMinutes);
    }

    private void intializeDjikstraTable(HashMap<String, DjikstraAlgoTable> djikstraAlgoTable,
                                        PriorityQueue<DjikstraAlgoTable> djikstraPQ,
                                        String sourceNodeName) {
        for (String currentNode : this.graph.keySet()) {
            if (currentNode.equals(sourceNodeName)) {
                djikstraAlgoTable.put(currentNode, new DjikstraAlgoTable(currentNode, 0.0));
            } else {
                djikstraAlgoTable.put(currentNode, new DjikstraAlgoTable(currentNode));
            }
            djikstraPQ.add(djikstraAlgoTable.get(currentNode));
        }
    }

    public void djikstra(String sourceNodeName, String destinationNodeName,
                         double minutesImportance, double milesImportance) {

        if (!graph.containsKey(sourceNodeName) || !graph.containsKey(destinationNodeName)) {
            System.out.println("Source or Destination node does not exist.");
            return;
        }

        DjikstraAlgoTable currentNode;
        HashMap<String, DjikstraAlgoTable> djikstraAlgoTable = new HashMap<>();
        PriorityQueue<DjikstraAlgoTable> djikstraPQ = new PriorityQueue<>();

        this.intializeDjikstraTable(djikstraAlgoTable, djikstraPQ, sourceNodeName);

        while (!djikstraPQ.isEmpty()) {
            currentNode = djikstraPQ.poll();

            if (currentNode.getVisited()) {
                continue;
            }

            currentNode.setVisited(true);

            // if we can't reach the node, skip it
            if (this.graph.get(currentNode.getCurrentNode()) == null || currentNode.getValue() == Double.POSITIVE_INFINITY) {
                continue;
            }

            for (String adjacentNode : this.graph.get(currentNode.getCurrentNode()).keySet()) {

                DjikstraAlgoTable neighborTable = djikstraAlgoTable.get(adjacentNode);

                if (neighborTable.getVisited()) {
                    continue;
                }

                Pair pair = this.graph.get(currentNode.getCurrentNode()).get(adjacentNode);
                double normalizedMinutes = pair.getNormalizedMinutes();
                double normalizedMiles = pair.getNormalizedMiles();

                // calculate the cost based on user importance
                double finalEdgeValue = this.importanceFunction(
                        minutesImportance, normalizedMinutes,
                        milesImportance, normalizedMiles);

                double newValue = currentNode.getValue() + finalEdgeValue;

                if (newValue < neighborTable.getValue()) {
                    // remove it first so the queue can re-sort when we add it back with the new value
                    djikstraPQ.remove(neighborTable);
                    
                    neighborTable.setValue(newValue);
                    neighborTable.setParentNode(currentNode.getCurrentNode());

                    djikstraPQ.add(neighborTable);
                }
            }
        }

        this.printDestinationPath(destinationNodeName, djikstraAlgoTable);
    }

    private void printDestinationPath(String destinationNodeName,
                                      HashMap<String, DjikstraAlgoTable> djikstraAlgoTable) {

        if (djikstraAlgoTable.get(destinationNodeName).getValue() == Double.POSITIVE_INFINITY) {
            System.out.println("No path found to " + destinationNodeName);
            return;
        }

        String currentNodeName = destinationNodeName;
        Stack<String> path = new Stack<>();
        double pathValue = djikstraAlgoTable.get(destinationNodeName).getValue();

        while (currentNodeName != null && djikstraAlgoTable.get(currentNodeName).getParentNode() != null) {
            path.push(currentNodeName);
            currentNodeName = djikstraAlgoTable.get(currentNodeName).getParentNode();
        }

        if (currentNodeName != null) path.push(currentNodeName);

        System.out.print("Weighted Score: " + String.format("%.4f", pathValue) + " | Path: ");

        while (!path.isEmpty()) {
            if (path.size() == 1) {
                System.out.print(path.pop());
            } else {
                System.out.print(path.pop() + " -> ");
            }
        }
        System.out.println();
    }
}