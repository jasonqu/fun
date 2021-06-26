package demo.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TryTest {
  public static void main(String[] args) {
    int x = readInt("Enter an Int that you'd like to divide:\n");
    int y = readInt("Enter an Int that you'd like to divide by:\n");
    divide(x, y);
  }

  private static int readInt(String prelude) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.print(prelude);
      String input = reader.readLine();
      return Integer.parseInt(input);
    } catch (Exception e) {
      System.out.println("You must've divided by zero or entered something that's not an Int. Try again!");
      System.out.println("Info from the exception: " + e.getMessage());
      return -1;
      //throw e;
    }
  }

  private static int divide(int dividend, int divisor) {
    try {
      System.out.println("Result of " + dividend + "/" + divisor + " is: " + dividend / divisor);
      return dividend / divisor;
    } catch (ArithmeticException e) {
      System.out.println("You must've divided by zero or entered something that's not an Int. Try again!");
      System.out.println("Info from the exception: " + e.getMessage());
      throw e;
    }
  }
}
