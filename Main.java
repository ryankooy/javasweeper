package Minesweeper;

import java.util.Scanner;

public class Main {

  static int intErrorTrap (int x, int y) {
    int max, min;

    if (x < y) {
      min = x;
      max = y;
    } else {
      min = y;
      max = x;
    }

    int input;
    boolean loopEnd;
    String wordcheck;

    do {
      System.out.println("Please enter an integer between " + min + " to " + max + ".");
      Scanner userInput = new Scanner(System.in);

      try {
        input = userInput.nextInt();

        if (input > max || input < min) {
          loopEnd = false;
          System.out.println("Invalid input.");
          return -1;
        } else {
          loopEnd = true;
          System.out.println(input + " is a valid input.");
          return input;
        }
      }
      
      catch (Exception e) {
        loopEnd = false;
        wordcheck = userInput.next();
        System.out.println("Invalid input.");
        return 0;
      }
    } while (loopEnd == false);
  }

  public static void main(String[] args) {
    System.out.println("Enter width.");
    int x = intErrorTrap(0, 60);
    
    System.out.println("Enter height.");
    int y = intErrorTrap(0, 30);

    System.out.println("Enter difficulty.");
    int d = intErrorTrap(0, 100);

    new Minesweeper(x, y, d);
  }
}