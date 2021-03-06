import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {
        
        for (Suits cardSuit : Suits.values()) {
            for (Values cardValue : Values.values()) {
                this.deck.add(new Card(cardValue, cardSuit));
            }
        }
    }
       
    
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public String toString() {
        String cardList = "";
        for (Card card : this.deck) {
            cardList += "\n-" + card.toString();
        }
        return cardList;
    }


    public Card getCard(int i){
        return this.deck.get(i);
    }

    public void removeCard(int i){
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        this.deck.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
       
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.deck.size();

        // Cards will be added to the deck until the deck size matches its original size
        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }

    }

    public int cardsValue() {
        int total = 0;
        int aces = 0;

        for (Card card : this.deck) {
            switch (card.getValue()) {
                case TWO -> total += 2;
                case THREE -> total += 3;
                case FOUR -> total += 4;
                case FIVE -> total += 5;
                case SIX -> total += 6;
                case SEVEN -> total += 7;
                case EIGHT -> total += 8;
                case NINE -> total += 9;
                case TEN, JACK, QUEEN, KING -> total += 10;
                case ACE -> aces += 1;
            }
            
        }
        return total + aces;



    



}
}
