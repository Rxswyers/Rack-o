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
	public Card discard(Card C1, Card C2)
	{
		return C1;
	}
	public int score()
	{
		return 1;
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
}
