// Andrew Duerr
// 6/15/19
// A simple numbers game to test my coding in Java.

// Rules:
// Input numbers to be removed from a list.
// These numbers must add up to the rolled number.

import java.util.Scanner;
import java.util.Random;

public class Game{
  Scanner scan = new Scanner(System.in);
  Random rand = new Random();

  //initial game state
  int numbers[] = {1,2,3,4,5,6,7,8,9};
  int available = 9;
  int rolled = 0;

  public void getScore(){
    int tally = 0;
    for(int i = 0; i < this.numbers.length; i++){
      if(this.numbers[i] != 0){
        tally++;
      }
    }
    if(tally == 0){
      System.out.println("You won!");
    }
    else{
      System.out.print("Your score is: ");
      for(int j = 0; j < this.numbers.length; j++){
        if(this.numbers[j] != 0){
          System.out.print(this.numbers[j]);
        }
      }
      System.out.println();
    }
  }

  public void roll(){
      this.rolled = rand.nextInt(12) + 1;
      System.out.println("\nYou rolled a " + this.rolled);
  }

  public boolean hasOption(int x){
    int amount = x;
    int total = 0;
    int pointer = numbers.length-1;
    int tries = 0;
    int temp = 0;

    while(total < amount && pointer >= 0 && tries <= this.numbers.length-1){
      temp = this.numbers[pointer];
      total += temp;
      if(total > amount){
        total -= temp;
      }
      pointer -= 1;
      if(pointer < 0 && total != amount){
        tries += 1;
        total = 0;
        pointer = this.numbers.length-1-tries;
      }
    }
    if(total == amount){
      return true;
    }
    else{
      return false;
    }
  }

  public void printAvail(){
    System.out.print("Still available: [ ");
    for(int i = 0; i < numbers.length; i++){
      if(this.numbers[i] != 0){
        System.out.print(i+1 + " ");
      }
    }
    System.out.print("]\n");
  }

  public void getNumbers(){
    int sum = 0;
    int input = 0;
    int tally = 0;
    int used[] = {0,0,0,0,0,0,0,0,0};
    while(sum < this.rolled){
      this.printAvail();
      System.out.println("Current total: " + sum);
      while(!this.scan.hasNextInt()){
        System.out.println("Input must be a number.");
        this.scan.nextLine();
      }
      input = this.scan.nextInt();
      if(input < 10 && input > 0 && this.numbers[input-1] != 0){
        this.numbers[input-1] = 0;
        used[input-1] = 1;
        tally++;
        sum += input;
      }
      else{
        System.out.println("Please choose a valid number.");
      }
      if(sum > this.rolled){
        System.out.println("Too much. Try again.");
        //reset
        tally = 0;
        sum = 0;
        for(int i = 0; i < this.numbers.length; i++){
          if(used[i] == 1){
            this.numbers[i] = i+1;
          }
        }
      }
      else if(sum == this.rolled){
        //worked, edit available numbers
        this.available -= tally;
        System.out.println("Accepted.\n");
      }
      else{
        System.out.println("Choose another.\n");
      }
    }
  }

  public void getChoice(){
    String option = "none";
    option = this.scan.nextLine();
    if(option.equals("roll")){
      roll();
      if(this.hasOption(this.rolled)){
        this.getNumbers();
      }
      else{
        this.printAvail();
        System.out.println("No options left. Game over.");
        // this.getScore();
        this.available = 0;
      }
    }
    else if(option.equals("check")){
      this.printAvail();
    }
    else if(option.equals("quit")){
      this.getScore();
      this.available = 0;
    }
    else{
      System.out.println("Please use either 'roll', 'check', or 'quit'");
      this.getChoice();
    }
  }

  public static void main(String[] args) {
    Game dice = new Game();
    System.out.println("Let's play a game.");
    System.out.println("Type \t'roll' to roll the dice,\n\t'check' to see available numbers, or\n\t'quit' to end game.");
    while(dice.available > 0){
      dice.getChoice();
    }
    dice.getScore();
    dice.scan.close();
  }
}
