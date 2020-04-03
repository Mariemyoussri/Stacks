package eg.edu.alexu.csd.datastructure.stack.cs77;
import java.util.Scanner; 

/**
 * @author Mariam
 * This class is a main for the application part.
 * it handles the evaluation of expressions with char in it as it scans it from
 * the user
 */
public class Main2 {

  /**
 * This function prints the menu.
 */
public static void menu() {
    System.out.println("Choose an action:");
    System.out.println("====================================");
    System.out.println("1-Infix to postfix");
    System.out.println("2-Evaluate");
    System.out.println("3-Exit");
    System.out.println("====================================");
  }
  public static void main(String[] args) {
    Boolean done = false;
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    Scanner sc = new Scanner(System.in);
    String expression = "";
    String postFix = "";
    while(!done) {
      menu();
      char choice = sc.next().charAt(0);
      sc.nextLine();
      if (choice == '1') {
        try {
          System.out.println("Enter expression:");
          expression = sc.nextLine();
          postFix = evaluator.infixToPostfix(expression);
          System.out.println("PostFix expression is: " + postFix);
        }
        catch (RuntimeException e){
          System.out.println("Invalid expression");
        }
      }
      else if (choice == '2') {
        if (postFix == null) {
          System.out.println("No expression to evaluate please enter expression");
          break;
        }
        for (int i = 0; i < postFix.length(); i++) {
          if (Character.isLetter(postFix.charAt(i))) {
            boolean ok = false;
            while (!ok) {
              try {
                System.out.println("Enter value of " + postFix.charAt(i));
            
                String value = sc.nextLine();
                float num = Float.parseFloat(value);
                if (num >= 0) {
                  postFix = postFix.replaceAll("" + postFix.charAt(i), value);
                }
                else {
                  num = num * -1;
                  postFix = postFix.replaceAll("" + postFix.charAt(i), "0 " + (int)num +" -");
                }
                ok = true;
              }
              catch (Exception e) {
                System.out.println("Invalid input");
              }
            }
          }
        }
        try {
          int evaluation = evaluator.evaluate(postFix);
          System.out.println("Result is: " + evaluation);
        }
        catch (Exception e) {
          System.out.println("Error Can't evaluate expression");
        }
      }
      else if (choice == '3') {
        done = true;
      }
    }
  }
}
