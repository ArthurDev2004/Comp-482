
public class NodePair {
    private Node destinationNode; 
    private int weight; // weight of the edge connecting the destination node to the source node

    public NodePair()
    {
        this.destinationNode = null; 
        this.weight = 0; 
    }

    public NodePair(Node dest, int weight)
    {
        this.destinationNode = dest; 
        this.weight = weight; 
    }


    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public void setDestinationNode(Node destination)
    {
        this.destinationNode = destination; 
    }

    public int getWeight()
    {
        return this.weight; 
    }

    public Node getDestinationNode()
    {
        return this.destinationNode; 
    }

}
