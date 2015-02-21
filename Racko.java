
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
		int cardSize;
		switch(Players.size())
		{
			case 2:
			cardSize = 40;
			break;
			case 3:
			cardSize = 50;
			break;
			case 4:
			cardSize = 60;
			break;
		}
		for(int i = 1; i <= cardSize; i ++)
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
/*
public void populateDeck()
{
}
public void swapDecks()
{
}
*/
}
