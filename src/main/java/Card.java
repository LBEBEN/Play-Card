import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class Card {
    private final int rank;
    private final int suit;
    public static final String[] SIUTS = {"trefl", "karo", "kier", "pik"};
    public static final String[] RANKS = {null,"2","3","4","5","6","7","8","9","10","walet","dama","król", "as"};

    public Card(int rank, int suit){
        this.rank=rank;
        this.suit=suit;
    }

    public int getRank() {
        return this.rank;
    }

    public int getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return RANKS[this.rank] +" "+SIUTS[this.suit];
    }

    public boolean equals(Card that){
        return this.rank == that.rank && this.suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public int compereTo(Card that){
        if(this.suit < that.suit){
            return -1;
        }
        if(this.suit > that.suit){
            return 1;
        }
        if(this.rank < that.rank){
            return -1;
        }
        if (this.rank > that.rank){
            return  1;
        }
        return 0;
    }
    // metoda tworząca talię kart
    public static Card[] makeDeck(){
        int index = 0;
        Card[] cards = new Card[52];
        for(int suit = 0; suit <=3; suit ++){
            for(int rank = 1; rank <= 13; rank ++){
                cards[index] = new Card(rank,suit);
                index ++;
            }
        }
        return cards;
    }

    //metoda wyświetlająca tablicę kart opcjonalna
    public static void printDeck(Card[] cards){
        for(Card c : cards){
            System.out.println(c);
        }
    }

    //wyszukiwanie sekwencyjne
    public static int search(Card[] cards, Card target){
        for (int i = 0; i < cards.length; i++){
            if(cards[i].equals(target)){
                return i;
            }
        }
        return  -1;
    }

    //wyszukiwanie binarne
    public static int binarySearch(Card[] cards, Card target){
        int low = 0;
        int high = cards.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            int comp = cards[mid].compereTo(target);
            if(comp == 0){
                return mid;
            }else if( comp < 0){
                low = mid + 1;
            }
            else {
                high = mid -1;
            }
        }
        return -1;
    }

    public static String suitHist(Card[] cards){
        int trefl = 0;
        int karo = 0;
        int kier = 0;
        int pik = 0;
        for (Card c : cards){
            switch (SIUTS[c.suit]) {
                case "trefl" -> trefl++;
                case "karo" -> karo++;
                case "kier" -> kier++;
                case "pik" -> pik++;
            }
        }
        return  String.format("W wylosowanej talii jest  kart trefl: %s,kart karo: %s,kart kier: %s oraz kart pik %s.", trefl, karo,kier,pik);
    }

    public static boolean hasFlush(Card[] cards){
        int trefl = 0;
        int karo = 0;
        int kier = 0;
        int pik = 0;
        for (Card c : cards){
            switch (SIUTS[c.suit]) {
                case "trefl" -> trefl++;
                case "karo" -> karo++;
                case "kier" -> kier++;
                case "pik" -> pik++;
            }
        }
        if (trefl > 4 || karo > 4 || kier > 4 || pik > 4){
        return true;}
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Card[] deck = makeDeck();
        Card[] randomDeck = new Card[10];
        for (int i = 0; i < randomDeck.length; i++){
            randomDeck[i] = deck[random.nextInt(deck.length-1)];
        }
        System.out.println(Arrays.toString(randomDeck));
        System.out.println(hasFlush(randomDeck));

    }


}
