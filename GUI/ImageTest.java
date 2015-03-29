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

public class ImageTest extends JApplet implements ActionListener
{
  Image image[] = new Image[2];
  ImageIcon imageIcons[] = new ImageIcon[2];

  public void init()
  {
	setLayout(null);
	setSize(800,600);

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
  //Setting up the draw pile
	Deck Draw = new Deck();
	Draw.setBounds(150,200,200,200);
	this.add(Draw);
	Draw.setLayout(null);

	Card CTest = new Card(Images[0],imageIcons[0],30);
	CTest.addActionListener(this);

	Card CTest2 = new Card(Images[0],imageIcons[0],29);
	CTest2.addActionListener(this);
  CTest.setState(false);
  CTest2.setState(false);
  
  int drawOffset = 2;
	Draw.addCard(CTest,1,20,20);
	Draw.addCard(CTest2,2,22,22);
  //end setting up the draw pile

  //Setting up the discard pile
  Deck Discard = new Deck();
  Discard.setBounds(350,200,200,200);
  this.add(Discard);
  Discard.setLayout(null);

  Card DTest = new Card(Images[0],imageIcons[0],27);
  DTest.addActionListener(this);

  Card DTest2 = new Card(Images[0],imageIcons[0],28);
  DTest2.addActionListener(this);

  DTest.setState(true);
  DTest2.setState(true);
  Discard.addCard(DTest,1,20,20);
  Discard.addCard(DTest2,2,22,22);
  //end setting up the discard pile

  //generating the cards on the rack
	Rack R = new Rack();
	R.setBounds(0,400,800,200);
	this.add(R);
	R.setLayout(null);


	Card C;
	int xOffset = 25;
	int yOffset = -12;
	int last = 10;
	for (int i = 1; i < 11; i ++)
	{
		C = new Card(Images[0],imageIcons[0],i);
    C.setState(true);
		C.addActionListener(this);
		C.setActionCommand(Integer.toString(i));
    C.setBounds(200+(xOffset * (i-1)),130+(yOffset*(i-1)),110,60);
		R.addCard(C,new Integer(10 - i));
	}
  // end rack generation
  R.printStuff();

	this.setVisible(true);
	this.validate();

  }
  public void actionPerformed(ActionEvent e)
  {
    System.out.println(e.getActionCommand());
  }
}
