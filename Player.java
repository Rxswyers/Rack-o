public class Player
{
  //members
  String name;
  Rack Hand = new Rack();
  int score;				//this score is going to be the actual score
  int currentScore;			//this will be the calculated score at the end of each turn
  //methods

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
  public void pickupCard(Card C)
  {
    this.Hand.addCard(C);
  }
  public void printHand()
  {
    System.out.println(this.Hand);
  }

  //public void takeTurn()
  //{

  //}
  //public void countScore()
  //{

  //}
}
