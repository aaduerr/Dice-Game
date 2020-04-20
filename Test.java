import java.util.Scanner;

public class Test{
  int numbers[] = {1,2,3,4,5,6,7,8,9};

  public void check(int x){
    int amount = x;
    int total = 0;
    int pointer = numbers.length-1;
    int tries = 0;
    int temp = 0;

    while(total < amount && pointer >= 0 && tries <= this.numbers.length-1){
      temp = this.numbers[pointer];
      // System.out.println("Adding " + temp);
      total += temp;
      if(total > amount){
        // System.out.println("Too much, removing " + temp);
        total -= temp;
      }
      pointer -= 1;
      if(pointer < 0 && total != amount){
        // System.out.println("Didn't work, retrying.");
        tries += 1;
        total = 0;
        pointer = this.numbers.length-1-tries;
        // System.out.println("Tries is " + tries);
        // System.out.println("Pointer is " + pointer);
      }
    }
    if(total == amount){
      System.out.println("Sucess\n===========\n");
    }
    else{
      System.out.println("Failure\n==========\n");
    }
  }

  public static void main(String[] args) {
    Test game = new Test();
    Scanner scan = new Scanner(System.in);
    int entry = 0;

    while(true){
      entry = 0;
      System.out.println("Enter a number:");
      while(entry == 0){
        if(scan.hasNextInt()){
          entry = scan.nextInt();
          if(entry < 0){
            entry = 0;
          }
        }
        else{
          System.out.println("Please make a valid entry");
          scan.nextLine();
        }
      }

      game.check(entry);
    }
  }
}
