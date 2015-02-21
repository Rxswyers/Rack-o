
import java.util.*;
public class Racko
{
	//members
	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList<Card> Cards = new ArrayList<Card>();
	Deck DrawPile = new Deck();
	Deck DiscardPile = new Deck();
	//methods
	public void getCards()
	{
		int cardSize = 0;
		switch(this.Players.size())
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
			this.Cards.add(new Card(i));
		}
		//Collections.shuffle(this.Cards);
	}
	public void addPlayer(Player P)
	{
		this.Players.add(P);
	}
	public void deal()
	{
		int count = 0;
		for(int i = 0; i < this.Players.size(); i++)
		{
			for(int j = 0; j < 10; j++)
			{
				this.Players.get(i).pickupCard(this.Cards.get(count));
				count++;
			}
		}
		System.out.println("Player one's hand");
		this.Players.get(0).printHand();
		System.out.println("Player two's hand");
		this.Players.get(1).printHand();
		//Populates the draw pile with the rest of the cards that weren't dealt to players
		for(int num = count; num < this.Cards.size(); num++)
		{
			DrawPile.addCard(this.Cards.get(num));
		}
		System.out.println(DrawPile);
		System.out.println(DrawPile.draw());
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
