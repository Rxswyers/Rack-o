import java.util.Stack;
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
	public int getSize()
	{
		return Pile.size();
	}
	public boolean empty()
	{
		return Pile.empty();
	}
	public Card getTop()
	{
		if(this.empty())
		{
			return new Card(0);
		}
		else
		{
			return this.Pile.peek();
		}
	}
	public String top()
	{
		if(this.empty())
		{
			return "O";
		}
		else
		{
			return this.Pile.peek().toString();
		}
	}
	public String toString()
	{
		String result = "";
		//Printed out the stack without popping anything from it
		//in order to do so, you need to treat it as a vector
		for(int i = Pile.size() - 1; i >= 0; i--)
		{
			result += "" + Pile.get(i).toString() + "\n";
		}
		return result;
	}

}
