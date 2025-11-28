import java.util.Scanner; 


//This is a test from sara becasue she doesn't know GIT very well
public class Main {
    public static void main(String[] args)
    {
        Graph graph = new Graph(); 
        String sourceNodeName = null;
        String destinationNodeName = null; 
        double minutesImportance = 0.0;
        double milesImportance = 0.0;
        Scanner inputScanner = new Scanner(System.in); 
        boolean quit = false; 
        String continueInput; 

        graph.populate();
        graph.print();

        System.out.print("Enter the source node: "); 
        sourceNodeName = inputScanner.nextLine(); 

        System.out.print("Enter destination node: ");
        destinationNodeName = inputScanner.nextLine(); 

        // keep going until the user signifies 
        while (quit == false)
        {
            // get the input with the proper input validation
            System.out.print("On a scale of 0-10, enter how much importance you want to put on minutes: "); 
            minutesImportance = inputScanner.nextDouble(); 
            
            while(minutesImportance < 0 || minutesImportance > 10)
            {
                System.out.println("Input a number on a scale of 0-10. Try Again!"); 
                System.out.print("On a scale of 0-10, enter how much importance you want to put on minutes: "); 
                minutesImportance = inputScanner.nextDouble(); 
            }
            minutesImportance = minutesImportance / 10; 

            // get the input with the proper input valdiation
            System.out.print("On a scale of 1-10, enter how much importance you want to put on miles: ");
            milesImportance = inputScanner.nextDouble(); 
            while(milesImportance < 0 || milesImportance > 10)
            {
                System.out.println("Input a number on a scale of 0-10. Try Again!"); 
                System.out.print("On a scale of 0-10, enter how much importance you want to put on miles: "); 
                milesImportance = inputScanner.nextDouble(); 
            }
            milesImportance = milesImportance / 10;
            

            graph.djikstra(sourceNodeName, destinationNodeName, minutesImportance, milesImportance); // performs djiksrta's algorithm 
            
            // ask if they want to continue
            System.out.print("Do you want to try other values of importance (Y/N): ");
            continueInput = inputScanner.next();

            if (continueInput.equals("N"))
                quit = true; 


        }
     
    }
}
