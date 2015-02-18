public class Racko
{
	//members
	ArrayList<Player> Players = new ArrayList<Player>();
	Deck DrawPile = new Deck();
	Deck DiscardPile = new Deck();

	public void addPlayer(Player P)
	{
		return this.Players.add(P);
	}

}
