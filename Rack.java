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
	public Card discard(int value)
	{
		for(Card C:Hand)
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
	public String toString()
	{
		String result = "\n";
		for(int i = 0; i < Hand.size(); i ++)
		{
			result += this.Hand.get(i) +"\n";
		}
		return result;
	}
}
