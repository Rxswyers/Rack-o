import java.util.*;
public class NewTest
{
  public static void main(String[] args)
  {
    Deck Stuff = new Deck();
    Racko Game = new Racko();
    Scanner keyboard = new Scanner(System.in);
    String name;

    System.out.println("What is your name?");
    name = keyboard.nextLine();
    Game.addPlayer(name, 'h');
    Game.addPlayer("Computer", 'c');
    Game.getCards();
    Game.deal();
    Game.loadCheats(args);
    while(Game.checkWin()!= true)
    {
      System.out.println("Draw  Discard");
      System.out.println(Game);
      Game.nextTurn();
    }


  }
}
