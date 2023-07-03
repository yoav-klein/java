
class Test {
    public static void main(String[] args) {
        
        Deck d = new Deck();

        System.out.println(Card.rankToString(d.getCard(3, 9).getRank()));
        System.out.println(Card.suitToString(d.getCard(3, 9).getSuit()));
    }
}