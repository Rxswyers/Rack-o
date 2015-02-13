import java.util.ArrayList;
public class Rack
{
	private ArrayList<Card> Hand = new ArrayList<Card>();
	

	public boolean addCard(Card C)
	{
		return this.Hand.add(C);
	}
	public Card discard(int index)
	{
		return this.Hand.remove(index);
	}
	public void show()
	{
		for(int i = 0; i < Hand.size(); i ++)
		{
			System.out.println(Hand.get(i));
		}
	}
}
