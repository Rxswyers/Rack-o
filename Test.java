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
		
		ArrayList<int> testList = new ArrayList<int>();
		
		testList.add(1);
		testList.add(3);
		
		//testing an arrayList for the purpose of Racko.deal()
		//will it print the other 8 null, or will it just print 2 things?
		//this is good to test, because there will only be 2 players at first, but there can be up to 4
		for(int i = 0; i < testList.size(); i ++)
		{
			System.out.println(testList[i]);
		}
	}
}
