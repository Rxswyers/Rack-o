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
turns.
