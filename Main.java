public class Main {
    public static void main(String[] args) {
        Graph myGraph = new Graph("Arthur's Graph"); 

        System.out.println(myGraph.getName()); 

        myGraph.populate();
        myGraph.print(); 
    }
}