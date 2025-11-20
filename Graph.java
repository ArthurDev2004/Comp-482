import java.util.HashMap;
import java.util.Scanner; 

public class Graph {
    public HashMap<String, HashMap<String, Double>> graph; // represents the graph in the adjacency list representation


    // inserts the node into the graph
    private void insertNode(String newNode)
    {
        this.graph.put(newNode, new HashMap<String, Double>()); // adds the newNode as the key and these 
    }

    private void insertEdge(String currentNode, String adjacentNode, double weight)
    {
        HashMap<String, Double> currentNodeAdjacencyList = this.graph.get(currentNode); // returns the reference to the adjcancey list (hashmap) of the current node

        // insert the adjacent node 
        currentNodeAdjacencyList.put(adjacentNode, weight); 

    }


    // will be used to fill in the graph
    public void populate()
    {
        boolean quit = false; 
        int userInput = 0; 
        Scanner inputScanner = new Scanner(System.in); 
        String newNodeName = null; // will be the name of the new node being inserted 
        Node newNode = null; 

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
                    System.out.println("Enter the source node: "); 
                    System.out.println("Enter the destination node: ");
                    
                    // it will insert the edge 

                    break;
                case 3:
                    quit = true;
                    break;
            }


        }
     



    }

}
