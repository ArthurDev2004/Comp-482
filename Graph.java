import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;  

public class Graph {
    public HashMap<String, HashMap<String, Pair>> graph; // represents the graph in the adjacency list representation

    public Graph()
    {
        graph = new HashMap<>(); 
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


    // 
    public void populateFromFile()
    {
        // path to file 
        String filePath = "Comp-482/graph.txt"; 

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


                    // add the edge with the proper double weights 
                    Pair newEdge = new Pair(Double.parseDouble(subPartitions[1]), Double.parseDouble(miles)); 

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

    // will be used to fill in the graph
    public void populate()
    {
        boolean quit = false; 
        int userInput = 0; 
        Scanner inputScanner = new Scanner(System.in); 
        String newNodeName = null; // will be the name of the new node being inserted
        String destinationNodeName = null; // will be the name of the destination node for the edge
        double minutes = 0.0; // will be the weight of the edge 
        double miles = 0.0; 

        while (quit == false)
        {
            Menu.menu(); // gives the menu for the input 
            userInput = inputScanner.nextInt(); 

            // determine what to do 

            switch(userInput)
            {
                case 1:
                    Menu.insertNodeMenu();
                    inputScanner.nextLine(); // read the newline character 
                    newNodeName = inputScanner.nextLine(); 

                    this.insertNode(newNodeName); // inserts into the adjacency list
                    break;
                case 2:
                    System.out.print("Enter the source node: "); 
                    inputScanner.nextLine();
                    newNodeName = inputScanner.nextLine(); 
                    System.out.print("Enter the destination node: ");
                    destinationNodeName = inputScanner.nextLine(); 
                    System.out.print("Enter the miles of the edge from " + newNodeName + " -> " + destinationNodeName + ": "); 
                    miles = inputScanner.nextDouble(); 
                    System.out.print("Enter the minutes of the edge from " + newNodeName + " -> " + destinationNodeName + ": "); 
                    minutes = inputScanner.nextDouble();
                    
                    // it will insert the edge 
                    // get the adjacency list of the node which it should be 
                    // then add the key value pair to that adjacency list (hashmap)
                    

                    
                    this.insertEdge(newNodeName, destinationNodeName, miles, minutes); 
        

                    break;
                case 3: // when user wants to 
                    quit = true;
                    break;
            }


        }
     
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

    }

    // will perform djikstra algorithm on this current graph, will output the shortest path and 
    public void djikstra()
    {
        String sourceNodeName = null;
        HashMap<String, DjikstraAlgoTable> djikstraAlgoTable = new HashMap<>();
        
    }


}
