/*Author: 				Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 			April 3, 2015
* Course: 				CSC243
* Professor Name: Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 			Deck.java
* Purpose:		  	This represents a Deck in the GUI. It is a JPanel that has a JLayeredPane on it.
									When a card is added, it gets added to the JLayeredPane.
*/
import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.util.ArrayList;
/**
*Holds all of the cards that the players don't have. It's a subclass of JPanel
* and has a JLayeredPane. The cards are stacked on top of each other at a slight angle.
*/
public class Deck extends JPanel
{
	//members
	/**
	*Allows the Cards to be layed on top of each other at an angle.
	*/
	JLayeredPane DJLP;
	/**
	*Keeps track of the number of Cards
	*/
	int numCards;
	/**
	*Holds all of the Cards in the Deck
	*/
	ArrayList<Card> Cards;
	/**
	*Sets up the Deck
	*/
	public Deck()
	{
		this.Cards = new ArrayList<Card>();
		this.DJLP = new JLayeredPane();
		this.add(this.DJLP);
		this.DJLP.setBounds(0,0,200,200);
		this.DJLP.setLayout(null);
		this.numCards = 0;
		setOpaque(false);
	}
	//methods
	/**
	*Adds a Card to the Deck and correctly sets the position on the JLayeredPane.
	*@param C				Card to be added.
	*/
	public void addCard(Card C)
	{
		numCards++;
		int off = 2;
		C.setBounds(20+(off*this.Cards.indexOf(C)),20+(off*this.Cards.indexOf(C)),110,60);
		C.setOwner(-1);
		this.Cards.add(C);
		this.DJLP.add(C,new Integer(1));
		this.fixOrder();

	}
	/**
	*Fixes the order of the Deck once a Card is added.
	*/
	public void fixOrder()
	{
		int off = 2;
		for(Card C:Cards)
		{
			this.DJLP.remove(C);
		}
		for(int i = this.Cards.size() - 1; i>=0; i --)
		{
			this.Cards.get(i).setBounds(20+(off*(i+1)),20+(off*(i+1)),110,60);
			this.DJLP.add(this.Cards.get(i),new Integer(1));
		}
	}
	/**
	*Reorders the layers of the JLayeredPane to the correct spot
	*/
	public void reorder()
	{
		for(int i = 0; i < this.Cards.size(); i++)
		{
			this.DJLP.setLayer(this.Cards.get(i), new Integer(-1));
		}
	}
	/**
	*Removes the top card and fixes the order
	*@return				Card that is removed
	*/
	public Card draw()
	{

		this.DJLP.remove(this.Cards.size()-1);
		Card C = this.Cards.remove(this.Cards.size()-1);
		this.fixOrder();
		repaint();
		return C;
	}
	/**
	*Gets the top Card of the Deck without removing it
	*@return				Top of the Deck, if it's empty it returns a null Card
	*/
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
	/**
	*Checks to see if the Deck is empty
	*@return				<code>true</code> if it's empty, <code>false</code> if it isn't
	*/
	public boolean empty()
	{
		return this.Cards.isEmpty();
	}
}
