import java.io.Serializable;
import java.util.*;

public class Deck implements Serializable {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();

        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {

        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }

    public void reset(){
        cards.clear();

        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    public Card deal() {
        if (cards.isEmpty()) {
            return null;
        } else {
            return cards.remove(0);
        }
    }
}