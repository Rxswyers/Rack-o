import java.util.ArrayList;
public class Rack
{
	private ArrayList<Card> Hand = new ArrayList<Card>();
	

	public boolean addCard(Card C)
	{
		
		boolean result = this.Hand.add(C);
		
		return result;
	}
	public Card discard(int index)
	{
		return Hand.remove(index);
	}
	public void show()
	{
		for(int i = 0; i < Hand.size(); i ++)
		{
			System.out.println(Hand.get(i));
		}
	}
}
