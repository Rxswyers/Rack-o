import java.util.Scanner;
public class Human extends Player
{
	public Human(String name)
	{
		super(name);
	}
	public Card takeTurn(Card C)
  {
    this.pickupCard(C);
    return chooseDiscard();
  }
  public Card chooseDiscard()
  {
    Scanner keyboard = new Scanner(System.in);
    Card Result;
    int choice;
    System.out.println("My Rack:");
    System.out.println(this.Hand);
    System.out.println("What card do you want to discard? (Enter the value of the card)");
    choice = keyboard.nextInt();
    if(choice == this.ExtraCard.getValue())
    {
      Result = this.ExtraCard;
    }
    else
    {
      Result = this.Hand.discard(this.ExtraCard,this.Hand.find(choice));
    }
    System.out.println("Current rack score: " + this.Hand.score());
    return Result;

  }
  public int choosePile(Card Top)
  {
    Scanner keyboard = new Scanner(System.in);
    int choice;
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
