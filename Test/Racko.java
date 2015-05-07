/*Author: 			  Ruben Swyers
* Creation Date: 	March 15, 2015
* Due Date: 		  May 7, 2015
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
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
/**
*This applet controls the game of Racko. There are 2 players, you and a computer opponent.
*When the game first starts you can put cheats in the input dialog box. You will play until you
* or the computer get all of the cards in your racks in order.
*@author Ruben Swyers
*/
public class Racko extends JApplet implements ActionListener, MouseListener
{
  /**
  *Keeps track of the state of the game
  */
  boolean win = false;
  /**
  *Holds the images used in the game
  */
  Image image[] = new Image[3];
  /**
  *Holds the image icons used in the game
  */
  ImageIcon imageIcons[] = new ImageIcon[3];
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
  String Images[] = {"PokemonCardN.jpg","PokemonBack.jpg","pika2.png"};
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
  /**
  *Keeps track of the original border of a button, used when highlighting it
  */
  Border oldB;
  /**
  *Card to be highlighted
  */
  Card highlightC;
  /**
  *Card that the computer picks in their turn
  */
  Card compPick;
  /**
  *Holds the parameters from the HTML form
  */
  HashMap<String,String> parmsMap;
  /**
  *Number of players, attained from the HTML form
  */
  int numP;
  /**
  *Contains the information from the name textbox in the HTML form, can also
  *have cheats.
  */
  String response;
  /**
  *Popup menu to control the music
  */
  JPopupMenu pop = new JPopupMenu();
  /**
  *Menu item to play the music
  */
  JMenuItem play = new JMenuItem("Play");
  /**
  *Menu item to stop the music
  */
  JMenuItem stop = new JMenuItem("Stop");
  /**
  *Menu item to pick the other song
  */
  JMenuItem other = new JMenuItem("Other Song");
  /**
  *Holds the component that the popup menu appeared on
  */
  Component cmp = null;
  /**
  *Background music
  */
  AudioClip backMusic;
  /**
  *Keeps track of what song is playing
  */
  Boolean otherSong = false;
  /**
  *Starts the applet up
  */
  public void init()
  {
	  setLayout(null);
    setSize(800,600);
    //sets up the context menu
    pop.add(play);
    pop.add(stop);
    pop.add(other);
    play.addActionListener(this);
    stop.addActionListener(this);
    other.addActionListener(this);
    //sets up the background music
    String music = "opening.wav";
    backMusic = getAudioClip(getCodeBase(), music);

    //Getting the image for the background of the cards

    image[0] = getImage(getCodeBase(), Images[0]);
    imageIcons[0] = new ImageIcon(image[0]);

    image[1] = getImage(getCodeBase(), Images[1]);
    imageIcons[1] = new ImageIcon(image[1]);

    image[2] = getImage(getCodeBase(), Images[2]);
    imageIcons[2] = new ImageIcon(image[2]);

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
    setContentPane(new ImagePanel(image[1]));

    //gets the info from the form on the web page
    try {
      cutURL();
      //dumpMap(parmsMap);
      numP = Integer.parseInt(parmsMap.get("numPRadios"));
      response = parmsMap.get("name");
      System.out.println("NumP is " + numP);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    String delims = " ";
    String[] cheats = response.split(delims);
    //end getting images

    //handles making the players
    Players.add(new Human(cheats[0],0));
    Players.get(0).getRack().setBounds(0,400,600,200);
    Players.get(0).getInfoPane().setBounds(600,0,200,100);
    this.add(Players.get(0).getRack());
    this.add(Players.get(0).getInfoPane());
    Players.get(0).getRack().setLayout(null);
    for(int i = 1; i < numP; i ++)
    {
      Players.add(new Computer(("Comp"+i),i));
      Players.get(i).getRack().setBounds(0+((i-1)*800),0,600,200);
      Players.get(i).getInfoPane().setBounds(600,0+(i*100),200,100);
      add(Players.get(i).getRack());
      add(Players.get(i).getInfoPane());
      Players.get(i).getRack().setLayout(null);
      ((Computer)Players.get(i)).setNumPlayers(numP);
    }

    //loads up the cheats
    loadCheats(cheats);
    //Setting up the draw and discard
    Draw.setBounds(150,200,200,200);
    this.add(Draw);
    Draw.setLayout(null);

    Discard.setBounds(350,200,200,200);
    this.add(Discard);
    Discard.setLayout(null);
    //deals the cards
    this.getCards();
    this.deal();
    //sets up the top of the draw pile, so it can be clicked
    Draw.top().addActionListener(this);

    phase = 1;
    //enales the frame to use the context menu
    this.addMouseListener(this);
    //waits 3 seconds to start the background music
    Timer timer = new Timer(3000, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
          backMusic.loop();
    }
       });
    timer.setRepeats(false);
    timer.setCoalesce(true);
    timer.start();
    this.setVisible(true);
  	this.validate();

  }
    /**
    *Handles any interaction with the Cards as well as the popup menu on the GUI. This is also where
    * the players take their turns based on what Cards they click.
    *@param e     ActionEvent triggered by clicking a Card.
    */
    public void actionPerformed(ActionEvent e)
    {
      if((e.getSource() == play) && (cmp != null))
      {
        backMusic.loop();
        return;
      }
      if((e.getSource() == stop) && (cmp != null))
      {
        backMusic.stop();
        return;
      }
      if(((e.getSource()) == other) && (cmp != null))
      {
        if(otherSong == false)
        {
          backMusic.stop();
          String music = "battle.wav";
          backMusic = getAudioClip(getCodeBase(), music);
          backMusic.loop();
          otherSong = true;
          return;
        }
        else
        {
          backMusic.stop();
          String music = "opening.wav";
          backMusic = getAudioClip(getCodeBase(), music);
          backMusic.loop();
          otherSong = false;
          return;
        }
      }


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
          if(checkWin())
          {
            System.out.println("Somebody won!");
            win = true;
          }
          //puts you back to the draw phase
          phase = 1;
          //completes a turn
          turns ++;
          if(Players.size() > 2)
          {
            switchInfo();
          }

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
          }
          //checks if anyone won
          if(checkWin())
          {
            System.out.println("Somebody won!");
            win = true;
          }
        }
      }

    }
    /**
    *Handles a Computer's phase 1 of their turn
    */
    public void handleComputerP1()
    {
      int compChoice = 1;
      if(!Discard.empty())
      {
        compChoice = Players.get(currentTurn).choosePile(Discard.top());
        System.out.println(Discard.top());
        System.out.println("Computer's choice " + compChoice);
        System.out.println("Comp's name " + Players.get(currentTurn).getName());
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
        Players.get(currentTurn).pickupCard(Draw.draw());
        //Players.get(currentTurn).getRack().getExtra().addActionListener(this);
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
        Players.get(currentTurn).pickupCard(Discard.draw());
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
        System.out.println("After click");
      }
    }
    /**
    *Handles a Computer's phase 2 of their turn.
    */
    public void handleComputerP2()
    {
      Card C;
      compPick = ((Computer)Players.get(currentTurn)).getDiscard();
      highlight(compPick);
      Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            compPick.doClick();

      }
        });
        timer.setRepeats(false);
        timer.setCoalesce(true);
        timer.start();
      System.out.println("Compt p2");
    }
    /**
    *Highlights a Card for 1.5 seconds after a Computer player picks a Card.
    *@param C         Card to be highlihgted
    */
    public void highlight(Card C)
    {
      oldB = C.getBorder();
      highlightC = C;
      C.setBorder(BorderFactory.createLineBorder(Color.green, 2));
      Timer timer = new Timer(1500, new ActionListener() {
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
          if(Players.get(i) instanceof Human)
          {
            this.Cards.get(count).setState(true);
          }
          if(Players.get(i) instanceof Computer)
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
          String message = "";
          message += (P.getName() + " won!\n");
          //prints the scores of the players to the command line
          for(Player Pl:Players)
          {
            message+= (Pl.getName()+" - Score: "+Pl.getCurrentScore()+'\n');
          }
          JOptionPane.showMessageDialog(this,message);
          return true;
        }
      }
      //checks for the limitTurns cheat
      if(limitTurns)
      {
        String message = "";
        if(this.turnLimit == this.turns)
        {
          String winner = "";
          Player winna;
          int highScore = 0;
          //gets the winner's name
          for(Player Pl:Players)
          {
            if(highScore < Pl.getCurrentScore())
            {
              highScore = Pl.getCurrentScore();
              winner = Pl.getName();
            }
          }
          message += (winner + " won!\n");
          //prints the scores of the players to the command line
          for(Player P:Players)
          {
            message+= (P.getName()+" - Score: "+P.getCurrentScore()+'\n');
          }
          JOptionPane.showMessageDialog(this,message);
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
      if(cheatList.size() > 1)
      {
        AudioClip audioClip;
        String pika = "pikachu.wav";

        audioClip = getAudioClip(getCodeBase(), pika);
        audioClip.play();
      }
      if(cheatList.contains("/c"))
      {
        showOpponent = true;
        System.out.println("interesting");
        for(Player play: Players)
        {
          if(play instanceof Computer)
          {
            ((Computer)play).setSight(showOpponent);
            System.out.println("worked");
          }
        }
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
    Updates the InfoPanels on the right side to notify who's turn it is and swaps
    Racks
    */
    public void switchInfo()
    {
      Player nextP = Players.get((currentTurn+1)%this.Players.size());
      Player prevP = Players.get(currentTurn);

      InfoPanel next = Players.get((currentTurn+1) % this.Players.size()).getInfoPane();
      InfoPanel prev = Players.get(currentTurn).getInfoPane();

      for(Player play: Players)
      {
          play.getInfoPane().setState(true);
      }
      next.setState(false);
      //swap the bounds of Infos, using the currentplayer as the index
      // that will be turns % Players.size()
      Rectangle oldBounds = new Rectangle();
      oldBounds = next.getBounds();
      //swaps the Racks
      if((nextP instanceof Computer) && (prevP instanceof Computer) )
      {
        Rectangle oldRBounds = new Rectangle();
        oldRBounds = nextP.getRack().getBounds();

        nextP.getRack().setBounds(0,0,600,200);
        prevP.getRack().setBounds(800,0,600,200);
        nextP.getRack().repaint();
        System.out.println("Rack is showing " + prevP.getName());
      }
      //Handles skipping the Human's rack from being disturbed
      else if((nextP instanceof Computer) && (prevP instanceof Human))
      {
        Rectangle oldRBounds = new Rectangle();
        oldRBounds = nextP.getRack().getBounds();
        nextP.getRack().setBounds(0,0,600,200);
        Players.get((currentTurn +2)%this.Players.size()).getRack().setBounds(oldRBounds);
        nextP.getRack().repaint();
      }
      //Puts the next InfoPanel on the top
      next.setBounds(600,0,200,100);
      //Swaps with the previous player's InfoPanel
      prev.setBounds(oldBounds);
    }
    /**
    *Cuts out the base URL and throws the parameters into a hash map
    */
    public void cutURL() throws UnsupportedEncodingException {
      String completeURL = getDocumentBase().toString();
      System.out.println("Complete URL: " + completeURL);
      int i = completeURL.indexOf("?");
      if (i > -1) {
         String searchURL = completeURL.substring(completeURL.indexOf("?") + 1);
         System.out.println("Search URL: " + searchURL);
         initMap(searchURL);
      }
    }
    /**
    *Makes a hash map of the information from the HTML form
    *@param search        info from the HTML form
    */
    public void initMap(String search) throws UnsupportedEncodingException
    {
      parmsMap = new HashMap<String,String>();
      String params[] = search.split("&");

      for (String param : params) {
         String temp[] = param.split("=");
         parmsMap.put(temp[0], java.net.URLDecoder.decode(temp[1], "UTF-8"));
      }
    }
    /**
    *Custom handler for dealing with the context menu, it will display the menu
    *where the user right clicks
    *@param  ev     MouseEvent that made a mouse pressed or mouse released event
    */
    private void popup(MouseEvent ev)
    {
      int x = 0;
      int y = 0;
      if(ev.isPopupTrigger())
      {
        cmp = ev.getComponent();
        x = ev.getX();
        y = ev.getY();
        pop.show(cmp,x,y);
      }
    }
    /**
    *Handles the mouse being left clicked, will launch the custom handler for
    *the popup menu
    */
    public void mousePressed(MouseEvent ev)
    {
      popup(ev);
    }
    /**
    *Handles the mouse being left clicked, will launch the custom handler for
    *the popup menu. This was added for OS differences.
    */
    public void mouseReleased(MouseEvent ev)
    {
      popup(ev);
    }
    public void mouseClicked(MouseEvent ev) {}
    public void mouseEntered(MouseEvent ev) {}
    public void mouseExited(MouseEvent ev) {}
  }
