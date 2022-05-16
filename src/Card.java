import java.util.Random;

public class Card {
    private Suits suit;
    private Values value;


    private Values[] values = Values.values();
    private Random randomValues = new Random();
    private Suits[] suits = Suits.values();
    private Random randomSuits = new Random();


    public Card(Values value, Suits suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card() {
        this.value = getRandomValue();
        this.suit = getRandomSuit();
    }


    public Values getValue() {
        return this.value;
    }

    public Values  getRandomValue() {
        return values[randomValues.nextInt(values.length)];
    }


    public Suits getRandomSuit() {
        return suits[randomSuits.nextInt(values.length)];
    }

    @Override
    public String toString() {
        return  this.value.toString() + " of " + this.suit.toString() ;
    }


}