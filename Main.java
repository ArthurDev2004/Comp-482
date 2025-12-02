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
        double minutes_input = 0.0;
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
            minutes_input = (int) inputScanner.nextDouble();
            
            while(minutes_input < 0 || minutes_input > 10)
            {
                System.out.println("Input a number on a scale of 0-10. Try Again!"); 
                System.out.print("On a scale of 0-10, enter how much importance you want to put on minutes: "); 
                minutes_input = (int) inputScanner.nextDouble();
                 

            }
            minutesImportance = minutes_input / 10;

            System.out.println("Minutes Importance: " + minutesImportance);
            
            milesImportance = (10-minutes_input)/10;
            System.out.println("Miles Importance: " + milesImportance);

            System.out.println("Please confirm the following Importance scale you chose. \nMinutes Importance: " + (minutesImportance*10) + "\nMiles Importance: " + (milesImportance*10));
            System.out.println("Is the following correct? (Y/N)");
            char user_confirm = ((inputScanner.next()).toUpperCase()).charAt(0);
            
            
    
            while(user_confirm == 'N'){
                System.out.print("On a scale of 0-10, enter how much importance you want to put on minutes: "); 
                minutes_input = (int) inputScanner.nextDouble(); 
                
                while(minutes_input <= 0 || minutes_input >= 10)
                {
                    System.out.println("Input a number on a scale of 0-10. Try Again!"); 
                    System.out.print("On a scale of 0-10, enter how much importance you want to put on minutes: "); 
                    minutes_input = (int) inputScanner.nextDouble();
                    

                }
                minutesImportance = minutes_input / 10;

                System.out.println("Minutes Importance: " + minutesImportance);
                
                milesImportance = (10-minutes_input)/10;
                System.out.println("Miles Importance: " + milesImportance);

                System.out.println("Please confirm the following Importance scale you chose. \nMinutes Importance: " + (minutesImportance*10) + "\nMiles Importance: " + (milesImportance*10));
                System.out.println("Is the following correct? (Y/N)");
                user_confirm = ((inputScanner.next()).toUpperCase()).charAt(0);
            }
            while(user_confirm != 'N' && user_confirm != 'Y'){
                System.out.println("Invalid response. Try Again!");
                System.out.println("Please confirm the following Importance scale you had given us. \nMinutes Importance: " + (minutesImportance*10) + "\nMiles Importance: " + (milesImportance*10));
                System.out.println("Is the following correct? (Y/N)");
                user_confirm = ((inputScanner.next()).toUpperCase()).charAt(0);

            } 
            if(user_confirm == 'Y'){
                graph.djikstra(sourceNodeName, destinationNodeName, minutesImportance, milesImportance); // performs djiksrta's algorithm

            }

                        
            // ask if they want to continue
            System.out.print("Do you want to try other values of importance (Y/N): ");
            continueInput = inputScanner.next();

            if (continueInput.equals("N"))
                quit = true; 


        }
     
    }
}
