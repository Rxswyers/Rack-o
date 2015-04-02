import java.util.ArrayList;
public class StringTest
{
  public static void main(String args[])
  {
    String S;
    int next = -1;
    int turnLimit = 0;
    boolean limitTurns;
    boolean sCheat;
    int numOrdered = 0;
    int orderLoc = 0;
    String delims = " ";
    String response = "/c /n 3 /s 10";
    String[] cheats = response.split(delims);
    int turnLocation = 0;
    ArrayList<String> cheatList = new ArrayList<String>();
    boolean showOpponent = false;
    for(String s:cheats)
    {
      cheatList.add(s);
      System.out.println(s);
    }
    if(cheatList.contains("/c"))
    {
      showOpponent = true;
    }
    else
    {
      showOpponent = false;
    }
    if(cheatList.contains("/n"))
    {
      limitTurns = true;
      turnLocation = cheatList.indexOf("/n") + 1;
      turnLimit = Integer.parseInt(cheatList.get(turnLocation));
      turnLimit *= 2;
    }
    else
    {
      limitTurns = false;
    }
    if(cheatList.contains("/s"))
    {
      sCheat = true;
      orderLoc = cheatList.indexOf("/s") + 1;
      numOrdered = Integer.parseInt(cheatList.get(orderLoc));
      if(numOrdered > 10)
      {
        numOrdered = 10;
      }
      else if(numOrdered < 0)
      {
        numOrdered = 0;
      }
    }
    else
    {
      sCheat = false;
    }
    System.out.println("Show opponent? " + showOpponent);
    System.out.println("Limit Turns: "+ limitTurns + " amount of turns: " + turnLimit);
    System.out.println("Sort cheat? "+ sCheat + " number to be sorted: " + numOrdered);

    /*
    public void loadCheats(String []args)
  	{
  		for(String arg:args)
  		{
  			Cheats.add(arg);
  		}
  		  checkCheats();
  	}
  	public void checkCheats()
  	{
      if(cheatList.contains("/c"))
      {
        showOpponent = true;
      }
      else
      {
        showOpponent = false;
      }
      if(cheatList.contains("/n"))
      {
        limitTurns = true;
        turnLocation = cheatList.indexOf("/n") + 1;
        turnLimit = Integer.parseInt(cheatList.get(turnLocation));
        turnLimit *= 2;
      }
      else
      {
        limitTurns = false;
      }
      if(cheatList.contains("/s"))
      {
        sCheat = true;
        orderLoc = cheatList.indexOf("/s") + 1;
        numOrdered = Integer.parseInt(cheatList.get(orderLoc));
        if(numOrdered > 10)
        {
          numOrdered = 10;
        }
        else if(numOrdered < 0)
        {
          numOrdered = 0;
        }
      }
      else
      {
        sCheat = false;
      }
  	}
    */
  }
}
