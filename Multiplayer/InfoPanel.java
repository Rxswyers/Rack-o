import java.util.*;
import javax.swing.*;
import java.awt.*;
/**

*/
public class InfoPanel extends JPanel
{
  /**
  Name label
  */
  JLabel Name;
  /**
  */
  public InfoPanel(String n)
  {
      this.Name.setText(n);
      this.add(Name);
      this.Name.setBounds(10,10,190,15);
      this.setLayout(null);
      this.setBorder(BorderFactory.createLineBorder(Color.black));
  }
}
