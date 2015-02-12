public class Card
{
	//members
	private int value;
	private boolean state; //true - flipped up, false - face down
	
	//methods
	
	//gets
	public int getValue()
	{
		return value;
	}
	public boolean getState()
	{
		return state;
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
	//constructors
	public Card(int v)
	{
		this.value = v;
		this.state = true;
	}
	public String toString()
	{
		String s = this.getValue() + " ";
		if(getState())
		{
			s += "face up";
		}
		s+= "face down";
		return s;
	}
}
