public class Menu {

    // helper for the menu text
    public static void menu()
    {
        System.out.println("1) Add Node");
        System.out.println("2) Add Edge");
        System.out.println("3) Quit");
        System.out.print("Enter selection: "); 
    }

    public static void insertNodeMenu()
    {
        System.out.print("Enter the name of the node: ");
    }
}