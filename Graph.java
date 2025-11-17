
import java.util.ArrayList; 
import java.util.Scanner; 
// will represent the graph itself 
public class Graph {
    private String name; // the name of the graph, just for semantic purposes 

    private ArrayList<PairList<Node, ArrayList<NodePair>>> adjacenyList; // represents the adhjacency list, which is the graph

    public Graph(String name)
    {
        this.name = name; 
        this.adjacenyList = new ArrayList<>(); // the compilier should properly infer the type based on how it was referred to in the class defintion
    }

    public void setName(String name)
    {
        this.name = name; 
    }

    public String getName()
    {
        return this.name; 
    }

    private void graphPopulationInterface()
    {
        Scanner inputScanner = new Scanner(System.in); // will be used for user input 
        int userInput = 0; 
        String nodeName = null; 
        boolean quit = false; 


        // keep going until the user does not want to inpt data any longer
        while (quit == false)
        {
            System.out.println("1) New Node\n2) Add Edge\n3) Quit"); // menu for prompting the user on what to do 
            userInput = inputScanner.nextInt(); 

            switch (userInput)
            {
                case 1: // create the new node, and add to the adjacency list
                    System.out.print("Enter the name of the node: ");
                    inputScanner.nextLine(); // read the newline character from the input before
                    nodeName = inputScanner.nextLine(); 

                    // construct the node 
                    Node newNode = new Node(nodeName); 
                    // create the pair list, so it can be added to the adjacency list
                    PairList<Node, ArrayList<NodePair>> newPairList = new PairList<>(newNode, new ArrayList<>()); // creates the new node's pair list

                    this.adjacenyList.add(newPairList); // adds the new pair list which has the newly created node, and a list for its adjcent nodes

                    break;
                case 2: // used to add edge(directed) (between two existing nodes) with a weight
                    Node fromNode = null; 
                    Node toNode = null; 
                    int weight = 0; 
                    String fromNodeName = null; 
                    String toNodeName = null; 
                    ArrayList<NodePair> nodeAdjacents = null; 
                    int i = 0; 
                    int fromNodeIndex = 0; 

                    System.out.print("Enter the node where the edge will be from: "); 
                    inputScanner.nextLine(); 
                    fromNodeName = inputScanner.nextLine(); // gets the name of the soruce node 

                    System.out.print("Enter the node where the edge will go to: ");
                    toNodeName = inputScanner.nextLine(); // gets the name of the destination node

                    System.out.print("Enter the weight of the edge from " + fromNodeName + " -> " + toNodeName + ": "); 
                    weight = inputScanner.nextInt(); // gets the weight of the edge 

                    // get the toNode and the fromNode, which will allow for the "edge" to be created
                    for (PairList<Node, ArrayList<NodePair>> pair : this.adjacenyList)
                    {
                        // find the from node in the list of nodes in the graph (should get the index of this to have better access for the next one)
                        if (pair.getNode().getName().equals(fromNodeName))
                        {
                            fromNode = pair.getNode(); 
                            fromNodeIndex = i; 
                        }

                        if (pair.getNode().getName().equals(toNodeName))
                        {
                            toNode = pair.getNode(); // this is the node which the edge is going to
                        }

                        // if both are found, no need to keep going through the list of nodes in the graph
                        if (fromNode != null && toNode != null)
                            break; 

                        i++; 
                    }

                    // create the nodePair 
                    NodePair newEdge = new NodePair(toNode, weight); 

                    // add that new node pair to the adjacency list for the from node 

                    // access the proper pair in the arraylist
                    this.adjacenyList.get(fromNodeIndex).getList().add(newEdge); // adds that adjacent node, and the weight (NodePair) to the adjaceny list of the proper node                 



                    break;
                case 3:
                    quit = true; 
                    break;
            }
        }



        inputScanner.close();
    }

    // prints out the graph in the adjacency list format
    public void print()
    {
        System.out.println("Name\tAdjecent Nodes & Weight");

        for (PairList<Node, ArrayList<NodePair>> pair : this.adjacenyList)
        { 

            System.out.print(pair.getNode().getName()); // prints the name of the node 

            // prints its adjacency list
            for (NodePair edge : pair.getList())
            {
                System.out.print(" (" + edge.getDestinationNode().getName() + ", " +  edge.getWeight() + ")"); 
            }

            System.out.println(); 
        }
    }
   
    // populate the graph with information 
    public void populate()
    {
        this.graphPopulationInterface();
    }

}
