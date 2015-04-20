import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
Shows the name of a Player, and if they are having their turn at the moment.
*/
public class InfoPanel extends JPanel
{
  /**
  Name label
  */
  JLabel Name;
  JLabel Going;
  /**
  Constructs an InfoPanel
  */
  public InfoPanel(String n)
  {
      this.Name = new JLabel("Name: " + n);
      //this.Name.setText(n);
      //this.Going.setText("It is this player's turn.");
      this.Going = new JLabel("It is this player's turn.");
      this.add(Name);
      this.add(Going);
      this.Name.setBounds(10,10,190,15);
      this.Going.setBounds(10,45,190,15);
      this.setLayout(null);
      this.setBorder(BorderFactory.createLineBorder(Color.black));
  }
}
