import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;  

public class Graph {
    public HashMap<String, HashMap<String, Pair>> graph; // represents the graph in the adjacency list representation
    double maxMinutes; 
    double minMinutes;
    double maxMiles;
    double minMiles; 

    public Graph()
    {
        graph = new HashMap<>();
        this.maxMiles = 0.0;
        this.maxMinutes = 0.0;
        this.minMiles = 0.0;
        this.minMinutes = 0.0; 
    }


    // inserts the node into the graph
    private void insertNode(String newNode)
    {
        this.graph.put(newNode, new HashMap<>()); // adds the newNode as the key and these 
    }

    // inserts an edge into the graph
    private void insertEdge(String currentNode, String adjacentNode, double miles, double minutes)
    {
        HashMap<String, Pair> currentNodeAdjacencyList = this.graph.get(currentNode); // returns the reference to the adjcancey list (hashmap) of the current node

        Pair newEdgePair = new Pair(minutes, miles); 

        // insert the adjacent node into the adjacency list
        currentNodeAdjacencyList.put(adjacentNode, newEdgePair); // adjacentNode string is the key, and weight is the value

    }

    private void insertEdge()
    {

    }

    // inserts the node from the file 
    private void insertNodeFromFile(String newNode)
    {
        this.graph.put(newNode, new HashMap<>()); 
    }


    // will read in data for the construction of the graph from a file
    private void populateFromFile()
    {
        // path to file 
        String filePath = "Comp-482/graph.txt"; 
        boolean firstPass = true; // will be used to get the first values in the first edge as the min and max of the respective values, then go from there

        // get the data from the scanner 
        try (Scanner fileInput = new Scanner(new File(filePath)))
        {
            while(fileInput.hasNextLine())
            {
                String currentLine = fileInput.nextLine(); 
                String[] partitions = currentLine.split(" "); 

                // add the current node to the graph (which is the first partition)
                this.graph.put(String.valueOf(partitions[0]), new HashMap<>()); 

                for (int i = 1; i < partitions.length; i++)
                {
                    // parse each partition by comma
                    String[] subPartitions = partitions[i].split(","); 

                    // add the adjacent node to the main graph, if it does not already exist 
                    if (this.graph.containsKey(String.valueOf(subPartitions[0].charAt(1))) == false)
                    {
                        this.graph.put(String.valueOf(subPartitions[0].charAt(1)), new HashMap<>()); 
                    }

                    String miles = new String(); 
                    // put second string to get double
                    for (int y = 0; y < (subPartitions[2].length()-1); y++)
                    {
                        miles += subPartitions[2].charAt(y); 
                    }

                    // intializes the min and max variables, so they can be used throughout the construction of the graph later
                    if (firstPass == true)
                    {
                        this.maxMiles = Double.parseDouble(miles);
                        this.minMiles = Double.parseDouble(miles);
                        this.minMinutes = Double.parseDouble(subPartitions[1]);
                        this.maxMinutes = Double.parseDouble(subPartitions[1]);
                        firstPass = false; 
                    }

                    // add the edge with the proper double weights 
                    Pair newEdge = new Pair(Double.parseDouble(subPartitions[1]), Double.parseDouble(miles)); 

                    // add the neccesary checks to see the max and min values 
                    if (newEdge.getMinutes() > this.maxMinutes)
                        this.maxMinutes = newEdge.getMinutes(); 

                    if (newEdge.getMiles() > this.maxMiles)
                        this.maxMiles = newEdge.getMiles();

                    if (newEdge.getMinutes() < this.minMinutes)
                        this.minMinutes = newEdge.getMinutes(); 

                    if (newEdge.getMiles() < this.minMiles)
                        this.minMiles = newEdge.getMiles(); 

                    HashMap<String,Pair> adjacencyList = this.graph.get(String.valueOf(partitions[0])); 

                    // add the adjacent node and its two values to the adjacency list 
                    adjacencyList.put(String.valueOf(subPartitions[0].charAt(1)), newEdge); 
                }

            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not Found"); 
        }

    }

    // will be used to construct/populate the graph
    public void populate()
    {
        this.populateFromFile();
    }

    // will be used to normalize the values in the graph (using min max normalization)
    private double normalize(double min, double max, double currentValue)
    {
        return (currentValue - min) / (max - min); 
    }

    // calculates the importance, and computes a single value from it 
    private double importanceFunction(double minutesImportance, double minutes, double milesImportance, double miles)
    {
        return (minutesImportance * minutes) + (milesImportance * miles); 
    }

    // print the graph in adjacency list format 
    public void print()
    {
        Pair edgePair; 

        // goes through each of the nodes
        for (String currentNode : this.graph.keySet())
        {
            System.out.print(currentNode + " ");
            // go through the adjacency list of the current node, and prints out the adjacency list  
            for (String adjacentNode : this.graph.get(currentNode).keySet())
            {
                edgePair = this.graph.get(currentNode).get(adjacentNode); 
                System.out.print("(" + adjacentNode + ", " + edgePair.getMinutes() + ", " + edgePair.getMiles() + ") "); 
            }
            System.out.println();
        }

        System.out.println("Max Miles: " + this.maxMiles);
        System.out.println("Min Miles: " + this.minMiles);
        System.out.println("Max Minutes: " + this.maxMinutes);
        System.out.println("Min Minutes: " + this.minMinutes);



    }

    // will intialize the table which is used for djikstra algorihtm (contains visited, value, parent node, and the current node)
    private void intializeDjikstraTable(HashMap<String, DjikstraAlgoTable> djikstraAlgoTable, PriorityQueue<DjikstraAlgoTable> djikstraPQ, String sourceNodeName)
    {
        for (String currentNode : this.graph.keySet())
        {
            // if we are currently in the graph, which is where the source node is, have this have its value be intialized to 0
            if (currentNode.equals(sourceNodeName))
            {
                djikstraAlgoTable.put(currentNode, new DjikstraAlgoTable(currentNode, 0.0)); // intialize this source node with value 0 for djikstra algorithm
            }
            else
            {
                djikstraAlgoTable.put(currentNode, new DjikstraAlgoTable(currentNode)); // intialize this source node with value 0 for djikstra algorithm
            }
            djikstraPQ.add(djikstraAlgoTable.get(currentNode)); 
        }
    }

    // will print the path to each of the nodes from the source node
    private void printPaths(String sourceNodeName, HashMap<String, DjikstraAlgoTable> djikstraAlgoTable)
    {
        String currentNodeName = null;
        Stack<String> path = new Stack<>(); 
        double pathValue = 0.0;

        for (String node : djikstraAlgoTable.keySet())
        {
            currentNodeName = node; 
            pathValue = djikstraAlgoTable.get(node).getValue(); 


            while (djikstraAlgoTable.get(currentNodeName).getParentNode() != null)
            {
                path.push(currentNodeName); 

                currentNodeName = djikstraAlgoTable.get(currentNodeName).getParentNode(); 
            }

            // adds the node which has a predecessor node of null to stack to complete the path
            path.push(currentNodeName); 

            System.out.print("Path Value: " + pathValue + " Path: "); 

            while(path.isEmpty() == false)
            {
                if (path.size() == 1)
                {
                    System.out.print(path.pop());
                }
                else
                {
                    System.out.print(path.pop() + " -> "); 
                }
            }

            System.out.println();

        }
        


    }

    

    // will perform djikstra algorithm on this current graph, will output the shortest path and 
    public void djikstra(String sourceNodeName, String destinationNodeName,double minutesImportance, double milesImportance)
    {
        DjikstraAlgoTable currentNode = null; 
        double miles = 0.0;
        double minutes = 0.0;
        double normalizedMiles = 0.0;
        double normalizedMinutes = 0.0;
        double finalEdgeValue = 0.0;
        HashMap<String, DjikstraAlgoTable> djikstraAlgoTable = new HashMap<>(); // represents the table which will be used for djikstra's algorithm 
        PriorityQueue<DjikstraAlgoTable> djikstraPQ = new PriorityQueue<>(); 


        // intialize the djikstra algorithm table which will keep track of all of the values, visited, and parents 
        this.intializeDjikstraTable(djikstraAlgoTable, djikstraPQ, sourceNodeName);

        // now can start the process of djikstra's algorithm on the graph
        while(djikstraPQ.isEmpty() == false)
        {
            // take out the current node with the lowest value (should initially be the source node)
            currentNode = djikstraPQ.poll(); 
            currentNode.setVisited(true);

            // go through the adjacent nodes for this current node 
            for (String adjacentNode : this.graph.get(currentNode.getCurrentNode()).keySet())
            {
                // checks if this adjacent node has been visited 
                if (djikstraAlgoTable.get(adjacentNode).getVisited() == true)
                {

                }
                else // perform the edge relaxation (normalization, importance function, single value)
                {
                    // first normalize the weights on the edge (minutes and miles)
                    minutes = this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).getMinutes(); 
                    miles = this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).getMiles();
                    
                    // sets the normalized values 
                    this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).setNormalizedMinutes(this.normalize(this.minMinutes, this.maxMinutes, minutes));
                    this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).setNormalizedMiles(this.normalize(this.minMiles, this.maxMiles, miles));                     
                    
                    normalizedMinutes = this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).getNormalizedMinutes();
                    normalizedMiles = this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).getNormalizedMiles();

                    // apply the importance function on the normalized values 
                    this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).setSingalFinalValue(this.importanceFunction(minutesImportance,normalizedMinutes , milesImportance, normalizedMiles));
                    
                    // sets the final signal value after all transformations
                    finalEdgeValue = this.graph.get(currentNode.getCurrentNode()).get(adjacentNode).getSingalFinalValue(); 


                    // now can start regular comparison like in djikstras

                    // if the value of the currentnode and the edge connecting it to its adjacent node, is less than the current value of the adjacent node (which indicates the weight to get there), then it should be updated with the new weight
                    if (currentNode.getValue() + finalEdgeValue < djikstraAlgoTable.get(adjacentNode).getValue())
                    {
                        // sets the parent node, to the node which is the current node
                        djikstraAlgoTable.get(adjacentNode).setValue(currentNode.getValue() + finalEdgeValue);
                        djikstraAlgoTable.get(adjacentNode).setParentNode(currentNode.getCurrentNode());

                        // removes node from Priority queue and puts it back, so its place in the queue is updated with respect to the new value 
                        djikstraPQ.remove(djikstraAlgoTable.get(adjacentNode)); 
                        djikstraPQ.add(djikstraAlgoTable.get(adjacentNode)); 
                    }


                }
            }




        }

        this.printDestinationPath(destinationNodeName, djikstraAlgoTable);

        //this.printPaths(sourceNodeName, djikstraAlgoTable);

    }

    // prints the paths
    private void printDestinationPath(String destinationNodeName, HashMap<String, DjikstraAlgoTable> djikstraAlgoTable)
    {
        String currentNodeName = destinationNodeName; 
        Stack<String> path = new Stack<>();
        double pathValue = 0.0;
        String fromNode = null;
        String toNode = null;

        
        // accumulate the total minutes that the path will take
        double totalMinutes = 0.0;
        double totalKm = 0.0; 

        // get the destinationNode from the table and use it 

        while(djikstraAlgoTable.get(currentNodeName).getParentNode() != null)
        {
            path.push(currentNodeName); 

            // get the distance and the minutes for the path from the destination node to source node working backwordss
            
            // get the edge where current node is the to node of the edge, and the parent node is the from node of the edge
            fromNode = djikstraAlgoTable.get(currentNodeName).getParentNode();
            toNode = currentNodeName; 
            Pair weights = this.graph.get(fromNode).get(toNode); // these are the weights for the graph 

            totalMinutes += weights.getMinutes(); // add the minutes to the accum
            totalKm += weights.getMiles(); // add miles 

            currentNodeName = djikstraAlgoTable.get(currentNodeName).getParentNode(); 
        }

        path.push(currentNodeName); 

        System.out.println("Path Distance (km): " + totalKm); 
        System.out.println("Path Time (min): " + totalMinutes); 

        while(path.isEmpty() == false)
        {
             if (path.size() == 1) // for last element in the stack
                {
                    System.out.print(path.pop());
                }
                else {
                    System.out.print(path.pop() + " -> ");
                }
        }
        System.out.println(); 

    }


}