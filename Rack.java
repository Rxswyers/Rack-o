import java.util.ArrayList;
public class Rack
{
	//members
	private ArrayList<Card> Hand = new ArrayList<Card>();

	//
	public boolean addCard(Card C)
	{
		return this.Hand.add(C);
	}
	public Card discard(int index)
	{
		return this.Hand.remove(index);
	}
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
