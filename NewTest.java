import java.util.*;
public class NewTest
{
  public static void main(String[] args)
  {
    Racko Game = new Racko();
    Scanner keyboard = new Scanner(System.in);
    Player P1 = new Player();
    Player P2 = new Player();
    Game.addPlayer(P1);
    Game.addPlayer(P2);
    Game.getCards();
  }
}
