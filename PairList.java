
public class PairList<N, L> {
    private final N node; 
    private L list; 

    public PairList(N node, L list)
    {
        this.node = node;
        this.list = list; 
    }

    public N getNode()
    {
        return this.node; 
    }

    public L getList()
    {
        return this.list; 
    }

}
