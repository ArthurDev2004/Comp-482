public class DjikstraAlgoTable implements Comparable<DjikstraAlgoTable>
{
    private double normalizedValue; 
    private boolean visited;
    private String parentNode; 
    private final String currentNode; 

    public DjikstraAlgoTable(String currentNodeName)
    {
        this.normalizedValue = Double.POSITIVE_INFINITY; 
        this.visited = false; 
        this.parentNode = null;
        this.currentNode = null;
    }

    public DjikstraAlgoTable(String currentNode, double value)
    {
        this.normalizedValue = value; 
        this.visited = false; 
        this.parentNode = null;
        this.currentNode = null;
    }

        public void setValue(double value)
    {
        this.normalizedValue = value;
    }

    public double getValue(){
        return this.normalizedValue;
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
        return this.normalizedValue + " " + this.visited + " " + this.parentNode;
    }

    // will be used so this can be put in the priority queue
    @Override
    public int compareTo(DjikstraAlgoTable other)
    {
        return Double.compare(this.normalizedValue, other.getValue());
    }

    

    
}