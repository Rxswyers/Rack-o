public class Card
{
	//members
	private int value;
	private boolean state; //true - flipped up, false - face down

	//methods

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

	//constructor
	public Card(int v)
	{
		this.value = v;
		this.state = false;
	}

	//other methods
	public String toString()
	{
		String s = this.getValue() + " ";
		if(this.getState())
			s += "face up";
		else
			s+= "face down";
		return s;
	}
}
