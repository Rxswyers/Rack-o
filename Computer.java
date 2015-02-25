public class Computer extends Player
{
	public Computer(String name)
	{
		super(name);
	}
	public int choosePile()
	{
		return 1;
	}
	public Card takeTurn(Card C)
	{
		return new Card(0);
	}
}
