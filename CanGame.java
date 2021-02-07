
/**
 * Programmer : Pravesh Jung Karki CSC110ab 11:00am
 * Programming Assignment 3 
 * Description of program : The program will basically play
 * the dice game with two players.
 * At first the program will ask if the user want's to see
 * the rules of the game .When the user are ready to
 * continue then
 * they will press ENTER . After that application will ask
 * the names of the player and they will then need to roll
 * the dice
 * The one with the highest roll will play first .The two
 * players will alternate taking a turn until one of them
 * earns a total
 * game score of 200 or more. so the first player to earn
 * 200 points will win the game .
 * If Players roll zero twice in a row then the penalty of
 * 50 points is deducted from their current scores and can
 * of dice will
 * passed to other player after that. The game will still
 * ask the player if they want to roll even if the score is
 * 200 .This would be
 * a mistake of the player that they don't want to commit.
 */

import java.util.Scanner;
import java.util.Random;

public class CanGame {

   // named constant for winning score .
   private final static int WIN_SCORE       = 200;

   // named constant for penalty point.
   private final static int DECREMENT_SCORE = 50;

   // creating scanner object for user input.
   private static Scanner   keyboard        = new Scanner( System.in );


   /**
    * This is the main method where several other methods is
    * being called.
    */
   public static void main(String[] args) {

      // creating can of three dice object
      CanOf3Dice three = new CanOf3Dice();

      // calling scoring rules methods.
      scoringRules();

      // calling name1 method
      String name1 = player1Name();

      // calling name2 method
      String name2 = player2Name();

      // calling playGame method
      playGame( three, name1, name2 );

   }

   /**
    * This method will display the scoring rules of the game
    * It will ask the user weather they want to see the
    * rules or not .
    * method doesnot take any parametes and doesnot returns
    * anything.
    */
    public static void scoringRules() {
      String userInput;
      System.out.println( "Welcome to the Can-of-3-Dice Game" );
      System.out.print( "Do you want to see the scoring rules? (Y/N) " );
    
      userInput = keyboard.nextLine();
      if ( userInput.equalsIgnoreCase( "Y" ) ) {
         System.out.print( "Two players play this game and take turns" );
         System.out.println( " rolling the can of three dice.\n" );
         System.out.println( "To start, each player rolls the can of dice." );
         System.out.println( "The player with the highest can total plays first." );
         System.out.println( "The first player to reach 200 wins the game\n" );
         System.out.println( "To take a turn, a player rolls the can of dice and" );
         System.out.println( "\t is awarded the score earned for that roll." );
         System.out.print( "The player may continue to roll until the player" );
         System.out.println( "tells the game to stop" );
         System.out.print("\t or the player earns scores of ");
         System.out.println(" zero on two consecutive rolls." );
         System.out.print( "When two consecutive rolls earn zero, a panalty" );
         System.out.println( "of 50 points is deducted" );
         System.out.print( "\t from the current player's points and" );
         System.out.println( " the can is passes to the other player." );
         System.out.println( "Here is a summary of scoring:\n" );
         System.out.println("The first three are values added to a player's game score:" );
         System.out.println( "1) Triple: All three dice rolled are equal" );
         System.out.println("\t Score earned is the square of the sum of the faces\n" );
         System.out.println( "2) Sequence: The three dice are consecutive values" );
         System.out.print( "\t Score earned is the sum of the faces" );
         System.out.println( " TIMES the maximum face value\n" );
         System.out.println( "3) Half-House: Only two of the dice are equal" );
         System.out.print( "\t Score earned is the sum of the two equal" );
         System.out.println( "values TIMES the third value\n" );
         System.out.print( "The next two rolls are subtracted ");
         System.out.println(" from a player's game score:" );
         System.out.print( "4) All Even: If the roll is NOT a triple" );
         System.out.println( " then the value subtracted form the" );
         System.out.print("\tplayer's game score is a random ");
         System.out.println(" number between 1 and 10.\n\n" );
         System.out.print("5) All Odd: If the roll is NOT a triple ");
         System.out.println(" then the value subtracted from the" );
         System.out.print("\tplayer's game score is a random ");
         System.out.println(" number between 1 and 20.\n" );
      }
   }
   
    /**
    * This method will ask the name of first player
    * It doesnot take any parameter.
    * It will return the name of player1.
    */
   public static String player1Name() {

      String name1 = " ";
      System.out.println( "Press ENTER to continue " );
      keyboard.nextLine();
      System.out.print( "Enter the  name of Player 1: " );
      name1 = keyboard.next();

      return name1;
   }

   /**
    * This method will ask the name of second player
    * It doesnot take any parameter.
    * It will return the name of player2.
    */
   public static String player2Name() {

      String name2 = " ";
      System.out.print( "Enter the  name of Player 2: " );
      name2 = keyboard.next();
      return name2;
   }

   /**
    * This method will take two parameters (CanOf3Dice
    * object and a playerName ).
    * It will check if the score is zero twice in row and
    * does the necessery deduction.
    * It will ask the user if they want to player again .
    * It will keep track of the scores.
    * The method will returns the total points earned by a
    * player.
    */
   public static int getScore(CanOf3Dice three , String playerName) {
      boolean flag = false;
      boolean flag2 = false;
      String keepPlaying = " ";
      int points = 0;

      do {

         three.roll();
         int score = three.getScore();
         System.out.println( playerName + ": "
               + three.toString( three.getOdd(), three.getEven() ) );
         if ( score == 0 ) {
            if ( flag == true ) {
               points = points - DECREMENT_SCORE;
               System.out.print( playerName
                     + " scored ZERO twice in a row - round halted." );
               System.out.println( " Penalty points deducted." );
               flag2 = true;
               keepPlaying = " ";
            }
            else {
               flag = true;
            }
         }
         else {
            flag = false;
            points += score;
            System.out
                  .print( playerName + ",  Your score is " + points + " : " );
            System.out.print( "Keep playing? (y/n) " );
            keepPlaying = keyboard.next();

         }
      } while ( keepPlaying.equalsIgnoreCase( "y" )
            || (!flag2 && three.getScore() == 0) );

      return points;
   }

   /**
    * The method playGame will player the game. It will take
    * three parameters
    * (class object , name1 and name2 ) . Both the players
    * will roll the dice and the one with highest points
    * will play first.
    * The method uses boolean to keep track of the turns of
    * the player.
    * It will call the getScore method and keep track of the
    * scores for specific player.
    * It doesnot return any value .
    */
   public static void playGame(CanOf3Dice three , String n1 , String n2) {
      int player1Score = 0, player2Score = 0;
      boolean turn = false;
      int firstRoll = three.roll();
      int secondRoll = three.roll();
      System.out.println( n1 + " rolled " + firstRoll );
      System.out.println( n2 + " rolled " + secondRoll );
      if ( firstRoll > secondRoll ) {
         System.out.println( "So " + n1 + " will play first . . ." );
         turn = true;
      }
      else {
         System.out.println( "So " + n2 + " will play first . . ." );
         turn = false;
      }
      System.out.println( "Press ENTER to continue . . ." );
      keyboard.nextLine();
      keyboard.nextLine();  
      do {
         if ( turn ) {
            player1Score = player1Score + getScore( three, n1 );
            turn = false;
         }
         else {
            player2Score = player2Score + getScore( three, n2 );
            turn = true;
         }
         System.out.printf( "Score: %10s %10s \n", n1, n2 );
         System.out.printf( "%14d %14d \n", player1Score, player2Score );
         if ( player1Score < WIN_SCORE && player2Score < WIN_SCORE ) {
            System.out.println( "Press ENTER to continue . . ." );
            keyboard.nextLine();
            keyboard.nextLine(); 
         }
      } while ( player1Score < WIN_SCORE && player2Score < WIN_SCORE );
      if ( player1Score >= WIN_SCORE || player2Score >= WIN_SCORE ) {
         if ( player1Score > player2Score ) {
            System.out.print("Congratulations, " + n1 + ", you won!\nYou scored " );
            System.out.print( +player1Score + " and " + n2 + " only scored ");
            System.out.println( + player2Score + " points!" );
         }
         else {
            System.out.print("Congratulations, " + n2 + ", you won!\nYou scored " );
            System.out.print( +player2Score + " and " + n1 + " only scored ");
            System.out.println(+ player1Score + " points!" );
         }
      }
   }
}

/**
 * This class will be the data type that is use to create
 * the can of 3 dice used in the game.
 * This class will have a several methods that will help to
 * play the Cangame.
 * The method will roll all three dice in a can and check if
 * the face value of the dice is halfhouse, triple or a
 * sequence.
 * If non of this is true then the method will return a
 * zero.
 * In halfhouse there is also an odd and an even method that
 * will give odd or even value after necessary calculation.
 */


class CanOf3Dice {

   // Creating random object from a Randoom class
   public static Random rand = new Random();

   // four private instances variables
   // Three of these variables are die object
   private Die          dice1;

   private Die          dice2;

   private Die          dice3;

   // for displaying the score of a roll
   private String       str;

   // for odd and even value
   private int          oddValue, evenValue;

   // creating a default constructors
   // initialize the three Die instance variables.

   public CanOf3Dice() {

      dice1 = new Die();
      dice2 = new Die();
      dice3 = new Die();

   }

   /*
    * creating methods roll that will roll the can of a
    * dice.
    * the method will return the sum of the face of all
    * three dice.
    */
   public int roll() {

      dice1.roll();
      dice2.roll();
      dice3.roll();

      return (dice1.roll() + dice2.roll() + dice3.roll());

   }

   /**
    * creating methods getScore.
    * This method will determine the score earned on thee
    * most recent roll of the can object.
    * Number of private helper method are created for
    * functioning of this method.
    * method will return int (the result of the score after
    * necessary calculation)
    */
   public int getScore() {
      int result = 0;
      // checks for triples
      if ( triples() ) {
         result = ((dice1.getFace() * 3) * (dice3.getFace() * 3));
      }
      // checks for halfHouse
      else if ( isHalfHouse() > 0 ) {
         int haHo = isHalfHouse();
         oddValue = isOdd();
         evenValue = isEven();
         result = (haHo - oddValue - evenValue);
      }
      // checks for a sequence
      else if ( sequence() ) {
         int max = dice1.getFace();
         for ( int i = 0; i < 2; i++ ) {
            if ( i == 0 && max < dice2.getFace() )
               max = dice2.getFace();
            if ( i == 1 && max < dice3.getFace() )
               max = dice3.getFace();
         }
         result = max * (dice1.getFace() + dice2.getFace() + dice3.getFace());
      }
      // zero if non of them matches
      else {
         result = 0;
      }
      // returns result
      return result;
   }

   /**
    * This method is the helper method for getting score .
    * The method sequence will check will the dice is a
    * sequence.
    * the method returns boolean true if it is a sequence .
    * To be a sequence it has to be a different consecutive
    * values.
    */
   private boolean sequence() {
      boolean sequence = false;
      if ( (dice1.getFace() < dice2.getFace()
            && dice2.getFace() < dice3.getFace())
            && (dice3.getFace() - dice1.getFace() == 2) )
         sequence = true;
      else if ( (dice1.getFace() < dice3.getFace()
            && dice3.getFace() < dice2.getFace())
            && (dice2.getFace() - dice1.getFace() == 2) )
         sequence = true;
      else if ( (dice2.getFace() < dice1.getFace()
            && dice1.getFace() < dice3.getFace())
            && (dice3.getFace() - dice2.getFace() == 2) )
         sequence = true;
      else if ( (dice2.getFace() < dice3.getFace()
            && dice3.getFace() < dice1.getFace())
            && (dice1.getFace() - dice2.getFace() == 2) )
         sequence = true;
      else if ( (dice3.getFace() < dice1.getFace()
            && dice1.getFace() < dice2.getFace())
            && (dice2.getFace() - dice3.getFace() == 2) )
         sequence = true;
      else if ( (dice3.getFace() < dice2.getFace()
            && dice2.getFace() < dice1.getFace())
            && (dice1.getFace() - dice3.getFace() == 2) )
         sequence = true;
      else
         sequence = false;
      return sequence;
   }

   /**
    * This method is the helper method for getting score .
    * This method will check if the dice are triples.
    * To be a triple all the dice should be of same number.
    * If so we will squared the sum of all three face
    * values.
    * The method will return true .
    */
   private boolean triples() {

      boolean triples = false;

      if ( dice1.getFace() == dice2.getFace()
            && dice2.getFace() == dice3.getFace() ) {

         triples = true;
      }
      return triples;

   }

   /**
    * This method is the helper method for getting score .
    * It will check if the dice is a halfHouse
    * If two value is equal and third value is different
    * then it's a halfHouse.
    * It will return an integer: The sum of two eqaul dice
    * multiplies by third.
    * 
    */

   private int isHalfHouse() {

      int halfHouse = 0;
      if ( dice1.getFace() == dice2.getFace()
            && dice1.getFace() != dice3.getFace() ) {
         halfHouse = (dice1.getFace() + dice2.getFace()) * dice3.getFace();
      }
      else if ( dice1.getFace() == dice3.getFace()
            && dice1.getFace() != dice2.getFace() ) {
         halfHouse = (dice1.getFace() + dice3.getFace()) * dice2.getFace();
      }
      else if ( dice2.getFace() == dice3.getFace()
            && dice2.getFace() != dice1.getFace() ) {
         halfHouse = (dice2.getFace() + dice3.getFace()) * dice1.getFace();
      }

      return halfHouse;

   }

   /**
    * The method isOdd will check if the face values of dice
    * are the odd values .
    * The method will create a random object from 1-20 and
    * that value will be subtracted from halfHouse.
    * It will return the odd value as an integer.
    */

   private int isOdd() {

      int odd = 0;

      if ( (dice1.getFace() % 2 != 0) && (dice2.getFace() % 2 != 0)
            && (dice3.getFace() % 2 != 0) ) {

         odd = rand.nextInt( 20 ) + 1;
      }

      return odd;

   }

   /**
    * The method isEven will check if the face values of
    * dice are the even values .
    * The method will create a random object from 1-10 and
    * that value will be subtracted from halfHouse.
    * It wi;ll return an even integer.
    */

   private int isEven() {

      int even = 0;
      if ( (dice1.getFace() % 2 == 0) && (dice2.getFace() % 2 == 0)
            && (dice3.getFace() % 2 == 0) ) {
         even = rand.nextInt( 10 ) + 1;
      }

      return even;

   }

   /**
    * getOdd method will return the oddValue.
    */
   public int getOdd() {

      return oddValue;

   }

   /**
    * getEven method will return the even Value.
    */

   public int getEven() {

      return evenValue;

   }

   /**
    * The method toString will take two parameters(both
    * integers).
    * It will return the String that describes the state of
    * the can.
    * 
    */
   public String toString(int odds , int evens) {
      String str = " ";
      // If it's a triples
      if ( triples() ) {
         str = dice1.toString() + " - " + dice2.toString() + " - "
               + dice3.toString() + " : " + "\t Triple:  " + getScore();
      }
      // If it's a halfHouse
      else if ( isHalfHouse() > 0 ) {
         str = dice1.toString() + " - " + dice2.toString() + " - "
               + dice3.toString() + " : " + "\t HalfHouse:  " + isHalfHouse();
         // checks odd
         if ( odds > 0 ) {

            str = dice1.toString() + " - " + dice2.toString() + " - "
                  + dice3.toString() + " : " + "\t HalfHouse:  "
                  + (isHalfHouse()) + "\tOdds: -" + odds;

         }
         // checks even
         else if ( evens > 0 ) {
            str = dice1.toString() + " - " + dice2.toString() + " - "
                  + dice3.toString() + " : " + "\t HalfHouse:  "
                  + (isHalfHouse()) + "\tEvens: -" + evens;
         }

      }
      // If it's a sequence
      else if ( sequence() ) {
         str = dice1.toString() + " - " + dice2.toString() + " - "
               + dice3.toString() + " : " + "\t Sequence:  " + getScore();
      }
      else
         str = dice1.toString() + " - " + dice2.toString() + " - "
               + dice3.toString() + " : ";
      return str;

   }

}





//Enum type rank 

enum Rank
    {
        ACE, TWO, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    
    
 class Die {

   // INSTANCES variables
   private int          sides; // for the number of sides

   private int          face; // for the value of face

   // creating random object from Random class
   public static Random rand = new Random();

   // Constructor
   public Die() {
      sides = 6;
      roll();

   }

   /**
    * roll method will roll the die object
    * sets face to a random value between 1 and sides,
    * and returns face of the die .
    * 
    */
   public int roll() {
      face = rand.nextInt( sides ) + 1;

      return face;

   }

   /**
    * getFace method will return the current value of the
    * face.
    * 
    * 
    */

   public int getFace() {

      return face;
   }

   /**
    * toString method will return the face value as a String
    * .
    * 
    */
   public String toString() {

      String str = " " + face;

      return str;

   }

}


