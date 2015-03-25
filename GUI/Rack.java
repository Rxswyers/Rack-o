import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
public class Rack extends JPanel
{
	JLayeredPane RJLP;
	
	public Rack()
	{
		this.RJLP = new JLayeredPane();
		this.add(this.RJLP);
		this.RJLP.setLayout(null);
		
	}
	public void addCard(Card C, int index)
	{
		this.RJLP.add(C,new Integer(index));
	}
}
