/*Author: 				Ruben Swyers
* Creation Date: 	April 2, 2015
* Due Date: 			May 7, 2015
* Course: 				CSC243
* Professor Name: Dr. Spiegel
* Assignment: 		#3 - Racko GUI
* Filename: 			CardComparator.java
* Purpose:		  	This is a custom Comparator to make Cards sortable while in an
*                 ArrayList
*/
import java.util.*;
/**
*Comparator specifically made to compare two Cards.
*@author  Ruben Swyers
*/
public class CardComparator implements Comparator<Card>
{
  /**
  *Compares 2 Cards.
  *@param C1    Card to compare
  *@param C2    Card to compare to C1
  *@return      a negative integer if C2 is less than, zero if they are equal, and
  * a positive integer if C2 is greater.
  */
  public int compare(Card C1,Card C2)
  {
    return C1.compareTo(C2);
  }
}
