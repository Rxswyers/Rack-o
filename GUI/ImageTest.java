/*Author: 			Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 		March 28, 2015
* Course: 			CSC243
* Professor Name: 	Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 		ImageTest.java
* Purpose:		  	Testing class to test the classes as I go along
*/
import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ImageTest extends JApplet implements ActionListener
{
  Image image[] = new Image[2];
  ImageIcon imageIcons[] = new ImageIcon[2];

  public void init()
  {
	setLayout(null);
	setSize(800,600);
    //Getting the image for the background of the cards
    String Images[] = {"newblacksailscard.jpg","blacksailsback.jpg"};
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
    ArrayList<Card> testing = new ArrayList<Card>();
    Card C = new Card(Images[0],imageIcons[0],5);
    C.setState(true);
    Card C1 = new Card(Images[0],imageIcons[0],7);
    C1.setState(true);
    Card C2 = new Card(Images[0],imageIcons[0],3);
    C2.setState(true);
    Card C3 = new Card(Images[0],imageIcons[0],6);
    C3.setState(true);
    testing.add(C1);
    testing.add(C);
    testing.add(C2);
    testing.add(C3);
    System.out.println(testing);
    Collections.sort(testing.subList(0,3),new CardComparator());
    System.out.println(testing);

    this.setVisible(true);

  }
  public void actionPerformed(ActionEvent e)
  {
    System.out.println(e.getActionCommand());
  }
}
