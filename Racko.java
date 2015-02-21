
import java.util.*;
public class Racko
{
	//members
	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList<Card> Cards = new ArrayList<Card>();
	Deck DrawPile = new Deck();
	Deck DiscardPile = new Deck();
	//methods
	//Name: 				getCards
	//Parameters:		none
	//Returns:			N/A
	//Description:	Depending on the number of players there are,
	// a certain number of cards will be used for the game. After
	// the number of cards is decided, they are shuffled.
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
		Collections.shuffle(this.Cards);
	}
	//Name:					addPlayer
	//Paramaters:		a Player
	//Returns:			N/A
	//Description:	Adds a player to the ArrayList of players
	public void addPlayer(Player P)
	{
		this.Players.add(P);
	}
	//Name:					deal
	//Parameters:		none
	//Returns:			N/A
	//Description:	This method deals 10 cards to each player and then
	// populates the DrawPile with the rest of the cards.
	public void deal()
	{
		//Used to keep track of the amount of cards that are dealt
		int count = 0;
		//Deals 10 cards to each player
		for(int i = 0; i < this.Players.size(); i++)
		{
			for(int j = 0; j < 10; j++)
			{
				this.Players.get(i).pickupCard(this.Cards.get(count));
				count++;
			}
		}
		//Used for debugging
		System.out.println("Player one's hand");
		this.Players.get(0).printHand();
		System.out.println("Player two's hand");
		this.Players.get(1).printHand();
		//Populates the draw pile with the rest of the cards that weren't dealt to players
		for(int num = count; num < this.Cards.size(); num++)
		{
			DrawPile.addCard(this.Cards.get(num));
			//Used for debugging
			//DiscardPile.addCard(this.Cards.get(num));
		}
	}
	//Name:					checkDeck
	//Parameters:		none
	//Returns:			N/A
	//Description:	Checks to see if the drawPile is empty, if it is, it'll
	// call switchDecks()
	public void checkDeck()
	{
		if(DrawPile.empty())
		{
			this.switchDecks();
		}
	}
	//Name:					switchDecks
	//Parameters:		none
	//Returns:			N/A
	//Description:	Empties out the discard pile, shuffles the cards,
	// then populates the draw pile with those cards
	public void switchDecks()
	{
		ArrayList<Card> TempCards = new ArrayList<Card>();
		while(!DiscardPile.empty())
		{
			TempCards.add(DiscardPile.draw());
		}
		//Shuffle the cards before they go into the drawPile
		TempCards.shuffle();
		for(Card C: TempCards)
		{
			DrawPile.addCard(C);
		}
	}
}
