// represents the two weights of each edge in the graph
public class Pair {
    private final double minutes; 
    private final double  km; 
    private double normalizedMinutes; 
    private double normalizedKm;
    private double singalFinalValue; 

    public Pair(double minutes, double km)
    {
        this.minutes = minutes; 
        this.km = km; 
        this.normalizedMinutes = 0.0;
        this.normalizedKm = 0.0;
        this.singalFinalValue = 0.0;
    }

    public double getMinutes()
    {
        return this.minutes; 
    }

    public double getKm()
    {
        return this.km; 
    }

    public double getNormalizedKm()
    {
        return this.normalizedKm; 
    }

    public double getNormalizedMinutes()
    {
        return this.normalizedMinutes; 
    }

    public void setNormalizedMiles(double normalizedMiles)
    {
        this.normalizedKm = normalizedMiles; 
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
