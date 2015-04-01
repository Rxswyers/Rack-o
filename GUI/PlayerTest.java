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

public class PlayerTest extends JApplet implements ActionListener
{
  Image image[] = new Image[2];
  ImageIcon imageIcons[] = new ImageIcon[2];
  ArrayList<Player> Players = new ArrayList<Player>();
  int currentTurn = 0;
  int phase; // This can be 1,2
  Deck Discard = new Deck();
  Deck Draw = new Deck();
  ArrayList<Card> Cards = new ArrayList<Card>();
  String Images[] = {"newblacksailscard.jpg","blacksailsback.jpg"};
  boolean limitTurns;
  int turns = 0;

  public void init()
  {
	setLayout(null);
	setSize(800,600);
    //Getting the image for the background of the cards

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

    Players.add(new Human("Ruben"));
    Players.add(new Computer("Comp"));
    Players.get(0).getRack().setBounds(0,400,800,200);
    Players.get(1).getRack().setBounds(0,0,800,200);
    this.add(Players.get(0).getRack());
    this.add(Players.get(1).getRack());
    Players.get(0).getRack().setLayout(null);
    Players.get(1).getRack().setLayout(null);
    //Setting up the draw and discard
    Draw.setBounds(150,200,200,200);
    this.add(Draw);
    Draw.setLayout(null);

    Discard.setBounds(350,200,200,200);
    this.add(Discard);
    Discard.setLayout(null);
    this.getCards();
    this.deal();
    /*
    Card CTest = new Card(Images[0],imageIcons[0],30);
    //CTest.addActionListener(this);

    Card CTest2 = new Card(Images[0],imageIcons[0],29);
    CTest2.addActionListener(this);
    CTest.setState(false);
    CTest2.setState(false);
    CTest.setOwner(-1);
    CTest2.setOwner(-1);

    int drawOffset = 2;
    Draw.addCard(CTest);
    Draw.addCard(CTest2);
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
    Discard.addCard(DTest);
    Discard.addCard(DTest2);
    //end setting up the discard pile
    currentTurn = 0;
    phase = 1;
    //setting up a player to test

    //generating the cards on the rack of the Player (Human)
    Rack R = Players.get(0).getRack();
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
      R.addCard(C,new Integer(1));
    }
    Players.get(0).printRack();
    //End Player one's rack
    */
    Players.get(0).printRack();
    Players.get(1).printRack();
    Draw.top().addActionListener(this);
    if(phase == 2)
    {
      System.out.println("Phase 2 has started");
    }
    phase = 1;
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
          if(((Card)e.getSource()).getState() == true) //the card is from the discard pile
          {
            Players.get(currentTurn).pickupCard(Discard.draw());
            if(!Discard.empty())
            {
              Discard.top().addActionListener(this);
            }

          }
          else
          {
            Players.get(currentTurn).pickupCard(Draw.draw());

            if(!Draw.empty())
            {
              Draw.top().addActionListener(this);
            }
          }
          checkDeck();
          phase = 2;
          if(currentTurn == 1)
          {
            //ActionEvent E = new ActionEvent(((Computer)Players.get(1)).getDiscard(),ActionEvent.ACTION_PERFORMED,null);
            Card C;
            C = ((Computer)Players.get(1)).getDiscard();
            C.doClick();
            //Discard.addCard(C);
            //System.out.println(((Computer)Players.get(1)).getDiscard());
            //Players.get(1).getRack().getExtra().doClick();
          }
        }
      }
      else if(phase == 2)
      {
        int compChoice = 1;
        //If nobody owns the card, then it's in one of the piles
        if(((Card)e.getSource()).getOwner() == -1) //the person is trying to draw from a deck again
        {
          System.out.println("You already drew a card");
        }
        else if(((Card)e.getSource()).getOwner() == currentTurn) //the card belongs to player 1
        {

          if(!Discard.empty())
          {
            Discard.top().removeActionListener(this);
          }

          Discard.addCard(Players.get(currentTurn).chooseDiscard(((Card)e.getSource())));
          Players.get(currentTurn).printHand();
          Players.get(currentTurn).getRack().reorder();

          phase = 1;
          turns ++;
          currentTurn = turns % this.Players.size();
          if(currentTurn == 1)
          {
            if(!Discard.empty())
            {
              compChoice = Players.get(1).choosePile(Discard.top());
            }
            else
            {
              compChoice = 1;
            }
            System.out.println("CompChoice: "+compChoice);
            if(compChoice == 1)
            {
              //ActionEvent A = new ActionEvent(Draw.top(),ActionEvent.ACTION_PERFORMED,null);
              Draw.top().doClick();
              System.out.println("Should have drawn from the draw pile");
            }
            else
            {
              //ActionEvent A = new ActionEvent(Discard.top(),ActionEvent.ACTION_PERFORMED,null);
              Discard.top().doClick();
            }
          }
        }
      }

    }
    public void switchDecks()
    {
      ArrayList<Card> TempCards = new ArrayList<Card>();
      while(!Discard.empty())
      {
        TempCards.add(Discard.draw());
      }
      //Shuffle the cards before they go into the drawPile
      Collections.shuffle(TempCards);
      for(Card C: TempCards)
      {
        C.setState(false);
        C.removeActionListener(this);
        Draw.addCard(C);
      }
      Draw.top().addActionListener(this);
      Discard.repaint();
    }
    public void checkDeck()
    {
      if(Draw.empty())
      {
        this.switchDecks();
      }
    }
    public void getCards()
    {
      int cardSize = 0;
      switch(this.Players.size())
      {
        case 2:
        cardSize = 40;
        break;
        case 3:
        cardSize = 50;
        break;
        case 4:
        cardSize = 60;
        break;
      }
      for(int i = 1; i <= cardSize; i ++)
      {
        this.Cards.add(new Card(Images[0],imageIcons[0],i));
        //C = new Card(Images[0],imageIcons[0],i);
      }
      /*
      C = new Card(Images[0],imageIcons[0],i);
      C.setState(true);
      C.setOwner(0);
      C.addActionListener(this);
      C.setActionCommand(Integer.toString(i));
      C.setBounds(200+(xOffset * (i-1)),130+(yOffset*(i-1)),110,60);
      C.setActionCommand("Place in RJLP: "+(10-i));
      R.addCard(C,new Integer(1));
      */
      Collections.shuffle(this.Cards);
    }
    public void deal()
    {
      //Used to keep track of the amount of cards that are dealt
      int count = 0;
      Rack R;
      int xOffset = 25;
      int yOffset = -12;
      //Deals 10 cards to each player
      for(int i = 0; i < this.Players.size(); i++)
      {
        R = this.Players.get(i).getRack();
        for(int j = 1; j < 11; j++)
        {
          this.Cards.get(count).addActionListener(this);
          this.Cards.get(count).setOwner(i);
          this.Cards.get(count).setActionCommand(""+this.Cards.get(count).getValue());
          this.Cards.get(count).setBounds(200+(xOffset*(j-1)),130+(yOffset*(j-1)),110,60);
          R.addCard(this.Cards.get(count),new Integer(1));
          count++;
        }
      }
      //Used for debugging
      /*
      System.out.println(this.Players.get(0).getName() +"'s hand");
      this.Players.get(0).printHand();
      System.out.println(this.Players.get(1).getName() +"'s hand");
      this.Players.get(1).printHand();
      */
      //Populates the draw pile with the rest of the cards that weren't dealt to players
      for(int num = count; num < this.Cards.size(); num++)
      {
        Draw.addCard(this.Cards.get(num));
      }
      //sets player one as going first
      this.currentTurn = 0;
    }
    public boolean checkWin()
    {
      for(Player P:Players)
      {
        if(P.getCurrentScore() == 75)
        {
          System.out.println(P.getName() + " won!!");
          return true;
        }
      }
      if(limitTurns)
      {
        if(this.turns == currentTurn)
        {
          for(Player P:Players)
          {
            System.out.println(P.getName()+" Score: "+P.getCurrentScore());
          }
          return true;
        }
      }
      return false;
    }
  }
