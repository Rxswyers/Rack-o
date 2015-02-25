import java.util.Scanner;
public class Player
{
  //members
  protected String name;
  protected Rack Hand = new Rack();
  protected int score;				//this score is going to be the actual score
  protected int currentScore;			//this will be the calculated score at the end of each turn
  protected Card ExtraCard;// = new Card();
  //methods

  public Player(String name)
  {
	  this.setName(name);
  }
  //sets
  public void setScore(int s)
  {
	  this.score = s;
  }
  public void setCurrentScore(int s)
  {
	  this.currentScore = s;
  }
  public void setName(String s)
  {
    this.name = s;
  }
  //gets
  public int getScore()
  {
	  return this.score;
  }
  public int getCurrentScore()
  {
	  return this.currentScore;
  }
  public String getName()
  {
    return this.name;
  }
  //other methods
  public void getCard(Card C)
  {
    C.setState(true);
    this.Hand.addCard(C);
  }
  public void pickupCard(Card C)
  {
    C.setState(true); //flips the card up so the value can be seen
    this.ExtraCard = C;
    System.out.println("Picked up:");
    this.showExtraCard();
    //this.Hand.addCard(C);
  }
  public void showExtraCard()
  {
    System.out.println(this.ExtraCard);
  }
  public void printHand()
  {
    System.out.println(this.Hand);
  }

  public Card takeTurn(Card C)
  {
    this.pickupCard(C);
    return chooseDiscard();
  }
  public Card chooseDiscard()
  {
    Scanner keyboard = new Scanner(System.in);
    int choice;
    System.out.println("My Rack:");
    System.out.println(this.Hand);
    System.out.println("What card do you want to discard? (Enter the value of the card)");
    choice = keyboard.nextInt();
    if(choice == this.ExtraCard.getValue())
    {
      return this.ExtraCard;
    }
    else
    {
      return this.Hand.discard(this.ExtraCard,this.Hand.find(choice));
    }


  }
  public int choosePile()
  {
    Scanner keyboard = new Scanner(System.in);
    int choice;
    System.out.println("What pile would you like to pick up from? (0 for Draw, 1 for Discard)");
    choice = keyboard.nextInt();
    while(choice != 0 && choice != 1)
    {
      System.out.println("You did not enter 0 or 1. Please enter 0 for the Draw pile or 1 for the Discard pile:");
      choice = keyboard.nextInt();
    }
    return choice;
  }
  //public void countScore()
  //{

  //}
}
