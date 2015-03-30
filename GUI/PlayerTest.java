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

public class PlayerTest extends JApplet implements ActionListener
{
  Image image[] = new Image[2];
  ImageIcon imageIcons[] = new ImageIcon[2];
  Player Players[] = new Player[2];
  int currentTurn;
  int phase; // This can be 1,2
  Deck Discard = new Deck();
  Deck Draw = new Deck();
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

    //Setting up the draw pile
    //Deck Draw = new Deck();
    Draw.setBounds(150,200,200,200);
    this.add(Draw);
    Draw.setLayout(null);

    Card CTest = new Card(Images[0],imageIcons[0],30);
    //CTest.addActionListener(this);

    Card CTest2 = new Card(Images[0],imageIcons[0],29);
    CTest2.addActionListener(this);
    CTest.setState(false);
    CTest2.setState(false);
    CTest.setOwner(-1);
    CTest2.setOwner(-1);

    int drawOffset = 2;
    Draw.addCard(CTest,1,20,20);
    Draw.addCard(CTest2,2,22,22);
    //end setting up the draw pile

    //Setting up the discard pile
    //Deck Discard = new Deck();
    Discard.setBounds(350,200,200,200);
    this.add(Discard);
    Discard.setLayout(null);

    Card DTest = new Card(Images[0],imageIcons[0],27);
    //DTest.addActionListener(this);

    Card DTest2 = new Card(Images[0],imageIcons[0],28);
    DTest2.addActionListener(this);
    DTest.setOwner(-1);
    DTest2.setOwner(-1);

    DTest.setState(true);
    DTest2.setState(true);
    Discard.addCard(DTest,1,20,20);
    Discard.addCard(DTest2,2,22,22);
    //end setting up the discard pile
    currentTurn = 0;
    phase = 1;
    //setting up a player to test
    Players[0] = new Human("Ruben");
    //generating the cards on the rack of the Player (Human)
    Rack R = Players[0].getRack();
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
      C.setOwner(0);
      C.addActionListener(this);
      C.setActionCommand(Integer.toString(i));
      C.setBounds(200+(xOffset * (i-1)),130+(yOffset*(i-1)),110,60);
      C.setActionCommand("Place in RJLP: "+(10-i));
      R.addCard(C,new Integer(10 - i));
    }
    Players[0].printRack();
    //End Player one's rack
    if(phase == 2)
    {
      System.out.println("Phase 2 has started");
    }

    this.setVisible(true);
  	this.validate();

    }
    public void actionPerformed(ActionEvent e)
    {
      System.out.println(e.getActionCommand());
      System.out.println(((Card)e.getSource()).getOwner());
      System.out.println("Phase: " + phase);
      //phase 1 is the draw phase
      if(phase == 1)
      {
        //If nobody owns the card, then it's in one of the piles
        if(((Card)e.getSource()).getOwner() == -1)
        {
          Players[currentTurn].pickupCard(((Card)e.getSource()));
          phase = 2;
        }
      }
      else if(phase == 2)
      {
        //If nobody owns the card, then it's in one of the piles
        if(((Card)e.getSource()).getOwner() == -1) //the person is trying to draw from a deck again
        {
          System.out.println("You already drew a card");
        }
        else if(((Card)e.getSource()).getOwner() == 0) //the card belongs to player 1
        {
          Players[0].chooseDiscard(((Card)e.getSource()));
          Discard.addCard(((Card)e.getSource()),3,24,24);
          Players[0].printHand();
          Players[0].getRack().reorder();
          phase = 1;
        }
      }

    }
  }
