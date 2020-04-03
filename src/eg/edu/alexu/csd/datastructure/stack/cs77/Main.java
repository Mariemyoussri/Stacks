package eg.edu.alexu.csd.datastructure.stack.cs77;

import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner; 

public class Main {

  public static void menu() {
    System.out.println("Choose an action:");
    System.out.println("===================================================");
    System.out.println("1: Push");
    System.out.println("2: Pop");
    System.out.println("3: Peek");
    System.out.println("4: Get size");
    System.out.println("5: Check if empty"); 
    System.out.println("6: Exit");
    System.out.println("===================================================");
  }
  
  /**
   * This main handles the stacks and operations on it.
   * @param args 
   */
  public static void main(String[] args) {
    Stack stack = new Stack();  
    Scanner sc = new Scanner(System.in);
    Boolean Exit = false;
    while (!Exit) {
      menu();
      char choice = sc.next().charAt(0);
      sc.nextLine();
      switch (choice) {
        case '1':
          Boolean done = false;
          Object element = 0;
          while (!done) {
            try {
              System.out.println("Enter element");
              element = sc.nextLine();
              done = true;
            }
            catch (InputMismatchException e) {
              sc.next();
              System.out.println("Invalid input\n");
            }
          }
          stack.push(element);
          break;
        case '2':
          try {
            stack.pop();
          }
          catch (EmptyStackException e) {
            System.out.println("Stack is empty can't pop");
          }
          break;
        case '3':
          try {
            System.out.println("Element at the top is: " + stack.peek());
          }
          catch (EmptyStackException e) {
            System.out.println("Stack is empty can't peek");
          }
          break;
        case '4':
          System.out.println("Size of the stack is: " + stack.size());
          break;
        case '5':
          System.out.println(stack.isEmpty());
          break;
        case '6':
          Exit = true;
          break;
        default:
          System.out.println("Wrong choice please choose again");
      }
    }


  }
}
