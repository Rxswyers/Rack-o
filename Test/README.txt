Cheats
  /c - Shows the opponent's hand
  /o - Orders the first n amount of cards in your rack to start.
       use "/o n" when entering cheats, n being a number
  /n - limits the number of turns, allowing each player to go once per turn.

  In order to use cheats, add them in the name textbox of the HTML Form.
  If you added a cheat, you will get a confirmation in the applet in the form of
  hearing pikachu.

It will take 3 seconds from load for the background music to start, if you want to stop
it, then use the context menu.

Since my design only allows 2 Racks to be shown at all times, when playing with
more than 2 people the Computer Racks will swap out. It may not seem like they do,
but if you use the /c cheat you can verify.

There is a bug, it rarely happens. A computer may pick a card and the Applet doesn't register it
as the computer doing it. It will fall onto you, then make it unable to continue.

These are notes as I progressed through my project:

I've decided to add on to my GUI, because there is a lot of extra space not being used.
I'm thinking that making a panel that displays the user's name and if it is their
turn or not on the right side of the GUI. Each player will have one, and if it is their
turn, then their rack will be shown as well as have their info panel bumped up to the top.
I've yet to decide if I would like to just make the racks invisible and then make them
visible when it's their turn, or just to paint the racks off of the view of the applet
and then swap the bounds when it's their turn. The info panels are a little easier, because
they are all shown at once, so I can just swap the bounds and it looks fine.

I chose to add the InfoPanels in a copy of the Racko.java and test it with only 2 players to start.
It appears that there was a bug that didn't happen in my command line version of the game, which used
the same code as the GUI. The Computer kept only drawing from the draw pile in the GUI, but drew from both
in the command line. I had to debug and eventually tracked the error in the Computer class.

I tested a way to get info from a form and put it into an applet, it took a little bit to get the right
stuff, but it now can work when I decide to add it in.

I'm now going to work on changing the status of the InfoPanel depending on who's turn it is, then
get into swapping the racks (eventually add a button to view any player's rack on the InfoPanel)

I noticed that the logic behind taking turns was slightly out of wack, I will have to redo taking
turns. The bug was in Racko, it was also a good opportunity to add the swapping of InfoPanels. The next
step is to switch the Racks. This is also the first attempt at adding more than 2 players. I'll have to
see if painting the Racks outside of the applet and then swapping the bounds when it's the Player's turn.
The Racks only need to switch when the person going is a Computer, otherwise it will display the Human's Rack
in place of the opponent's. Making an internet Player a subclass of Computer will work with this very nicely.
When I fixed the bug, I also made the opponent's discard to be highlighted for a little bit.

Once I get the Racks to swap automatically, I will try to add a button in the InfoPanel to swap the Racks at will.
First I had to get the InfoPanels swapping correctly with 3 Players, now I will test swapping the Racks.
The Racks and InfoPanels now properly switch, there was a bug at first when trying to swap right after the
Human goes. I didn't want to swap the Human's rack, so I didn't update the others while the Human went.
Painting the Racks off the view of the Applet worked great. The racks all update properly, and when it's your
turn, the rack that went before you is showing.

Adding music was pretty easy, and so was the context menu. I used the context menu to control the music, you have
play and stop items on the context menu.

Because I stuck with my original plan to swap Racks and InfoPanels, it unfortunately took up more time than I thought
it would. Animations may not be implemented due to that ):
