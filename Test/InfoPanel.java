/*Author: 					Ruben Swyers
* Creation Date: 		April 27, 2015
* Due Date: 				May 7, 2015
* Course: 					CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 			#3 - Racko GUI
* Filename: 				InfoPanel.java
* Purpose:		  		This shows the name and if the Player that it belongs to is
                    having their turn or not.
*/
import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
Shows the name of a Player, and if they are having their turn at the moment.
*/
public class InfoPanel extends JPanel
{
  /**
  *Name label
  */
  JLabel Name;
  /**
  *Label that shows when the Player is going
  */
  JLabel Going;
  /**
  *Boolean to set if the Player is going or not
  */
  boolean going;
  /**
  *Constructs an InfoPanel
  *@param n     Name to be shown on the panel.
  */
  public InfoPanel(String n)
  {
      this.Name = new JLabel("Name: " + n);
      this.Going = new JLabel("");
      this.going = false;
      this.add(Name);
      this.add(Going);
      this.Name.setBounds(10,10,190,15);
      this.Going.setBounds(10,45,190,15);
      this.setLayout(null);
      this.setBorder(BorderFactory.createLineBorder(Color.black));
      setOpaque(false);
  }
  /**
  *Tells the panel if it's the Player's turn or not
  *@param  s    true if not going, false if going
  */
  public void setState(boolean s)
  {
    this.going = s;
    if(s)
    {
      this.Going.setText("");
    }
    else
    {
      this.Going.setText("It is this player's turn.");
    }
  }
}
