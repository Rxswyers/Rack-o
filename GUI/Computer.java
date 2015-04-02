/**
*Computer Player to play Racko, it uses a rudementary AI to make it's choices
*for now.
*@author	Ruben Swyers
*/
public class Computer extends Player
{
	/**
	*@param name			Computer's desired name
	*/
	public Computer(String name)
	{
		super(name);
	}
	/**
	*Adds a Card to the Rack's extra slot
	*@param	C					Card to be added to the extra slot
	*/
	public void pickupCard(Card C) // set this to abstract
	{
		C.setState(true); //flips the card up so the value can be seen
		C.setOwner(1);
		this.Hand.setExtra(C);
	}
	/**
	*Chooses what pile to draw from, either the Draw or Discard
	*@param	Top				The top of the Discard pile
	*@return					<code>0</code> if it needs the Card from the Discard
	*deck, or <code>1</code> to draw from the Draw deck.
	*/
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
	/**
	*Gets the desired position of a certain Card
	*@param C					Card to get the desired position of
	*@return					The desired position of the Card
	*/
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
	/**
	*Checks to see if a certain position of the Rack is taken by a desired Card.
	*It will use positionWanted to check to see if it is desired or not.
	*@param	position				Position to check
	*@return								<code>true</code> if the position is filled with a desired slot or
	*<code>false</code> if it is not.
	*/
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
	/**
	*Removes a Card from their Rack. This was rewritten for use in the GUI.
	*@param C							Card to remove from the Rack (including the extra slot)
	*@return							The Card once it's removed from the Rack.
	*/
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
	/**
	*Gets the Card that is wanted to be discarded, it doesn't actually
	*remove it from anything.
	*@return			Card that is chosen to discard.
	*/
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
