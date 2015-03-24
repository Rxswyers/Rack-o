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
	
	JPanel panel = new JPanel();
	add(panel);
	panel.setLayout(new CardLayout(0,0));
	
	JLayeredPane jlp = new JLayeredPane();
	panel.add(jlp);
	jlp.setLayout(null);
	
    Card C = new Card(Images[0],imageIcons[0],25);
    C.addActionListener(this);	
	C.setBounds(40,400,95,140);
	jlp.add(C,new Integer(3));
	
	Card C2 = new Card (Images[0],imageIcons[0],20);
	C2.addActionListener(this);
	C2.setBounds(60,380,95,140);
	jlp.add(C2,new Integer(2));
	
	Card C3 = new Card (Images[0],imageIcons[0],19);
	C3.addActionListener(this);
	C3.setBounds(80,360,95,140);
	jlp.add(C3, new Integer(1));
  }
  public void actionPerformed(ActionEvent e)
  {
    System.out.println(e.getActionCommand());
  }
}
