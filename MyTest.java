import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

class MyTest {

	static Deck gameDeck;
	static Card deckCard;
	static ArrayList<Card> clientCards;
	static ArrayList<Card> dealerCards;

	@BeforeEach
	void before(){
		gameDeck = new Deck();
		gameDeck.shuffle();
		clientCards = new ArrayList<>();
		dealerCards = new ArrayList<>();
	}

	@BeforeAll
	static void setup() {
		deckCard = new Card("","");
	}

	@Test
	void deckCardSize() {
  		assertEquals(52, gameDeck.size());
	}

	@Test
	void checkDeckSizeWithDraws() {
		for(int i = 0; i < 3; i++){
			clientCards.add(gameDeck.drawCard());
			dealerCards.add(gameDeck.drawCard());
		}

		assertEquals(46, gameDeck.size());
	}

	@Test
	void checkHandStraightFlush() {
		Card c1 = new Card("Clubs", "8");
		Card c2 = new Card("Clubs", "10");
		Card c3 = new Card("Clubs", "9");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		String hand = pokerGame.checkHand(clientCards);
		assertEquals("Straight Flush", hand);
	}

	@Test
	void checkHandThreeOFAKind() {
		Card c1 = new Card("Clubs", "8");
		Card c2 = new Card("Spades", "8");
		Card c3 = new Card("Diamonds", "8");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		String hand = pokerGame.checkHand(clientCards);
		assertEquals("Three Of A Kind", hand);
	}

	@Test
	void checkHandStraight() {
		Card c1 = new Card("Clubs", "8");
		Card c2 = new Card("Spades", "9");
		Card c3 = new Card("Diamonds", "10");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		String hand = pokerGame.checkHand(clientCards);
		assertEquals("Straight", hand);
	}


	@Test
	void checkHandFlush() {
		Card c1 = new Card("Clubs", "8");
		Card c2 = new Card("Clubs", "5");
		Card c3 = new Card("Clubs", "10");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		String hand = pokerGame.checkHand(clientCards);
		assertEquals("Flush", hand);
	}

	@Test
	void checkHandPair() {
		Card c1 = new Card("Clubs", "8");
		Card c2 = new Card("Diamonds", "8");
		Card c3 = new Card("Clubs", "10");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		String hand = pokerGame.checkHand(clientCards);
		assertEquals("Pair", hand);
	}

	@Test
	void compareHandsDealerWOQueen() {
		Card c1 = new Card("Clubs", "8");
		Card c2 = new Card("Diamonds", "8");
		Card c3 = new Card("Clubs", "10");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		Card d1 = new Card("Hearts", "5");
		Card d2 = new Card("Hearts", "10");
		Card d3 = new Card("Hearts", "9");

		dealerCards.add(d1);
		dealerCards.add(d2);
		dealerCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client won! Dealer has no Queen or higher", winorlose);
	}

	@Test
	void compareSameHands(){
		Card c1 = new Card("Clubs", "10");
		Card c2 = new Card("Clubs", "jack");
		Card c3 = new Card("Clubs", "queen");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		Card d1 = new Card("Hearts", "10");
		Card d2 = new Card("Hearts", "jack");
		Card d3 = new Card("Hearts", "queen");

		dealerCards.add(d1);
		dealerCards.add(d2);
		dealerCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Dealer and Client tied", winorlose);
	}

	@Test
	void compareClientWinStraightFLush(){
		Card c1 = new Card("Clubs", "10");
		Card c2 = new Card("Clubs", "jack");
		Card c3 = new Card("Clubs", "queen");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		Card d1 = new Card("Diamonds", "queen");
		Card d2 = new Card("Hearts", "queen");
		Card d3 = new Card("Spades", "queen");

		dealerCards.add(d1);
		dealerCards.add(d2);
		dealerCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client won! Straight Flush", winorlose);
	}

	@Test
	void compareClientWinThreeofKind(){
		Card c1 = new Card("Clubs", "10");
		Card c2 = new Card("Spades", "10");
		Card c3 = new Card("Diamonds", "10");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		Card d1 = new Card("Diamonds", "queen");
		Card d2 = new Card("Hearts", "jack");
		Card d3 = new Card("Spades", "10");

		dealerCards.add(d1);
		dealerCards.add(d2);
		dealerCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client won! Three Of A Kind", winorlose);
	}

	@Test
	void compareClientWinStraight(){
		Card c1 = new Card("Diamonds", "queen");
		Card c2 = new Card("Hearts", "jack");
		Card c3 = new Card("Spades", "10");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		Card d1 = new Card("Diamonds", "queen");
		Card d2 = new Card("Diamonds", "7");
		Card d3 = new Card("Diamonds", "10");

		dealerCards.add(d1);
		dealerCards.add(d2);
		dealerCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client won! Straight", winorlose);
	}

	@Test
	void compareClientWinFlush(){
		Card c1 = new Card("Diamonds", "queen");
		Card c2 = new Card("Diamonds", "7");
		Card c3 = new Card("Diamonds", "10");

		clientCards.add(c1);
		clientCards.add(c2);
		clientCards.add(c3);

		Card d1 = new Card("Spades", "10");
		Card d2 = new Card("Diamonds", "queen");
		Card d3 = new Card("Diamonds", "10");

		dealerCards.add(d1);
		dealerCards.add(d2);
		dealerCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client won! Flush", winorlose);
	}

	@Test
	void compareDealerWinStraightFLush(){
		Card c1 = new Card("Clubs", "10");
		Card c2 = new Card("Clubs", "jack");
		Card c3 = new Card("Clubs", "queen");

		dealerCards.add(c1);
		dealerCards.add(c2);
		dealerCards.add(c3);

		Card d1 = new Card("Diamonds", "queen");
		Card d2 = new Card("Hearts", "queen");
		Card d3 = new Card("Spades", "queen");

		clientCards.add(d1);
		clientCards.add(d2);
		clientCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client Lost", winorlose);
	}

	@Test
	void compareDealerWinThreeofKind(){
		Card c1 = new Card("Clubs", "queen");
		Card c2 = new Card("Spades", "queen");
		Card c3 = new Card("Diamonds", "queen");

		dealerCards.add(c1);
		dealerCards.add(c2);
		dealerCards.add(c3);

		Card d1 = new Card("Hearts", "1");
		Card d2 = new Card("Hearts", "jack");
		Card d3 = new Card("Spades", "10");

		clientCards.add(d1);
		clientCards.add(d2);
		clientCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client Lost", winorlose);
	}

	@Test
	void compareDealerWinStraight(){
		Card c1 = new Card("Diamonds", "queen");
		Card c2 = new Card("Hearts", "jack");
		Card c3 = new Card("Spades", "10");

		dealerCards.add(c1);
		dealerCards.add(c2);
		dealerCards.add(c3);

		Card d1 = new Card("Diamonds", "queen");
		Card d2 = new Card("Diamonds", "7");
		Card d3 = new Card("Diamonds", "10");

		clientCards.add(d1);
		clientCards.add(d2);
		clientCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client Lost", winorlose);
	}

	@Test
	void compareDealerWinFlush(){
		Card c1 = new Card("Diamonds", "queen");
		Card c2 = new Card("Diamonds", "7");
		Card c3 = new Card("Diamonds", "10");

		dealerCards.add(c1);
		dealerCards.add(c2);
		dealerCards.add(c3);

		Card d1 = new Card("Spades", "10");
		Card d2 = new Card("Diamonds", "queen");
		Card d3 = new Card("Diamonds", "10");

		clientCards.add(d1);
		clientCards.add(d2);
		clientCards.add(d3);

		String dealerHand = pokerGame.checkHand(dealerCards);
		String clientHand = pokerGame.checkHand(clientCards);

		String winorlose = pokerGame.compareHands(dealerCards,clientCards, dealerHand ,clientHand);
		assertEquals("Client Lost", winorlose);
	}

}
