// keeps track of the data for the algorithm table
public class DjikstraAlgoTable implements Comparable<DjikstraAlgoTable>
{
    private double value; 
    private boolean visited;
    private String parentNode; 
    private final String currentNode; 

    public DjikstraAlgoTable(String currentNodeName)
    {
        this.value = Double.POSITIVE_INFINITY; 
        this.visited = false; 
        this.parentNode = null;
        this.currentNode = currentNodeName;
    }

    public DjikstraAlgoTable(String currentNode, double value)
    {
        this.value = value; 
        this.visited = false; 
        this.parentNode = null;
        this.currentNode = currentNode;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public boolean getVisited(){
        return this.visited;
    }

    public void setParentNode(String parentNode)
    {
        this.parentNode = parentNode;
    }

    public String getParentNode()
    {
        return this.parentNode;
    }

    public String getCurrentNode()
    {
        return this.currentNode;
    }

    @Override
    public String toString()
    {
        return this.currentNode + ": " + this.value;
    }

    // needed for the priority queue to sort correctly
    @Override
    public int compareTo(DjikstraAlgoTable other)
    {
        return Double.compare(this.value, other.getValue());
    }
}