/*Author: 			Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 		March 28, 2015
* Course: 			CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 		Test.java
* Purpose:		  	Testing class to test the classes as I go along
*/
import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Test extends JApplet implements ActionListener
{
  Image image[] = new Image[2];
  ImageIcon imageIcons[] = new ImageIcon[2];

  public void init()
  {
	setLayout(null);
	setSize(800,600);
    //Getting the image for the background of the cards
    String Images[] = {"PokemonCard.jpg","blacksailsback.jpg"};
    image[0] = getImage(getCodeBase(), Images[0]);
    imageIcons[0] = new ImageIcon(image[0]);

    image[1] = getImage(getCodeBase(), Images[1]);
    imageIcons[1] = new ImageIcon(image[1]);

    MediaTracker mt = new MediaTracker(this);
    showStatus("Loading Image ...");

    mt.addImage(image[0],1);
    try
    {
      mt.waitForID(1);
    }
    catch (InterruptedException e)
    {}

    showStatus("Loaded Image");
    //end getting images

		//start testing the infopane
    InfoPanel info = new InfoPanel("Rubizzle");
    this.add(info);
    info.setBounds(600,0,200,100);

		//Avatar panel
		JPanel Avatar = new JPanel();
		Avatar.setLayout(null);
		Avatar.setBackground(Color.BLACK);
		this.add(Avatar);
		Avatar.setBounds(600,400,200,200);
		this.setVisible(true);

  }
  public void actionPerformed(ActionEvent e)
  {
    System.out.println(e.getActionCommand());
  }
}
