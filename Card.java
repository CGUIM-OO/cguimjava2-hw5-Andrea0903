
 public class Card {

	        enum Suit{Clubs, Diamonds,Hearts,Spades}
			public Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
			private int rank; //1~13
			private String [] ranka={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
			/**
			 * @param k suit
			 * @param r rank
			 */
			public Card(Suit k,int r)
			{
				suit=k;
				rank=r;
			}	
		







	public void printCard()
	{
		
		if(suit==Suit.Clubs)
		{
			System.out.println(Suit.Clubs+","+ranka[rank-1]);
		}
		if(suit==Suit.Diamonds)
		{
			System.out.println(Suit.Diamonds+","+ranka[rank-1]);
		}
		if(suit==Suit.Hearts)
		{
			System.out.println(Suit.Hearts+","+ranka[rank-1]);
		}
		if(suit==Suit.Spades)
		{
			System.out.println(Suit.Spades+","+ranka[rank-1]);
		}


		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace

	}
	public Suit getSuit()
	{
		return suit;
	}
	public int getRank()
	{
		return rank;
	}
 }
