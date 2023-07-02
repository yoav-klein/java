
public class Deck {
    
    private Card[][] cards = new Card[4][13];

    public Deck() {
        for(int i = Card.DIAMONDS; i <= Card.SPADES; ++i) {
            for(int j = Card.ACE; j <= Card.KING; ++j) {
                cards[i - 1][j - 1] = new Card(i, j);
            }
        }
    }

    public Card getCard(int suit, int rank) {
        return cards[suit-1][rank-1];
    }
}