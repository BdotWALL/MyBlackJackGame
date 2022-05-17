import java.util.Scanner;

public class BlackJackGame {
    public static void main(String[] args)  {

        Scanner scan = new Scanner(System.in);
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerCard = new Deck();
        Deck dealerCard = new Deck();



        System.out.println("Welcome to Wall's Casino! Want to play some BlackJack?");

        
        System.out.println("Would you like to play? Enter yes or no" );
        String answer = scan.nextLine();

        if (answer.equals("yes")){
            System.out.println("Great! How much woud like to put into your betting account?");
            int answer2 = scan.nextInt();
            int bettingMoney = answer2;


            //Allows you to continue playing as long as $5 in account
            while(bettingMoney > 5) {
                System.out.print("You now have $" + bettingMoney + " in your betting account. ");

               
                System.out.println("How much would you like to bet? (Enter 0 to take your money and run!)" );
                int betAmount = scan.nextInt();

                // if statement that allows player to exit with 0
                if(betAmount == 0){
                    System.out.println("Smart one! Come back soon");
                    break;

                // if statement that will not allow player to bet unless they are betting in increments of $5
                 }else if (!(betAmount % 5 == 0)) {
                    System.out.println("Unaccepteable bet amount. Your can only bet in $5 increments.");
                }
                System.out.println("You bet $" + betAmount + ". Your new betting account balance is $" + (bettingMoney - betAmount) + ".");

            // Two cards drawn for player and dealer
            playerCard.draw(playingDeck);
            playerCard.draw(playingDeck);
            dealerCard.draw(playingDeck);
            dealerCard.draw(playingDeck);


            boolean winner = false;

                System.out.println("Your hand: ");
                System.out.println(playerCard.toString());
                System.out.println("Your hand is valued at: " + playerCard.cardsValue());

                // Display dealer hand - one card is hidden until the player busts or stands
                System.out.println("Dealer Hand: " + dealerCard.getCard(0).toString() + " and [Hidden]");
    
                if (betAmount > bettingMoney) {
                    System.out.println("Looks like you're all tapped out and do no have enough to bet. Please leave.");
                    
                }

              // While loop lets player  decide whether to hit or stand until they receive desired amount
              while(true) {
                System.out.print("Would you like to hit or stand? Enter hit or stand ");
                String hitOrStand = scan.next();
        
                 if (hitOrStand.equals("hit")) {
                    playerCard.draw(playingDeck);
                    System.out.println("You draw a: " + playerCard.getCard(playerCard.deckSize() - 1).toString());
                    System.out.println("Your hand is now valued at: " + playerCard.cardsValue());
                }
                //Once player chooses to stand Dealer cards and value revealed
                 if(hitOrStand.equals("stand")){
                    System.out.println("Dealer Cards: " + dealerCard.toString());
                    System.out.println("Dealer hand is valued at: " + dealerCard.cardsValue() );

                //If player value over 21, dealer wins, cards are placed back in deck
                if (playerCard.cardsValue() > 21) {
                    System.out.println("Bust. Currently valued at: " + playerCard.cardsValue());
                    System.out.println("Dealer wins!!!");
                    System.out.println("You lost $" + (betAmount ) + "! Your new account balance is $" + (bettingMoney - (betAmount )+ "."));
                    bettingMoney -= betAmount;
                    winner = true;
                    playerCard.moveAllToDeck(playingDeck);
                    dealerCard.moveAllToDeck(playingDeck);
                    break;
                }
                           
                //Dealer will draw as long as he has less than 17, otherwise as it        
                while(dealerCard.cardsValue() <17){
                    
                    dealerCard.draw(playingDeck);
                    System.out.println("Dealer Draws: " + dealerCard.getCard(dealerCard.deckSize()-1).toString() + "\n" + "Dealer hand is valued at: " + dealerCard.cardsValue() );
                }
                
                //If dealer goes over 21 they bust, and player wins. Cards are put back in deck 
                if ((dealerCard.cardsValue()  > 21) && !winner) {
                    System.out.println("Dealer busts! You win.");
                    System.out.println("You won $" + (betAmount * 2 ) + "! Your new account balance is $" + (bettingMoney + (betAmount *2)+ "."));
                    bettingMoney += betAmount;
                    winner = true;
                    playerCard.moveAllToDeck(playingDeck);
                    dealerCard.moveAllToDeck(playingDeck);
                    break;
                    }
                
                //if no one bust, player wins if value higher than dealer
                if ((playerCard.cardsValue() > dealerCard.cardsValue()) && !winner) {
                    System.out.println("You won the hand!");
                    System.out.println("Player's hand value: " + playerCard.cardsValue());
                    System.out.println("Dealer's hand value: " +dealerCard.cardsValue());
                    bettingMoney += betAmount;
                    playerCard.moveAllToDeck(playingDeck);
                    dealerCard.moveAllToDeck(playingDeck);
                    break;
                    
                //if no one bust, dealers wins if value higher than player
                } else if (!winner) {
                    System.out.println("You lost the hand!");
                    System.out.println("Dealer's hand value: " +dealerCard.cardsValue());
                    System.out.println("Player's hand value: " +playerCard.cardsValue());
                    bettingMoney -= betAmount;
                    playerCard.moveAllToDeck(playingDeck);
                    dealerCard.moveAllToDeck(playingDeck);
                    break;
                    
                    
                }
                
            }       
                                
    }
        if(bettingMoney < 5){
             System.out.println("You're all out of money or you don't have enough for the minimum $5 bet." + "\n" 
             + "Either way, come back when you got more to lose.");
            break;
         }
    }

        } else {
            System.out.println("Alright, maybe some other time. See ya later!");

        }
  


 }
}
