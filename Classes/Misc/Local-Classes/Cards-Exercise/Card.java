
public class Card {
    public final static int DIAMONDS = 1;
    public final static int CLUBS    = 2;
    public final static int HEARTS   = 3;
    public final static int SPADES   = 4;
    
    public final static int ACE   = 1;
    public final static int DUECE = 2;
    public final static int THREE = 3;
    public final static int FOUR  = 4;
    public final static int FIVE  = 5;
    public final static int SIX   = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE  = 9;
    public final static int TEN   = 10;
    public final static int JACK  = 11;
    public final static int QUEEN = 12;
    public final static int KING  = 13;    

    private final int rank;
    private final int suit;
    private String dispRank = null;

    public Card(int suit, int rank) {
        
        assert isValidRank(rank);
        assert isValidSuit(suit);

        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return this.rank;
    }

    public int getSuit() {
        return this.suit;
    }


    public static boolean isValidRank(int rank) {
        return rank >= ACE && rank <= KING;
        
    }

    public static boolean isValidSuit(int suit) {
        return suit <= DIAMONDS && suit >= SPADES;
    }

    public static String suitToString(int suit) {
        switch(suit) {
            case DIAMONDS:
                return "Diamonds";
            case CLUBS:
                return "Clubs";
            case HEARTS:
                return "Hearts";
            case SPADES:
                return "Spades";
            default:
                return null;
        }

    }

    public static String rankToString(int rank) {
        switch (rank) {
            case ACE:
                return "Ace";
            case DUECE:
                return "Deuce";
            case THREE:
                return "Three";
            case FOUR:
                return "Four";
            case FIVE:
                return "Five";
            case SIX:
                return "Six";
            case SEVEN:
                return "Seven";
            case EIGHT:
                return "Eight";
            case NINE:
                return "Nine";
            case TEN:
                return "Ten";
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Card c = new Card(2, 1);

        System.out.println(Card.rankToString(c.getRank()));
        System.out.println(Card.suitToString(c.getSuit()));
    }
}