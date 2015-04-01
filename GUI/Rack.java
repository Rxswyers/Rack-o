/*Author: 					Ruben Swyers
* Creation Date: 		March 15, 2015
* Due Date: 				April 1, 2015
* Course: 					CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 			#2 - Racko GUI
* Filename: 				Rack.java
* Purpose:		  		This represents a Rack in the GUI. It is a JPanel that has a JLayeredPane on it.
					The cards are added to the JLayeredPane to allow overlapping.
*/
import java.util.ArrayList;
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
	
	public void printStuff()
	{
		for(int i = this.rack.size() - 1; i >= 0; i --)
		{
			System.out.println(this.rack.get(i));
		}
	}
	public Card find(int v)
	{
		return rack.get(v);
	}
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
	public void setExtra(Card C)
	{
		this.ExtraCard = C;
		C.setBounds(50,50,110,60);
		this.RJLP.add(C,new Integer(1));
	}
	public Card getExtra()
	{
		return this.ExtraCard;
	}
	public String toString()
	{
		String result = "\n";
		for(int i = this.rack.size() - 1; i >= 0; i --)
		{
			result += this.rack.get(i) +"\n";
		}
		return result;
	}
	public void reorder()
	{
		for(Card C:rack)
		{
			this.RJLP.setLayer(C, new Integer(-1));
		}

	}
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
	public Card search(int pos)
	{
		return this.rack.get(pos);
	}
	public void reset()
	{
		this.rack.clear();
		this.RJLP.removeAll();
		this.ExtraCard = null;
	}
}
