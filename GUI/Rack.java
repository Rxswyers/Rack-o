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
	
	public Rack()
	{
		this.RJLP = new JLayeredPane();
		this.add(this.RJLP);
		this.RJLP.setBounds(0,0,600,300);
		this.RJLP.setLayout(null);
		
	}
	public void addCard(Card C, int index)
	{
		this.RJLP.add(C,new Integer(index));
	}
}
