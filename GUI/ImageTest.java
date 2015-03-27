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
	
    String Images[] = {"blacksailscard.jpg","blacksailsback.jpg"};
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
	Deck D = new Deck();
	D.setBounds(10,10,300,300);
	this.add(D);
	D.setLayout(null);
	
	
	//Card Cs[] = new Card[2];
	
	Card CTest = new Card(Images[0],imageIcons[0],30);
	CTest.addActionListener(this);
	
	Card CTest2 = new Card(Images[0],imageIcons[0],29);
	CTest2.addActionListener(this);
	D.addCard(CTest,1,20,20);
	D.addCard(CTest2,2,22,22);
	
	Rack R = new Rack();
	R.setBounds(0,300,500,300);
	this.add(R);
	R.setLayout(null);
	
	Card C;
	int xOffset = 40;
	int yOffset = -15;
	int last = 10;
	for (int i = 1; i < 11; i ++)
	{
		C = new Card(Images[0],imageIcons[0],i);
		C.addActionListener(this);
		C.setActionCommand(Integer.toString(i));
		C.setBounds(120+(xOffset * (i-1)),120+(yOffset*(i-1)),95,140);
		R.addCard(C,new Integer(10 - i));
	}
	this.setVisible(true);
	this.validate();
  }
  public void actionPerformed(ActionEvent e)
  {
    System.out.println(e.getActionCommand());
  }
}
