import java.util.ArrayList;
public class Rack
{
	//members
	private ArrayList<Card> Hand = new ArrayList<Card>();

	//methods
	//Name:				addCard
	//Parameters:	a Card
	//Returns:		boolean
	//Description:
	public boolean addCard(Card C)
	{
		return this.Hand.add(C);
	}
	//Name:
	//Parameters:
	//Returns:
	//Description:
	public Card discard(Card Replace,Card Discard)
	{
		int place = this.Hand.indexOf(Discard);
		this.Hand.remove(Discard);
		this.Hand.add(place,Replace);
		return Discard;

	}
	public Card search(int pos)
	{
		return this.Hand.get(pos);
	}
	//Name:
	//Parameters:
	//Returns:
	//Description:
	public Card find(int value)
	{
		for(Card C:this.Hand)
		{
			if(C.getValue() == value)
			{
				return C;
			}
		}
		return new Card(0);
	}
	//Name:
	//Parameters:
	//Returns:
	//Description:
	public int getSize()
	{
		return this.Hand.size();
	}
	//Name:
	//Parameters:
	//Returns:
	//Description:
	public int score()
	{
		int run = 1;
		int highestRun = 0;
		int score = 0;
		int least = this.Hand.get(0).getValue();
		int high = 0;
		for(Card C: this.Hand)
		{
			if(least < C.getValue())
			{
				least = C.getValue();
				run ++;
			}
			else
			{
				if(run > highestRun)
				{
					highestRun = run;
				}
				run = 1;
				least = C.getValue();
			}
		}
		score = highestRun * 5;
		if(score == 50)
		{
			score += 25;
		}
		return score;
	}
	//Name:
	//Parameters:
	//Returns:
	//Description:
	public String toString()
	{
		String result = "\n";
		for(int i = this.Hand.size() - 1; i >= 0; i --)
		{
			result += this.Hand.get(i) +"\n";
		}
		return result;
	}
}
