/*Author: 					Ruben Swyers
* Creation Date: 		March 15, 2015
* Due Date: 				April 3, 2015
* Course: 					CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 			#2 - Racko GUI
* Filename: 				Rack.java
* Purpose:		  		This represents a Rack in the GUI. It is a JPanel that has a JLayeredPane on it.
					The cards are added to the JLayeredPane to allow overlapping.
*/
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
/**
*Rack holds the Cards that a Player has, including the extra one
* that a player picks up.
*@author Ruben Swyers
*/
public class Rack extends JPanel
{
	/**
	*Holds the contents of the Rack, layers them as well.
	*/
	JLayeredPane RJLP;
	/**
	*Keeps track of the Rack, including order.
	*/
	ArrayList<Card> rack = new ArrayList<Card>();
	/**
	*The Card is an extra, and is what the Player picks up
	*but doesn't put directly into the rack unless they decide to
	*/
	Card ExtraCard;
	/**
	*Sets up the JLayeredPane
	*/
	public Rack()
	{
		this.RJLP = new JLayeredPane();
		this.add(this.RJLP);
		this.RJLP.setBounds(0,0,800,200);
		this.RJLP.setLayout(null);

	}
	/**
	*Adds a card to a specified index
	*@param	C			Card to be added
	*@param index	position to add the Card
	*/
	public void addCard(Card C, int index)
	{
		this.rack.add(C);
		this.RJLP.add(C,new Integer(index));
	}
	/**
	*Prints the rack out.
	*/
	public void printStuff()
	{
		for(int i = this.rack.size() - 1; i >= 0; i --)
		{
			System.out.println(this.rack.get(i));
		}
	}
	/**
	*Finds a Card in the Rack with an index
	*@param v 		index of the Card
	*@return			The Card at that index
	*/
	public Card find(int v)
	{
		return rack.get(v);
	}
	/**
	*Removes a Card from the Rack, it searches the extra card as well.
	*This will remove it from the JLayeredPane and the rack ArrayList.
	*@param	Discard		Card to be discarded from the Rack
	*@return					The Card that was discarded
	*/
	public Card discard(Card Discard)
	{
		Card D;
		if(Discard.getValue() == this.ExtraCard.getValue())
		{
			D = this.ExtraCard;
			this.RJLP.remove(this.ExtraCard);
			this.ExtraCard = null;
			repaint();
			return D;
		}
		else
		{
			int xoff = 25;
			int yoff = -12;
			int place = this.rack.indexOf(Discard);
			this.rack.remove(Discard);
			this.RJLP.remove(this.RJLP.getIndexOf(Discard));
			this.rack.add(place,this.ExtraCard);
			this.ExtraCard.setBounds(200+(xoff*place),130+(yoff*place),110,60);
			this.RJLP.add(this.ExtraCard,(10 - (place+1)));
			this.ExtraCard = null;
			return Discard;
		}

	}
	/**
	*Sets the Rack's extra Card, adds it to the ArrayList and the JLayeredPane
	*@param C			Card to add to the extra slot
	*/
	public void setExtra(Card C)
	{
		this.ExtraCard = C;
		C.setBounds(50,50,110,60);
		this.RJLP.add(C,new Integer(1));
	}
	/**
	*Gets the extra Card that is in the Rack.
	*@return			Card in the extra slot
	*/
	public Card getExtra()
	{
		return this.ExtraCard;
	}
	/**
	*Prints out the String representation of the Rack.
	*@return			Representation of the Rack.
	*/
	public String toString()
	{
		String result = "\n";
		for(int i = this.rack.size() - 1; i >= 0; i --)
		{
			result += this.rack.get(i) +"\n";
		}
		return result;
	}
	/**
	*Resets the order of the layers on the JLayeredPane of the Rack.
	*/
	public void reorder()
	{
		for(Card C:rack)
		{
			this.RJLP.setLayer(C, new Integer(-1));
		}

	}
	/**
	*Scores the rack based on the run from the first Card to the last.
	*It goes in sequential order from smallest to largest. 5 points per
	*card in order, 25 bonus points for having all 10 Cards in order.
	*@return			The score of the Rack.
	*/
	public int score()
	{
		int run = 0;
		int highestRun = 0;
		int score = 0;
		int least = 0;
		int high = 0;
		for(Card C: this.rack)
		{
			if(least < C.getValue())
			{
				least = C.getValue();
				run ++;
			}
			else
			{
				score = run * 5;
				if(score == 50)
				{
					score += 25;
				}
				return score;
			}
		}
		score = run *5;
		if(score == 50)
		{
			score+=25;
		}
		return score;
	}
	/**
	*Finds a Card at a certain position of the Rack.
	*@param pos			The position of the Card
	*@return				The Card at the position
	*/
	public Card search(int pos)
	{
		return this.rack.get(pos);
	}
	public void sortN(int n)
	{
		Collections.sort(this.rack.subList(0,n),new CardComparator());
		fixOrder();
		repaint();
	}
	/**
	*Fixes the order of the JLayeredPane after changes are made to the ArrayList.
	*/
	public void fixOrder()
	{
		int xOff = 25;
		int yOff = -12;
		for(Card C:rack)
		{
			this.RJLP.remove(C);
		}
		for(int i = 0; i < rack.size(); i ++)
		{
			this.rack.get(i).setBounds(200+(xOff*(i)),130+(yOff*(i)),110,60);
			this.RJLP.add(this.rack.get(i),new Integer(1));
		}
	}
}
