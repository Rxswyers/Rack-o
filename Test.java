import java.util.ArrayList;
public class Test
{
	public static void main(String args[])
	{
		Rack P1Rack = new Rack();
		Card C1 = new Card(1);
		Card C2 = new Card(2);
		Card C3 = new Card(3);
		
		P1Rack.addCard(C1);
		P1Rack.addCard(C2);
		P1Rack.addCard(C3);
		
		
		System.out.println("Removing " + P1Rack.discard(2));
		P1Rack.show();
	}
}