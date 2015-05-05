/*Author: 			  Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 		  April 3, 2015
* Course: 			  CSC243
* Professor Name: Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 		  Racko.java
* Purpose:		  	This is the applet that controls the game. The draw Deck is face down on the left.
                  and the discard Deck is on the right. Your Rack is on the bottom and the opponent's is
                  on the top.
*/
import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.net.MalformedURLException;
import java.net.*;
import javax.swing.border.Border;
/**
*This applet controls the game of Racko. There are 2 players, you and a computer opponent.
*When the game first starts you can put cheats in the input dialog box. You will play until you
* or the computer get all of the cards in your racks in order.
*@author Ruben Swyers
*/
public class Racko extends JApplet implements ActionListener
{
  /**
  *Keeps track of the state of the game
  */
  boolean win = false;
  /**
  *Holds the images used in the game
  */
  Image image[] = new Image[2];
  /**
  *Holds the image icons used in the game
  */
  ImageIcon imageIcons[] = new ImageIcon[2];
  /**
  *Keeps track of the players of the game
  */
  ArrayList<Player> Players = new ArrayList<Player>();
  /**
  *Keeps track of the Cards
  */
  ArrayList<Card> Cards = new ArrayList<Card>();
  /**
  *The current player
  */
  int currentTurn = 0;
  /**
  *Phase 1 is Draw phase, and Phase 2 is Discard phase
  */
  int phase; // This can be 1,2
  /**
  *Discard Deck
  */
  Deck Discard = new Deck();
  /**
  *Draw Deck
  */
  Deck Draw = new Deck();
  /**
  *Location of the images
  */
  String Images[] = {"PokemonCardN.jpg","blacksailsback.jpg"};
  /**
  *Card to be kept track of to avoid drawing from the discard and discarding it again
  */
  Card fromDiscard;
  /**
  *Enables or disables the /n cheat
  */
  boolean limitTurns;
  /**
  *Enables or disables the /o cheat
  */
  boolean oCheat;
  /**
  *Keeps track of the turn limit if limitTurns is enabled
  */
  int turnLimit;
  /**
  *Keeps track of the number of turns
  */
  int turns = 0;
  /**
  *Number of cards to start that are ordered in the Human Rack(if the cheat is enabled)
  */
  int numOrdered = 0;
  /**
  *The location of the numOrdered in cheatList
  */
  int orderLoc = 0;
  /**
  *The location of the turnLimit in cheatList
  */
  int turnLocation = 0;
  /**
  *Enables or disables the /c cheat
  */
  boolean showOpponent;
  /**
  *List of cheats once they are parsed
  */
  ArrayList<String> cheatList = new ArrayList<String>();
  Border oldB;
  Card highlightC;
  /**
  *Starts the applet up
  */
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
    //sets up the players and their racks
    Players.add(new Human("You"));
    Players.add(new Computer("Comp"));
    Players.get(0).getRack().setBounds(0,400,600,200);
    Players.get(0).getInfoPane().setBounds(600,0,200,100);
    Players.get(1).getRack().setBounds(0,0,600,200);
    Players.get(1).getInfoPane().setBounds(600,100,200,100);
    this.add(Players.get(0).getRack());
    this.add(Players.get(0).getInfoPane());
    this.add(Players.get(1).getRack());
    this.add(Players.get(1).getInfoPane());
    Players.get(0).getRack().setLayout(null);
    Players.get(1).getRack().setLayout(null);
    //Setting up the draw and discard
    Draw.setBounds(150,200,200,200);
    this.add(Draw);
    Draw.setLayout(null);

    Discard.setBounds(350,200,200,200);
    this.add(Discard);
    Discard.setLayout(null);


    //asks for cheats
    String response = JOptionPane.showInputDialog(null,"Enter the cheats you would like to use"
    ,"Enter cheats",JOptionPane.QUESTION_MESSAGE);
    String delims = " ";
    String[] cheats = response.split(delims);
    loadCheats(cheats);

    this.getCards();
    this.deal();

    Draw.top().addActionListener(this);

    phase = 1;
    this.setVisible(true);
  	this.validate();

  }
    /**
    *Handles any interaction with the Cards on the GUI. This is also where
    * the players take their turns based on what Cards they click.
    *@param e     ActionEvent triggered by clicking a Card.
    */
    public void actionPerformed(ActionEvent e)
    {
      //phase 1 is the draw phase
      if(phase == 1)
      {
        System.out.println("Phase 1");
        //handles the computer's draw phase
        if(Players.get(currentTurn) instanceof Computer)
        {
          handleComputerP1();
        }
        //If nobody owns the card, then it's in one of the piles
        if(((Card)e.getSource()).getOwner() == -1)
        {
          if(((Card)e.getSource()).getState() == true) //the card is from the discard pile
          {
            //handles the human clicking a card from the discard pile
            Players.get(currentTurn).pickupCard(Discard.draw());
            fromDiscard = Players.get(currentTurn).getRack().getExtra();
            //makes sure you don't pick up from the discard pile and discard it again
            fromDiscard.removeActionListener(this);
            if(!Discard.empty())
            {
              //only make the top card able to be clicked
              Discard.top().addActionListener(this);
            }

          }
          else //the card is from the draw pile
          {

            //handles the human clicking a card on the draw pile
            Players.get(currentTurn).pickupCard(Draw.draw());

            if(!Draw.empty())
            {
              //only make the top card able to be clicked
              Draw.top().addActionListener(this);
            }
          }
          //check to see if the draw pile is empty, if it is it is switched with the discard pile
          checkDeck();
          //move to the discard phase
          phase = 2;

        }
      }
      //discard phase
      else if(phase == 2)
      {

        System.out.println("Phase 2");
        //If nobody owns the card, then it's in one of the piles
        if(((Card)e.getSource()).getOwner() == -1) //the person is trying to draw from a deck again
        {
          System.out.println("You already drew a card");
        }
        else if(((Card)e.getSource()).getOwner() == currentTurn) //the card belongs to player the player that owns it
        {
          //Make sure that you can't do anything to the card underneath of the one that will be discarded
          if(!Discard.empty())
          {
            Discard.top().removeActionListener(this);
          }
          //Put the Card in the discard pile
          Discard.addCard(Players.get(currentTurn).chooseDiscard(((Card)e.getSource())));
          Discard.top().setState(true);
          Players.get(currentTurn).getRack().reorder();
          //reenables the card from the discard pile if the action listener was removed
          if(fromDiscard != null)
          {
            fromDiscard.addActionListener(this);
          }
          //puts you back to the draw phase
          phase = 1;
          //completes a turn
          turns ++;
          //gets the next player
          currentTurn = turns % this.Players.size();
          if(Players.get(currentTurn) instanceof Computer)
          {
                  Timer timer = new Timer(1000, new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {


                        handleComputerP1();

                  }
                     });
                     timer.setRepeats(false);
                     timer.setCoalesce(true);
                     timer.start();
            //handleComputerP1();
          }
          //checks to see if anyone won yet
          if(checkWin())
          {
            System.out.println("Somebody won!");
            win = true;
          }
          //Let's everyone know who's turn it is
          switchInfo();


        }

      }

    }
    public void handleComputerP1()
    {
      int compChoice = 1;
      if(!Discard.empty())
      {
        compChoice = Players.get(1).choosePile(Discard.top());
        System.out.println(Discard.top());
        System.out.println("Computer's choice " + compChoice);
      }
      else
      {
        compChoice = 1;
      }
      if(compChoice == 1)
      {
        //emulates the computer clicking on the draw pile
        System.out.println("Pickup from draw");
        //check to see if the draw pile is empty, if it is it is switched with the discard pile
        checkDeck();
        Players.get(1).pickupCard(Draw.draw());
        if(!Draw.empty())
        {
          //only make the top card able to be clicked
          Draw.top().addActionListener(this);
        }
        //move to the discard phase
        phase = 2;
        Timer timer = new Timer(1000, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {


              handleComputerP2();

        }
           });
           timer.setRepeats(false);
           timer.setCoalesce(true);
           timer.start();
      }
      else
      {
        System.out.println("Pickup from discard");
        //check to see if the draw pile is empty, if it is it is switched with the discard pile
        checkDeck();
        try{
          System.out.println("Sleepin");
          Thread.sleep(500);

        }catch(InterruptedException ev)
        {
          System.out.println("Can't sleep");
        }
        Players.get(1).pickupCard(Discard.draw());
        //move to the discard phase
        phase = 2;
        Timer timer = new Timer(1000, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {


              handleComputerP2();

        }
           });
           timer.setRepeats(false);
           timer.setCoalesce(true);
           timer.start();
        //emulates the computer clicking on the discard pile
        //Discard.top().doClick();
        System.out.println("After click");
      }
    }
    public void handleComputerP2()
    {
      Card C;
      C = ((Computer)Players.get(1)).getDiscard();
      highlight(Players.get(1).getRack().getExtra());
      C.doClick();
      System.out.println("Compt p2");
    }
    public void highlight(Card C)
    {
      oldB = C.getBorder();
      highlightC = C;
      C.setBorder(BorderFactory.createLineBorder(Color.green, 2));
      try{
        System.out.println("Sleepin");
        Thread.sleep(500);

      }catch(InterruptedException ev)
      {
        System.out.println("Can't sleep");
      }
      Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            highlightC.setBorder(oldB);

      }
        });
        timer.setRepeats(false);
        timer.setCoalesce(true);
        timer.start();


    }
    /**
    *Switches the Draw and Discard Decks if the Draw Deck is empty.
    */
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
    /**
    *Checks to see if the Draw Deck is empty, if it is, it will switch the decks
    */
    public void checkDeck()
    {
      if(Draw.empty())
      {
        this.switchDecks();
      }
    }
    /**
    *Gets the appropriate amount of Cards compared to the amount of players
    * 40 Cards for 2 players, 50 for 3, and 60 for 4
    */
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
    /**
    *Deals the Cards to the players and the decks.
    */
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
    /**
    *Checks to see if any of the players won, or if the turn limit was set it will end the game
    * and ask if you want to play again.
    *@return      <code>true</code> if there is a winner or the game ended, <code>false</code> if not
    */
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
          //alerts the user if there is a winner, and asks to play again
          int dialogButton = JOptionPane.YES_NO_OPTION;
          int dialogResult = JOptionPane.showConfirmDialog(null,P.getName() + " won!! Would you like to play again?","Winner",dialogButton);
          if(dialogResult == JOptionPane.YES_OPTION)
          {
            try {
              //restarts the applet
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
      //checks for the limitTurns cheat
      if(limitTurns)
      {
        if(this.turnLimit == this.turns)
        {
          //prints the scores of the players to the command line
          for(Player P:Players)
          {
            System.out.println(P.getName()+" - Score: "+P.getCurrentScore());
          }
          String winner;
          //gets the winner's name
          if(Players.get(0).getCurrentScore() > Players.get(1).getCurrentScore())
          {
            winner = Players.get(0).getName();
          }
          else
          {
            winner = Players.get(1).getName();
          }
          //Shows who won and asks to play again
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
    /**
    *Loads the cheats into the cheatList and checks for the three cheats that are implemented
    *@param cheats      Array of cheats from the initial inputDialog
    */
    public void loadCheats(String []cheats)
  	{
      //converts it to an arrayList to make it easier to check to see if a cheat is there
  		for(String c:cheats)
  		{
  			cheatList.add(c);
  		}
  		  checkCheats();
  	}
    /**
    *Checks for the cheats in the cheatList and processes them as well
    */
    public void checkCheats()
    {
      if(cheatList.contains("/c"))
      {
        showOpponent = true;
        ((Computer)Players.get(1)).setSight(showOpponent);
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
    /**
    Updates the InfoPanels on the right side to notify who's turn it is
    */
    public void switchInfo()
    {
        InfoPanel next = Players.get((currentTurn +1) % this.Players.size()).getInfoPane();
        InfoPanel prev = Players.get(currentTurn).getInfoPane();
        //Players.get(currentTurn).getInfoPane().setState(false);
        //Players.get((currentTurn + 1) % this.Players.size()).getInfoPane().setState(true);
        prev.setState(false);
        next.setState(true);
        //swap the index of Infos, using the currentplayer as the index
        // that will be turns % Players.size()
        Rectangle Bounds = new Rectangle();
        Bounds = next.getBounds();
        //Puts the next InfoPanel on the top
        next.setBounds(600,100,200,100);
        //Swaps with the previous player's InfoPanel
        prev.setBounds(Bounds);
    }
  }
