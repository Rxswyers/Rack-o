import java.util.Scanner;
public class Human extends Player
{
	public Human(String name)
	{
		super(name);
	}
	public void pickupCard(Card C)
	{
		C.setState(true); //flips the card up so the value can be seen
		C.setOwner(0);
		this.Hand.setExtra(C);
	}
  public Card chooseDiscard(Card D)
  {
    Scanner keyboard = new Scanner(System.in);
    Card Result;
    Result = this.Hand.discard(D);
    System.out.println("Current rack score: " + this.Hand.score());
    return Result;

  }
  public int choosePile(Card Top)
  {
    Scanner keyboard = new Scanner(System.in);
    int choice;
		System.out.println("My Rack:");
		this.printHand();
		System.out.println("The top of the discard pile is: " + Top);
    System.out.println("What pile would you like to pick up from? (0 for Draw, 1 for Discard)");
    choice = keyboard.nextInt();
    while(choice != 0 && choice != 1)
    {
      System.out.println("You did not enter 0 or 1. Please enter 0 for the Draw pile or 1 for the Discard pile:");
      choice = keyboard.nextInt();
    }
    return choice;
  }

}
