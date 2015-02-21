import java.util.*;
public class Racko
{
	public static void main(String args[])
	{
		ArrayList<Player> Players = new ArrayList<Player>();
		ArrayList<Card> Cards = new ArrayList<Card>();
		Deck DrawPile = new Deck();
		Deck DiscardPile = new Deck();
		int cardSize = 0;

		Player P1 = new Player();
		Player P2 = new Player();
		Players.add(P1);
		Players.add(P2);

		//Sets the number of cards based on how many players there are
		//2 Players - 40 Cards
		//3 Players - 50 Cards
		//4 Players - 60 Cards
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
		//Creates the cards
		for(int i = 1; i <= cardSize; i ++)
		{
			Cards.add(new Card(i));
		}
		//shuffles the cards up to be put in the DrawPile(Deck)
		Collections.shuffle(Cards);



		deal(Players,Cards,DrawPile);

		System.out.println(DrawPile);
	}
	public void deal(ArrayList<Player> Ps, ArrayList<Cards> Cs, Deck D)
	{
		int count = 0;
		for(int i = 0; i < Ps.size(); i++)
		{
			Ps.get(i).pickupCard(Cs.get(count));
			count++;
		}
		for(int j = count; j < Ps.size(); j++)
		{
			D.push(Cs.get(j));
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
