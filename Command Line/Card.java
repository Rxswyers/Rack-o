public class Card
{
	//members
	private int value;
	private boolean state; //true - flipped up, false - face down

	//methods

	//constructor
	public Card(int v)
	{
		this.value = v;
		this.state = false;
	}

	//gets
	public int getValue()
	{
		return this.value;
	}
	public boolean getState()
	{
		return this.state;
	}

	//sets
	public void setValue(int v)
	{
		this.value = v;
	}
	public void setState(boolean s)
	{
		this.state = s;
	}

	//other methods
	public String toString()
	{
		String s ="";
		if(this.getState())
			s += this.getValue(); //face up
		else
			s+= "X";//face down;
		return s;
	}
}
