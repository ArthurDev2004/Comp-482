// represents the two weights of each edge in the graph
public class Pair {
    private final double minutes; 
    private final double  miles; 
    private double normalizedMinutes; 
    private double normalizedMiles;
    private double singalFinalValue; 

    public Pair(double minutes, double miles)
    {
        this.minutes = minutes; 
        this.miles = miles; 
        this.normalizedMinutes = 0.0;
        this.normalizedMiles = 0.0;
        this.singalFinalValue = 0.0;
    }

    public double getMinutes()
    {
        return this.minutes; 
    }

    public double getMiles()
    {
        return this.miles; 
    }

    public double getNormalizedMiles()
    {
        return this.normalizedMiles; 
    }

    public double getNormalizedMinutes()
    {
        return this.normalizedMinutes; 
    }

    public void setNormalizedMiles(double normalizedMiles)
    {
        this.normalizedMiles = normalizedMiles; 
    }

    public void setNormalizedMinutes(double normalizedMinutes)
    {
        this.normalizedMinutes = normalizedMinutes; 
    }

    public void setSingalFinalValue(double finalValue)
    {
        this.singalFinalValue = finalValue;
    }

    public double getSingalFinalValue()
    {
        return this.singalFinalValue; 
    }

}
