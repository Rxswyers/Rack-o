/*Author: 			Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 		March 28, 2015
* Course: 			CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 		Rack.java
* Purpose:		  	This represents a Rack in the GUI. It is a JPanel that has a JLayeredPane on it.
					The cards are added to the JLayeredPane to allow overlapping.
*/
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
public class Rack extends JPanel
{
	JLayeredPane RJLP;
	ArrayList<Card> rack = new ArrayList<Card>();
	Card ExtraCard;
	public Rack()
	{
		this.RJLP = new JLayeredPane();
		this.add(this.RJLP);
		this.RJLP.setBounds(0,0,600,200);
		this.RJLP.setLayout(null);

	}
	public void addCard(Card C, int index)
	{
		C.setState(true);
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
			System.out.println("Discarding: " + D);
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
			System.out.println("Place in RJLP Calculated: "+ (10-(place+1)));
			System.out.println("Discarding: " + Discard);
			this.ExtraCard = null;
			return Discard;
		}

	}
	public int score()
	{
		return 1;
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
	//Name:
	//Parameters:
	//Returns:
	//Description:
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
}
