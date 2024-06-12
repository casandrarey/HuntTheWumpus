
public class Main {
  public static void main(String[] args){

    //new GUI().init();
    

    
    // QUESTIONS ---------------------------------------------------------------
    
    System.out.println("\nTHE QUESTIONS");
    System.out.println(new Trivia().getQuestions());
    
    
    // MAP ---------------------------------------------------------------------
    Map m = new Map("map");
    Cave c = new Cave(m.getLocations());

    System.out.println("\nTHE CAVE");
    System.out.println(c);

    new Controller(c);
    
    System.out.println("Hello world!");
  }

}