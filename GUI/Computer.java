public class Computer extends Player
{
	public Computer(String name)
	{
		super(name);
	}
	public void pickupCard(Card C) // set this to abstract
	{
		C.setState(true); //flips the card up so the value can be seen
		C.setOwner(1);
		this.Hand.setExtra(C);
	}
	public int choosePile(Card Top)
	{
		if(this.positionWanted(Top) < 0)//the Discard pile is emtpy
		{
			return 0;
		}
		else if(this.checkPosition(this.positionWanted(Top)))
		{
			return 1; //draw from the discard
			//CHANGED AT 9:34 AM
		}
		else
		{
			return 1; //draw from the draw pile
		}
	}

	public int positionWanted(Card C)
	{
		int value = C.getValue();
		if(value >= 1 && value <= 4)
		{
			return 0;
		}
		else if(value >= 5 && value <= 8)
		{
			return 1;
		}
		else if(value >= 9 && value <= 12)
		{
			return 2;
		}
		else if(value >= 13 && value <= 16)
		{
			return 3;
		}
		else if(value >= 17 && value <= 20)
		{
			return 4;
		}
		else if(value >= 21 && value <= 24)
		{
			return 5;
		}
		else if(value >= 25 && value <= 28)
		{
			return 6;
		}
		else if(value >= 29 && value <= 32)
		{
			return 7;
		}
		else if(value >= 33 && value <= 36)
		{
			return 8;
		}
		else if(value >= 37 && value <= 40)
		{
			return 9;
		}
		else
		{
			return -1;
		}
	}
	public boolean checkPosition(int position)
	{
		if(positionWanted(this.Hand.search(position)) == position)
		{
			return true; //position is filled with a card that is desired
		}
		else
		{
			return false; //the position doesn't have a card that is desired
		}
	}
	public Card chooseDiscard(Card C)
	{
		int pos = positionWanted(this.Hand.getExtra());
		if(pos == -1)
		{
			return this.Hand.getExtra();
		}
		else if(checkPosition(pos))
		{
			return this.Hand.getExtra();
		}
		else
		{
			return this.Hand.discard(this.Hand.search(pos));
		}
	}
	public Card getDiscard()
	{
		int pos = positionWanted(this.Hand.getExtra());
		if(pos == -1)
		{
			return this.Hand.getExtra();
		}
		else if(checkPosition(pos))
		{
			return this.Hand.getExtra();
		}
		else
		{
			return this.Hand.search(pos);
		}
	}
}
