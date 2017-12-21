import java.util.ArrayList;
import java.util.Random;

public class Deck {  
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard=new ArrayList<Card>();//記得要初始化
	private ArrayList<Card> openCard=new ArrayList<Card>();//新增打開的牌
	public int nUsed=0;
	int ndeck;
	
	public Deck(int nDeck){ //constructor
		cards=new ArrayList<Card>();
		for(int n=0;n<nDeck;n++){
			for(Card.Suit k:Card.Suit.values()){
				for(int r=1;r<14;r++){
					Card card=new Card(k,r);
					cards.add(card);	
				}
			}
		}
		shuffle();//洗牌
	}	
	
	public void printDeck(){
		for(Card card:cards){
			card.printCard();
		}
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public void shuffle(){
		Random rnd=new Random();
		for(int i=0;i<cards.size();i++) {
			int n =rnd.nextInt(cards.size());
			Card ChangeCard =cards.get(n);
			cards.set(n, cards.get(i));
			cards.set(i, ChangeCard);
		}
			usedCard.clear();
			nUsed=0;
			openCard.clear();
		
	}
	public Card getOneCard(boolean isOpened)
	{
		if (nUsed==ndeck*52)//if all the cards are used then shuffle.
    	{
    		shuffle();
    		if(isOpened)
    		{
    			openCard.add(cards.get(nUsed));
    		}
    	}
    	Card temp=cards.get(0); //get the first card in the array cards. 
    	cards.remove(0);
    	usedCard.add(temp);
    	nUsed+=1;
    	return temp;
	}
	public ArrayList<Card> getOpenedCard(){  
		return openCard;
	}
}