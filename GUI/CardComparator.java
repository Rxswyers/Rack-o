public class CardComparator implements Comparator<Card>
{
  public int compare(Card C1,Card C2)
  {
    return C1.getValue().compareTo(C2.getValue());
  }
}
