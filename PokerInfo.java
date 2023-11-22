import java.io.Serializable;
import java.util.ArrayList;

public class PokerInfo implements Serializable {

    private static final long serialVerionUID = 1L;

    int client_number;
    int pair_wager;
    int play_wager;
    int total_winnings;
    int ante_wager;
    int winning;
    String message;
    ArrayList<Card> clientCards;
    ArrayList<Card> dealerCards;
    int counter;
    String dealerHand;
    String clientHand;
    String win_or_loose_message;


    PokerInfo(){
        client_number = 0;
        ante_wager = 0;
        pair_wager = 0;
        play_wager = 0;
        winning = 0;
        total_winnings = 0;
        message = "Empty message try";
        clientCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        counter = 0;
        dealerHand = "";
        clientHand = "";
        win_or_loose_message = "";
    }

    void setClient_number(int num){client_number = num;}
    void setAnte_wager(int num){ante_wager = num;}
    void setPair_wager(int num){pair_wager = num;}
    void setPlay_wager(int num){play_wager = num;}
    void setCounter(Integer num){
        counter = num;
    }
    void setMessage(String s){
        message = s;
    }
    void setDealerHand(String s) { dealerHand = s;}
    void setClientHand(String s){ clientHand = s;}
    void setWin_or_loose_message(String s){ win_or_loose_message = s;}
    void setTotal_winnings(Integer n){total_winnings += n;}

    Integer getTotal_winnings(){ return total_winnings;}
    Integer getClient_number(){return client_number;}
    Integer getAnte_wager(){return ante_wager;}
    Integer getPair_wager(){return pair_wager;}
    Integer getPlay_wager(){return play_wager;}
    Integer getCounter(){
        return counter;
    }
    String getMessage(){
        return message;
    }
    String getClientCards(){
        return clientCards.get(0) + " " + clientCards.get(1) + " " + clientCards.get(2);
    }
    String getDealerCards(){
        return dealerCards.get(0) + " " + dealerCards.get(1) + " " + dealerCards.get(2);
    }
    String getClientHand(){return clientHand;}
    String getDealerHand(){return dealerHand;}
    String getWin_or_loose_message(){return win_or_loose_message;}

    void reset(){
        ante_wager = 0;
        pair_wager = 0;
        play_wager = 0;
        counter = 0;
        winning = 0;
        dealerHand = "";
        clientHand = "";
        dealerCards.clear();
        clientCards.clear();
    }

}
