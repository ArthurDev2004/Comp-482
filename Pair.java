// represents the two weights of each edge in the graph
public class Pair {
    private final double minutes; 
    private final double  miles; 
    private double normalizedValue; 

    public Pair(double min, double mi)
    {
        this.minutes = min; 
        this.miles = mi; 
        this.normalizedValue = 0.0; 
    }

    public double getMinutes()
    {
        return this.minutes; 
    }

    public double getMiles()
    {
        return this.miles; 
    }

    public void setNormalizedValue(double normalized)
    {
        this.normalizedValue = normalized; 
    }

    public double getNormalizedValue()
    {
        return this.normalizedValue; 
    }

}
