import java.util.*;
public class Racko
{
	//members
	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList<Card> Cards = new ArrayList<Card>();
	Deck DrawPile = new Deck();
	Deck DiscardPile = new Deck();


	//methods
	public void Racko()
	{
		for(int i = 1; i <= 40; i ++)
		{
			this.Cards.add(new Card(this));
		}
		Collections.shuffle(Cards);

	}
	public void addPlayer(Player P)
	{
		return this.Players.add(P);
	}
	public void deal()
	{
		int count = 0;
		for(int i = 0; i < Players.size(); i++)
		{
			Players.get(i).pickupCard(Cards.get(count));
			count++;
		}
	}
	public void populateDeck()
	{

	}
	public void swapDecks()
	{

	}
}
