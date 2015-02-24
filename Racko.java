
import java.util.*;
public class Racko
{
	//members
	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList<Card> Cards = new ArrayList<Card>();
	Deck DrawPile = new Deck();
	Deck DiscardPile = new Deck();
	int currentTurn;
	//methods
	//Name: 		getCards
	//Parameters:	none
	//Returns:		N/A
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
	//Name:			addPlayer
	//Paramaters:	a Player
	//Returns:		N/A
	//Description:	Adds a player to the ArrayList of players
	//public void addPlayer(Player P)
	public void addPlayer(String name, char type)
	{
		//this.Players.add(P);
		if(type == 'h' || type == 'H')
		{
			this.Players.add(new Human(name));
		}
		else
		{
			this.Players.add(new Computer(name));
		}

	}
	//Name:			deal
	//Parameters:	none
	//Returns:		N/A
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
				this.Players.get(i).getCard(this.Cards.get(count));
				count++;
			}
		}
		//Used for debugging
		System.out.println(this.Players.get(0).getName() +"'s hand");
		this.Players.get(0).printHand();
		System.out.println(this.Players.get(1).getName() +"'s hand");
		this.Players.get(1).printHand();
		//Populates the draw pile with the rest of the cards that weren't dealt to players
		for(int num = count; num < this.Cards.size(); num++)
		{
			DrawPile.addCard(this.Cards.get(num));
		}
		System.out.println(DrawPile);
		//sets player one as going first
		this.currentTurn = 0;
	}
	//Name:			checkDeck
	//Parameters:	none
	//Returns:		N/A
	//Description:	Checks to see if the drawPile is empty, if it is, it'll
	// call switchDecks()
	public void checkDeck()
	{
		if(DrawPile.empty())
		{
			this.switchDecks();
		}
	}
	//Name:			switchDecks
	//Parameters:	none
	//Returns:		N/A
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
		Collections.shuffle(TempCards);
		for(Card C: TempCards)
		{
			C.setState(false);
			DrawPile.addCard(C);
		}
	}
	public void nextTurn()
	{
		int num = currentTurn % this.Players.size();
		System.out.println("This is num: " + num);
		switch(num)
		{
			case 0:
				DiscardPile.addCard(this.Players.get(0).takeTurn(this.drawFrom(this.Players.get(0).choosePile())));
				//System.out.println("This is what you chose " + this.Players.get(0).choosePile());
				break;
			case 1:
				this.Players.get(1).takeTurn(this.drawFrom(this.Players.get(1).choosePile()));
				break;
			//case 2:
				//this.Players.get(2).takeTurn(this.drawFrom(this.Players.get(2).choosePile()));
			//case 3:
				//this.Players.get(3).takeTurn(this.drawFrom(this.Players.get(3).choosePile()));
		}
		//this.currentTurn ++;
	}
	public Card drawFrom(int choice)
	{
		if(choice == 0)
		{
			return DrawPile.draw();
		}
		else
		{
			return DiscardPile.draw();
		}
	}
	public String toString()
	{
		return DrawPile.top() + "     "+ DiscardPile.top();
	}
}
