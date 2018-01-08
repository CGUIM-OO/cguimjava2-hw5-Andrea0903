import java.util.ArrayList;

public class Table {
public static final int MAXPLAYER =4;

private Deck deck;
private Player[] aplayer;
private Dealer aDealer;
private int[]pos_betArray;
private int nDecks;



private ArrayList<Card> playerCard;


public Table(int ndecks)
	{
		deck = new Deck(ndecks);
		aplayer = new Player[MAXPLAYER];
		playerCard = new ArrayList<Card>();
	}
public void set_player(int pos, Player p) {
	aplayer[pos]=p;
}
public Player[] get_player() 
{
	return aplayer;
}
public void set_dealer(Dealer d) 
	{
	aDealer = d;
	}
public Card get_face_up_card_of_dealer()
	{
	ArrayList<Card>oneRoundCard;
	oneRoundCard=aDealer.getOneRoundCard();
	return oneRoundCard.get(1);
	}
private void ask_each_player_about_bets()
	{pos_betArray=new int [ MAXPLAYER];
	for(int i=0;i<MAXPLAYER;i++)
		{
		aplayer[i].sayHello();
		pos_betArray[i]=aplayer[i].makeBet();
		}
	}
private void distribute_cards_to_dealer_and_players()
	{
	for(int i=0;i<MAXPLAYER;i++)
		{
		ArrayList<Card> playerCard=new ArrayList<Card>();
		playerCard.add(deck.getOneCard(true));
		playerCard.add(deck.getOneCard(true));
		aplayer[i].setOneRoundCard(playerCard);
		}
	ArrayList<Card>dealerCard = new ArrayList<Card>();
	dealerCard.add(deck.getOneCard(true));
	dealerCard.add(deck.getOneCard(true));
	aDealer.setOneRoundCard(dealerCard);
	System.out.print("Dealer's face up card is");
	Card a=get_face_up_card_of_dealer();
	a.printCard();
	}
private void ask_each_player_about_hits()
	{
	for(int i=0;i<MAXPLAYER;i++)
	{
		boolean hit= false;
		do {
			hit= aplayer[i].hit_me(this);
			if(hit)
			{
				ArrayList<Card>playerCard = new ArrayList<Card>();
				playerCard=((Person) aplayer[i]).getOneRoundCard();
				playerCard.add(deck.getOneCard(true));
				 aplayer[i].setOneRoundCard(playerCard);
				System.out.println("Hit!");
				System.out.print(aplayer[i].getName()+"'s Cards now:");
				for(Card a:aplayer[i].getOneRoundCard()) 
				{
					a.printCard();
				}
			}
			else
			{
				System.out.println(aplayer[i].getName()+",Pass hit!");
				System.out.println(aplayer[i].getName()+"'s hit is over!");
				System.out.println(aplayer[i].getName()+",Cards now:");
				for(Card a:aplayer[i].getOneRoundCard()) 
				{
					a.printCard();
				}
			}
		}while(hit);

	}
	}
private void ask_dealer_about_hits()
	{
	boolean hit=false;
	do {
		hit=aDealer.hit_me(this);
		if(hit)
		{
			ArrayList<Card>DealerCard = new ArrayList<Card>();
			DealerCard=aDealer.getOneRoundCard();
			DealerCard.add(deck.getOneCard(true));
			aDealer.setOneRoundCard(DealerCard);
			System.out.println("Hit!");
			System.out.println("Dealer's Cards now:");
			for(Card a:aDealer.getOneRoundCard()) 
			{
				a.printCard();
			}
		}
		else
		{
			System.out.println("Dealer's hit is over!");
			System.out.println("Dealer's Cards now:");
			for(Card a:aDealer.getOneRoundCard()) 
			{
				a.printCard();
			}
		}
		
	}while(hit);
	}
private void calculate_chips()
{
	int Chip;
	Chip=aDealer.getTotalValue();
	System.out.println("Dealer's card value is "+Chip+",Cards:");
	aDealer.printAllCard();
	for(int i=0;i<MAXPLAYER;i++)
	{
		aplayer[i].getTotalValue();
		System.out.println(aplayer[i].getName()+"'s Card:");
		aplayer[i].printAllCard();
		System.out.print(aplayer[i].getName()+"'s card value is "+aplayer[i].getTotalValue());
		if(aplayer[i].getTotalValue()<=21 && aDealer.getTotalValue()<21)
		{
			aplayer[i].increaseChips(+pos_betArray[i]);
			;
			System.out.print(",Get "+pos_betArray[i]+" Chips,the chips now is:");
		}
		else if(aplayer[i].getTotalValue()>21 && aDealer.getTotalValue()<=21)
		{
			aplayer[i].increaseChips(-pos_betArray[i]);
			System.out.print(",Loss "+pos_betArray[i]+" Chips,the chips now is:");
		}
		else if(aplayer[i].getTotalValue()>aDealer.getTotalValue() && aplayer[i].getTotalValue()<=21)
		{
			aplayer[i].increaseChips(+pos_betArray[i]);
			System.out.print(",Get "+pos_betArray[i]+" Chips,the chips now is:");
		}
		else if(aplayer[i].getTotalValue()<aDealer.getTotalValue() && aDealer.getTotalValue()<=21)
		{
			aplayer[i].increaseChips(-pos_betArray[i]);
			System.out.print(",Loss "+pos_betArray[i]+" Chips,the chips now is:");
		}
		else if(aplayer[i].getTotalValue()<=21 && aDealer.getTotalValue()>21)
		{
			aplayer[i].increaseChips(+pos_betArray[i]);
			System.out.print(",Get "+pos_betArray[i]+" Chips,the chips now is:");
		}
		else
		{
			System.out.print(",chips has no change"+pos_betArray[i]+"Chips,the chips now is: ");
		}
		System.out.println(aplayer[i].getCurrentChips());
	}
}
public int[] get_players_bet()
{
	return pos_betArray;
}
public void play()
{
	ask_each_player_about_bets();
	distribute_cards_to_dealer_and_players();
	ask_each_player_about_hits();
	ask_dealer_about_hits();
	calculate_chips();
}
	}
