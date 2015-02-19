public class Player
{
  //members
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
  
  //gets
  public int getScore()
  {
	  return this.score;
  }
  public int getCurrentScore()
  {
	  return this.currentScore;
  }
  
  //other methods
  public void takeTurn()
  {
	  
  }
  public void countScore()
  {
	  
  }
}
