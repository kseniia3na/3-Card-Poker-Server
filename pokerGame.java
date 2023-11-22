import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class pokerGame {
    private static ArrayList<String> cardsByRanks = new ArrayList<>();;
    private static Boolean sameSuit;
    private static Boolean straightHand;
    private static Boolean sameKind;
    private static Boolean pairHand;

    private static ArrayList<String> getRanks(ArrayList<Card> threeCards){
        ArrayList<String> temp = new ArrayList<>();

        for(Card card: threeCards){
            temp.add(card.getRank());
        }

        return temp;
    }

    private static void sameSuitInHand(ArrayList<Card> threeCards){
        String suit = threeCards.get(0).getSuit();
        for(Card card: threeCards){
            if(suit != card.getSuit()){
                sameSuit = false;
                return;
            }
        }
        sameSuit = true;
    }

    private static Boolean check_queen_or_high(ArrayList<Card> dealerCards){
        Boolean temp = true;

        for(int i = 0; i < 3; i++){

            System.out.println(dealerCards.get(i).getRank());
            if(dealerCards.get(i).getRank().equals("queen")){
                temp =  false;
            }
            else if(dealerCards.get(i).getRank().equals("king")){
                temp = false;
            }
            else if(dealerCards.get(i).getRank().equals("ace")){
                temp =  false;
            }
        }
        return temp;
    }

    private static void straightNumsInHand(ArrayList<String> threeCards){

        if(threeCards.contains("0") && threeCards.contains("1") && threeCards.contains("2")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("1") && threeCards.contains("2") && threeCards.contains("3")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("2") && threeCards.contains("3") && threeCards.contains("4")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("3") && threeCards.contains("4") && threeCards.contains("5")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("4") && threeCards.contains("5") && threeCards.contains("6")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("5") && threeCards.contains("6") && threeCards.contains("7")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("6") && threeCards.contains("7") && threeCards.contains("8")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("7") && threeCards.contains("8") && threeCards.contains("9")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("8") && threeCards.contains("9") && threeCards.contains("10")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("9") && threeCards.contains("10") && threeCards.contains("jack")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("10") && threeCards.contains("jack") && threeCards.contains("queen")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.contains("jack") && threeCards.contains("queen") && threeCards.contains("king")){
            straightHand = true;
            sameKind = false;
            pairHand = false;
        }
        else if(threeCards.get(0) == threeCards.get(1) && threeCards.get(1) == threeCards.get(2)){
            sameKind = true;
            straightHand = false;
            pairHand = false;
        }
        else if(threeCards.get(0) == threeCards.get(1) || threeCards.get(1) == threeCards.get(2) || threeCards.get(2) == threeCards.get(0)){
            straightHand = false;
            sameKind = false;
            pairHand = true;
        }
        else{
            straightHand = false;
            sameKind = false;
            pairHand = false;
        }
    }

    static public Integer computeWinnings(String winorlose, Integer anteWage, Integer pairPlus, Integer playWage){
        Integer total = 0;
        if(winorlose == "Client Lost" || winorlose == "Dealer and Client tied"){
            return total;
        }
        else{
            total = 2 * (anteWage + pairPlus + playWage);
        }
        return total;
    }

    //sets the win or loose message
    static public String compareHands(ArrayList<Card> dealerCards, ArrayList<Card> clientCards, String dealerHand, String clientHand){

        if(check_queen_or_high(dealerCards)){
            return "Client won! Dealer has no Queen or higher";
        }
        else if(dealerHand == clientHand){
            return "Dealer and Client tied";
        }
        else if(clientHand == "Straight Flush"){
            return "Client won! Straight Flush";
        }
        else if(clientHand == "Three Of A Kind" && dealerHand != "Straight Flush"){
            return "Client won! Three Of A Kind";
        }
        else if(clientHand == "Straight" && (dealerHand != "Striaght Flush" || dealerHand != "Three Of A Kind")){
            return "Client won! Straight";
        }
        else if(clientHand == "Flush" && dealerHand == "Pair"){
            return "Client won! Flush";
        }
        else if(clientHand == "Pair"){
            return "Client Lost";
        }
        else{
            return "Client Lost";
        }
    }

    static public String checkHand(ArrayList<Card> threeCards) {
        sameSuitInHand(threeCards);
        cardsByRanks = getRanks(threeCards);
        straightNumsInHand(cardsByRanks);

        if(straightHand && sameSuit){
            return "Straight Flush";
        }
        else if(sameKind){
            return "Three Of A Kind";
        }
        else if(straightHand){
            return "Straight";
        }
        else if(sameSuit){
            return "Flush";
        }
        else if(pairHand){
            return "Pair";
        }
        else{
            return "Not A Hand";
        }

    }
}