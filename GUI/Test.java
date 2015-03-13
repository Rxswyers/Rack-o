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
		System.out.println(P1Rack);

		Player P1 = new Player();
		Player P2 = new Player();
		Player P3 = new Player();
		P1.pickupCard(C1);
		P1.pickupCard(C2);

		P1.printHand();

		Deck Pickup = new Deck();

		Pickup.addCard(C1);
		Pickup.addCard(C2);

		System.out.println(Pickup.getSize());
		System.out.println(Pickup.draw());
		System.out.println(Pickup.getSize());

		Racko R = new Racko();
		R.addPlayer(P1);
		R.addPlayer(P2);
		R.addPlayer(P3);
		R.getCards();

	}
}
