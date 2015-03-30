/*Author: 			Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 		March 28, 2015
* Course: 			CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 		Card.java
* Purpose:		  	This represents a Card in the game. It is a JButton that uses an image,
					and has a tool tip to display the value
*/
import javax.swing.*;
import java.awt.*;
import java.applet.*;
/**
*The Card class represents a Card in the GUI. It is a subclass of JButton.
*The Card has a state member that will determine if it is face up or down.
*If the state is true, it is face up and displays the value. If it's false
*then the back of the Card is shown(an image). This class will be the
*building block for most of the other classes in this project.
*@author	Ruben Swyers
*/
public class Card extends JButton
{
	//members
	private int value;
	private boolean state; //true - flipped up, false - face down
	private ImageIcon Pic;
	private String picFile;
	private int owner;

	//methods

	//constructor
	/**
	*@param	iconName	Location of the image to use
	*@param	img				The image to use for the background
	*@param	v 				The value of the Card
	*/
	public Card(String iconName, ImageIcon img, int v)
	{
		super(iconName,img);
		this.Pic = img;
		this.picFile = iconName;
		this.value = v;
		this.state = false;
		setVisible(true);
	}

	//gets
	/**
	*Gets the value of the Card
	*@return		the value of the Card
	*/
	public int getValue()
	{
		return this.value;
	}
	/**
	*Gets the state of the Card
	*@return		the value of the state of a Card
	*/
	public boolean getState()
	{
		return this.state;
	}
	/**
	*Gets the owner of the Card
	*@return		the owner of the Card
	*/
	public int getOwner()
	{
		return this.owner;
	}

	//sets
	/**
	*Sets the value of the Card
	*@param v 	the value that is desired
	*/
	public void setValue(int v)
	{
		this.value = v;
	}
	/**
	*Sets the state of the Card
	*@param	s		<code>true</code> to set it face up,
	*						<code>false</code> to set it face down
	*/
	public void setState(boolean s)
	{
		this.state = s;
	}
	/**
	*Sets the owner of the Card
	*@param	o		owner of the Card
	*/
	public void setOwner(int o)
	{
		this.owner = o;
	}
	//other methods
	/**
	*String representation of the Card
	*@return		Depending on the state of the Card
	*						<code>true</code> the card will be
	*						face up and displaying a value,
	*						<code>false</code> the card will
	*						be face down and an image will be
	*						displayed.
	*/
	public String toString()
	{
		String s ="";
		if(this.getState())
			s += this.getValue(); //face up
		else
			s+= "X";//face down;
		return s;
	}
	/**
	*If the state is <code>true</code>, the button will
	*be white and display the value of the Card. <code>false</code>
	* will display a background image instead.
	*@param	g		the <code>Graphics</code> object to use
	*/
	public void paintComponent(Graphics g)
	{
		//if the state is true
		if(this.getState())
		{
			int fontSize = 16;
			g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
			g.setColor(Color.white);
			g.fillRect(0,0,110,60);
			g.setColor(Color.black);
			g.drawString(""+this.getValue(), 88, 20);
			this.setToolTipText(Integer.toString(this.value));

		}
		else
		{
			//Draws the image properly scaled
			this.setToolTipText(null);
			ImageDrawer.drawScaledImage(this.Pic.getImage(), this, g);
		}

	}
}
