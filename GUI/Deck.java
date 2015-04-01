/*Author: 			Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 		March 28, 2015
* Course: 			CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 		Deck.java
* Purpose:		  	This represents a Deck in the GUI. It is a JPanel that has a JLayeredPane on it.
					When a card is added, it gets added to the JLayeredPane.
*/
import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.util.ArrayList;

public class Deck extends JPanel
{
	//members
	JLayeredPane DJLP;
	int numCards;
	ArrayList<Card> Cards;
	public Deck()
	{
		this.Cards = new ArrayList<Card>();
		this.DJLP = new JLayeredPane();
		this.add(this.DJLP);
		this.DJLP.setBounds(0,0,200,200);
		this.DJLP.setLayout(null);
		this.numCards = 0;
	}
	//methods
			//public void addCard(Card C, int index)
	//public void addCard(Card C, int index, int x, int y)
	public void addCard(Card C)
	{
		numCards++;
		int off = 2;
		//C.setBounds(x,y,110,60);
		C.setBounds(20+(off*this.Cards.indexOf(C)),20+(off*this.Cards.indexOf(C)),110,60);
		C.setOwner(-1);
		this.Cards.add(C);
		this.DJLP.add(C,new Integer(1));
		this.fixOrder();

	}
	public void fixOrder()
	{
		int off = 2;
		for(Card C:Cards)
		{
			this.DJLP.remove(C);
		}
		//for(int i = this.Cards.size() - 1; i>=0; i --)
		//for(int i = 0; i < this.Cards.size(); i++)
		for(int i = this.Cards.size() - 1; i>=0; i --)
		{
			this.Cards.get(i).setBounds(20+(off*(i+1)),20+(off*(i+1)),110,60);
			this.DJLP.add(this.Cards.get(i),new Integer(1));
		}
	}
	public void reorder()
	{
		for(int i = 0; i < this.Cards.size(); i++)
		{
			this.DJLP.setLayer(this.Cards.get(i), new Integer(-1));
		}
	}
	public Card draw()
	{

		this.DJLP.remove(this.Cards.size()-1);
		Card C = this.Cards.remove(this.Cards.size()-1);
		this.fixOrder();
		return C;
	}
	public Card top()
	{
		Card C = null;
		if(this.Cards.isEmpty())
		{
			return C;
		}
		else
		{
			return this.Cards.get(this.Cards.size()-1);
		}
	}
	public boolean empty()
	{
		return this.Cards.isEmpty();
	}
	/*
	public int getSize()
	{
		return Pile.size();
	}

	public Card getTop()
	{
		if(this.empty())
		{
			return new Card(0);
		}
		else
		{
			return this.Pile.peek();
		}
	}

	public String toString()
	{
		String result = "";
		//Printed out the stack without popping anything from it
		//in order to do so, you need to treat it as a vector
		for(int i = Pile.size() - 1; i >= 0; i--)
		{
			result += "" + Pile.get(i).toString() + "\n";
		}
		return result;
	}
*/
}
