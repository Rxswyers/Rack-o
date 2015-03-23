import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Test extends JFrame implements WindowListener, ActionListener
{
	//members
	Button B;

	public Test(String s)
	{
		super(s);
		JPanel panel = new JPanel(new BorderLayout(5,5));

		setSize(250,200);
		panel.setSize(250,250);
		addWindowListener(this);
		B = new Button("Testing");
		panel.add("East",B);
		B.addActionListener(this);
		getContentPane().add(panel);
		setVisible(true);
	}
	public void windowClosed(WindowEvent event) {}
	public void windowDeiconified(WindowEvent event) {}
	public void windowIconified(WindowEvent event) {}
	public void windowActivated(WindowEvent event) {}
	public void windowDeactivated(WindowEvent event) {}
	public void windowOpened(WindowEvent event) {}
	public void windowClosing(WindowEvent event) {System.exit(0);}

	public void actionPerformed(ActionEvent event)
	{
		System.out.println("poop");
	}
	public static void main(String []args)
	{
		new Test("Title");
	}
}
