public class Deck
{
	//members
	Stack<Card> Pile = new Stack<Card>();

	//methods
	public void addCard(Card C)
	{
		this.Pile.push(C);
	}
	public Card draw()
	{
		return this.Pile.pop();
	}
	
}
