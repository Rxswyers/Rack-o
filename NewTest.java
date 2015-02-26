import java.util.*;
public class NewTest
{
  public static void main(String[] args)
  {
    Deck Stuff = new Deck();
    Racko Game = new Racko();
    Scanner keyboard = new Scanner(System.in);


    Game.addPlayer("Hank", 'h');
    Game.addPlayer("Cortana", 'c');
    Game.getCards();
    Game.deal();
	  System.out.println(Game);
    Game.nextTurn();
    System.out.println(Game);
    Game.nextTurn();
    System.out.println(Game);
    

  }
}
