Name: Ruben Swyers - Project #2
Javadocs -  http://rxswyers.github.io/Rack-o.

Cheats
  /c - Shows the opponent's hand
  /o - Orders the first n amount of cards in your rack to start.
       use "/o n" when entering cheats, n being a number
  /n - limits the number of turns, allowing each player to go once per turn.

  Cheats are asked for in an inputDialog as soon as the game starts, you can use up to
  all three at once.

There is an occasional bug where the computer will pick up a card for you from the deck,
but it rarely happens.

Your rack is the one on the bottom, the Draw deck is to the left, and the Discard deck is on the
right. The opponent's rack is on the top.

The goal of the game is to get your rack in order from least to greatest. The bottom left most card
is where the smaller card goes. If you get your rack in order before the computer, you win. You will
be going first. In order to start, you just click on the Draw deck and the card you picked up will
be displayed on the left of your rack. You must then choose a discard, you must click on the card
you want to discard. After your first turn you can pick up from the discard pile as well, but
you cannot discard a card that you picked up from the discard pile.

If someone wins, a dialogbox will inform who won and ask if you'd like to play again. If you choose
to play again, the page that calls the applet will be reloaded.

I went a little far as to create a Comparator specifically for comparing cards, so that I could
use Collections.sort on an ArrayList of cards. I also had to make Card implement Comparable in order
to do so.

The applet replaces my Racko class in the first program, so it will control everything now. Making it so
the applet only keeps track of ActionListeners was really the way to go. At this time, the game is only
meant for two players. One human and one computer player.
