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

public class Card extends JButton
{
	//members
	private int value;
	private boolean state; //true - flipped up, false - face down
	private ImageIcon Pic;
	private String picFile;

	//methods

	//constructor
	public Card(String iconName, ImageIcon img, int v)
	{
		super(iconName,img);
		this.Pic = img;
		this.picFile = iconName;
		this.value = v;
		this.state = false;
		this.setToolTipText(Integer.toString(this.value));
		setSize(210,225);
		setVisible(true);
	}

	//gets
	public int getValue()
	{
		return this.value;
	}
	public boolean getState()
	{
		return this.state;
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

	//other methods
	public String toString()
	{
		String s ="";
		if(this.getState())
			s += this.getValue(); //face up
		else
			s+= "X";//face down;
		return s;
	}
	public void paintComponent(Graphics g)
	{
		//Draws the image properly scaled
		ImageDrawer.drawScaledImage(this.Pic.getImage(), this, g);
		//g.drawImage(Pic.getImage(),1,1,this);
	}
}
