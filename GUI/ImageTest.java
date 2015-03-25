import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageTest extends JApplet implements ActionListener
{
  Image image[] = new Image[2];
  ImageIcon imageIcons[] = new ImageIcon[2];

  public void init()
  {
	setLayout(new CardLayout(0,0));
	setSize(800,600);
	
    String Images[] = {"blacksailscard.jpg","blacksailsback.jpg"};
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
	
	Rack R = new Rack();
	add(R);
	R.setLayout(new CardLayout(0,0));
	Card C;
	int xOffset = 20;
	int yOffset = -20;
	int last = 10;
	for (int i = 1; i < 11; i ++)
	{
		C = new Card(Images[0],imageIcons[0],i);
		C.addActionListener(this);
		C.setActionCommand(Integer.toString(i));
		C.setBounds(120+(xOffset * (i-1)),320+(yOffset*(i-1)),95,140);
		R.addCard(C,new Integer(10 - i));
	}

  }
  public void actionPerformed(ActionEvent e)
  {
    System.out.println(e.getActionCommand());
  }
}
