public class Racko
{	
	//members
	ArrayList<Player> Players = new ArrayList<Player>();
	Stack<Card> Deck = new Stack<Card>();
	Stack<Card> DiscardPile = new Stack<Card>();
	
	public void addPlayer(Player P, char type)
	{
		return this.Players.add(P);
	}
	
}
