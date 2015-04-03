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
import java.net.MalformedURLException;
import java.net.*;

public class PlayerTest extends JApplet implements ActionListener
{
  boolean win = false;
  Image image[] = new Image[2];
  ImageIcon imageIcons[] = new ImageIcon[2];
  ArrayList<Player> Players = new ArrayList<Player>();
  ArrayList<Card> Cards = new ArrayList<Card>();
  int currentTurn = 0;
  int phase; // This can be 1,2
  Deck Discard = new Deck();
  Deck Draw = new Deck();
  String Images[] = {"newblacksailscard.jpg","blacksailsback.jpg"};
  Card fromDiscard;

  boolean limitTurns;
  boolean oCheat;
  int turnLimit;
  int turns = 0;

  int numOrdered = 0;
  int orderLoc = 0;
  int turnLocation = 0;
  boolean showOpponent;

  ArrayList<String> cheatList = new ArrayList<String>();

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

    Players.add(new Human("You"));
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



    String response = JOptionPane.showInputDialog(null,"Enter the cheats you would like to use"
    ,"Enter cheats",JOptionPane.QUESTION_MESSAGE);
    String delims = " ";
    String[] cheats = response.split(delims);
    loadCheats(cheats);

    this.getCards();
    this.deal();

    Players.get(0).printRack();
    Players.get(1).printRack();
    Draw.top().addActionListener(this);

    phase = 1;
    this.setVisible(true);
  	this.validate();

    }
    public void actionPerformed(ActionEvent e)
    {
      //phase 1 is the draw phase
      if(phase == 1)
      {

        //If nobody owns the card, then it's in one of the piles
        if(((Card)e.getSource()).getOwner() == -1)
        {
          if(((Card)e.getSource()).getState() == true) //the card is from the discard pile
          {
            Players.get(currentTurn).pickupCard(Discard.draw());
            fromDiscard = Players.get(currentTurn).getRack().getExtra();
            fromDiscard.removeActionListener(this);
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
            Card C;
            C = ((Computer)Players.get(1)).getDiscard();
            C.doClick();
            C.setState(true);
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
          Players.get(currentTurn).getRack().reorder();
          if(fromDiscard != null)
          {
            fromDiscard.addActionListener(this);
          }

          phase = 1;
          turns ++;
          currentTurn = turns % this.Players.size();
          if(checkWin())
          {
            System.out.println("Somebody won!");
            win = true;
          }
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
            if(compChoice == 1)
            {
              Draw.top().doClick();
            }
            else
            {
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
      }
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
          if(i == 0)
          {
            this.Cards.get(count).setState(true);
          }
          if(i == 1)
          {
            if(showOpponent)
            {
              this.Cards.get(count).setState(true);
            }
            else
            {
              this.Cards.get(count).setState(false);
            }

            //The show opponent rack cheat can be checked here
          }
          R.addCard(this.Cards.get(count),new Integer(1));
          count++;
        }
      }
      //Populates the draw pile with the rest of the cards that weren't dealt to players
      for(int num = count; num < this.Cards.size(); num++)
      {
        Draw.addCard(this.Cards.get(num));
      }
      //sets player one as going first
      this.currentTurn = 0;
      if(oCheat)
      {
        Players.get(0).getRack().sortN(numOrdered);
      }
    }
    public boolean checkWin()
    {
      if(win)
      {
        return true;
      }
      for(Player P:Players)
      {
        if(P.getCurrentScore() == 75)
        {
          int dialogButton = JOptionPane.YES_NO_OPTION;
          int dialogResult = JOptionPane.showConfirmDialog(null,P.getName() + " won!! Would you like to play again?","Winner",dialogButton);
          if(dialogResult == JOptionPane.YES_OPTION)
          {
            try {
               this.getAppletContext().showDocument(new URL(getCodeBase()+"appletCaller.html"));
             }
            catch (MalformedURLException e) {
                System.out.println(e.getMessage());
             }
          }
          try
          {
            Thread.sleep(2500);
          }
          catch(Exception ex)
          {
            System.out.println("Thread.Sleep Exception. " + ex.getMessage());
          }
          return true;
        }
      }
      if(limitTurns)
      {
        if(this.turnLimit == this.turns)
        {
          for(Player P:Players)
          {
            System.out.println(P.getName()+" - Score: "+P.getCurrentScore());
          }
          String winner;
          if(Players.get(0).getCurrentScore() > Players.get(1).getCurrentScore())
          {
            winner = Players.get(0).getName();
          }
          else
          {
            winner = Players.get(1).getName();
          }
          int dialogButton = JOptionPane.YES_NO_OPTION;
          int dialogResult = JOptionPane.showConfirmDialog(null,winner + " won!! Would you like to play again?","Winner",dialogButton);
          if(dialogResult == JOptionPane.YES_OPTION)
          {
            try {
               this.getAppletContext().showDocument(new URL(getCodeBase()+"appletCaller.html"));
             }
            catch (MalformedURLException e) {
                System.out.println(e.getMessage());
             }
          }
          try
          {
            Thread.sleep(2500);
          }
          catch(Exception ex)
          {
            System.out.println("Thread.Sleep Exception. " + ex.getMessage());
          }
          return true;
        }
      }
      return false;
    }
    public void loadCheats(String []cheats)
  	{
  		for(String c:cheats)
  		{
  			cheatList.add(c);
        System.out.println(c);
  		}
  		  checkCheats();
  	}
    public void checkCheats()
    {
      if(cheatList.contains("/c"))
      {
        showOpponent = true;
      }
      else
      {
        showOpponent = false;
      }
      if(cheatList.contains("/n"))
      {
        limitTurns = true;
        turnLocation = cheatList.indexOf("/n") + 1;
        turnLimit = Integer.parseInt(cheatList.get(turnLocation));
        turnLimit *= 2;
      }
      else
      {
        limitTurns = false;
      }
      if(cheatList.contains("/o"))
      {
        oCheat = true;
        orderLoc = cheatList.indexOf("/o") + 1;
        numOrdered = Integer.parseInt(cheatList.get(orderLoc));
        if(numOrdered > 10)
        {
          numOrdered = 10;
        }
        else if(numOrdered < 0)
        {
          numOrdered = 0;
        }
      }
      else
      {
        oCheat = false;
      }
    }
  }
