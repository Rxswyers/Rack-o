import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Test extends JFrame implements WindowListener, ActionListener
{
	//members
	Button B;
	ArrayList<Button> Bs = new ArrayList<Button>();
	public Test(String s)
	{
		super(s);
		JPanel panel = new JPanel(new BorderLayout(5,5));

		setSize(700,600);
		panel.setBounds(0,0,800,600);
		addWindowListener(this);
		char letter = 'A';
		int xOff = 50;
		int yOff = 50;
		for(int i = 0; i < 2; i ++)
		{
			for(int j = 0; j < 13; j ++)
			{
				B = new Button(Character.toString(letter));
				B.setBounds(25 + (xOff * j),415 + (yOff *i),45,40);
				this.add(B);
				Bs.add(B);
				B.addActionListener(this);
				letter ++;
			}
		}
		//for(Button button:Bs)
		//{
			//button.setEnabled(false);
		//}

		add(panel);
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
		System.out.println("Worked "  + ((Button)event.getSource()).getActionCommand());
		((Button)event.getSource()).setEnabled(false);
	}
	public static void main(String []args)
	{
		new Test("Title");
	}
}
