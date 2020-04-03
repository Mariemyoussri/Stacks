package eg.edu.alexu.csd.datastructure.stack.cs77;

public class ExpressionEvaluator implements IExpressionEvaluator {

/**
* this function tells which parameter has higher precedence.
* @param operator1 char 
* @param operator2 char
* @return true if operator1 has higher precedence or has the same precedence
* as operator2 otherwise it returns false
*/
  public Boolean precedence(char operator1, char operator2) {
    Boolean higher = true;
    if ((operator1 == '+' || operator1 == '-') && (operator2 == '*' || operator2 == '/')) {
      higher = false;
    }
    return higher;
  }
  
/**
 * This function helps me in the negative bracket in my expression.
 * @param expression String
 * @return  an object array that contains the string which is the complete bracket
 * and the counter 'number of characters in the string
 */
  public Object[] bracket(String expression) {
    Stack openBracket = new Stack();
    int i = 0;
    String bracket = "" + expression.charAt(i);
    openBracket.push(expression.charAt(i));
    for (i = 1; i < expression.length() && !openBracket.isEmpty(); i++) {
      bracket += expression.charAt(i);
      if (operator(expression.charAt(i)) == 1) {
        openBracket.push(expression.charAt(i));
      }
      else if (operator(expression.charAt(i)) == 2) {
        openBracket.pop();
      }
    }
    Object[] brackets = {bracket,i};
    return brackets;
  }
  
  /**
 * This function determines whether the character given to it is an operator. 
 * open bracket ,closed bracket , space or a special char "Symbol like @ #"
 * @param operator character
 * @return 0 if the char is a digit or a letter ,returns 1 if the char is open bracket,
 * returns 2 if the char is a close bracket, returns 3 if the char is a valid operator,
 * returns 4 if space and returns 5 if the char is neither a digit nor a letter 
 */
  public int operator(char operator) {
    int validOperator = 0;
    if (operator == '+'  || operator == '-' || operator == '*' || operator == '/') {
      validOperator = 3;
    }
    else if (operator == '(' || operator == '[' || operator == '{') {
      validOperator = 1;
    }
    else if (operator == ')' || operator == '[' || operator == '{') {
      validOperator = 2;
    }
    else if (operator == ' ') {
      validOperator = 4;
    }
    else if (!Character.isLetterOrDigit(operator)) {
      validOperator = 5;
    }
    return validOperator;
  }
  
  /**
 * This function helps me handle numbers with more than one digit.
 * @param expression String
 * @return an object array containing the string which is the number and the counter
 * which is the size if the string
 */
  public Object[] number(String expression) {
    Object[] num = new Object[2];
    int i = 0;
    String number = "";
    for (i = 0; i < expression.length() && operator(expression.charAt(i)) == 0; i++) {
      number += expression.charAt(i);
    }
    num[0] = number;
    num[1] = i;
    return num;
  }
  
  /**
 * In this function i handled the expression the user puts if there are errors.
 * in the expression and handled the negative sign with dummy zero 
 * @param expression String
 * @return The correct handled expression with negative numbers as (0-num)
 * and returns null in case of the expression being wrong  
 */
  public String checkString(String expression) {
    String newExpression = "";
    Stack openBracket = new Stack();
    Stack closeBracket = new Stack();
    expression = expression.replaceAll(" ","");
    if (operator(expression.charAt(expression.length()-1)) == 3 || operator(expression.charAt(expression.length()-1)) == 1) {
      return null;
    }
    for (int i = 0; i < expression.length(); i++) {
      if (i == 0 && expression.charAt(i) == '-') {
        if (operator(expression.charAt(i + 1)) == 0) {
          Object[] digits = number(expression.substring(i + 1));
          String num = (String)digits[0];
          i += (int)digits[1] ;
          newExpression += "(0-" + num + ")";
        }
        else if (operator(expression.charAt(i + 1)) == 1) {
          Object[] digits = bracket(expression.substring(i + 1));
          String bracket = (String)digits[0];
          i += (int)digits[1] ;
          newExpression += "((0-1)*" + bracket + ")";
        }
        else if (expression.charAt(i + 1) == '-') {
          return null;
        }
      }
      else if (i == 0 && operator(expression.charAt(i)) == 3) {
        if(expression.charAt(i) != '-' && operator(expression.charAt(i + 1)) == 0) {
        	return null;
        }
      }
      else if (operator(expression.charAt(i)) == 3 || operator(expression.charAt(i)) == 1) {
        if (operator(expression.charAt(i)) == 3) {
          newExpression += expression.charAt(i);
        }
        else if (operator(expression.charAt(i)) == 1) {
          openBracket.push(expression.charAt(i));
          newExpression += expression.charAt(i);
        }
        if (expression.charAt(i + 1) == '-' && operator(expression.charAt(i + 2)) == 0) {
          Object[] digits = number(expression.substring(i + 2));
          String num = (String)digits[0];
          i += (int)digits[1] + 1;
          newExpression += "(0-" + num + ")";
        }
        else if (expression.charAt(i + 1) == '-' && operator(expression.charAt(i + 2)) == 1) {
          Object[] digits = bracket(expression.substring(i + 2));
          String bracket = (String)digits[0];
          i += (int)digits[1] + 1;
          newExpression += "((0-1)*" + bracket + ")";
        }
        else if (expression.charAt(i + 1) == '+' && operator(expression.charAt(i + 1)) == 0) {
          i++;
        }
        else if (operator(expression.charAt(i + 1)) == 2 || (operator(expression.charAt(i + 1)) == 3  && expression.charAt(i) != '-')) {
          return null;
        }
      }
      else if (operator(expression.charAt(i)) == 2) {
        closeBracket.push(expression.charAt(i));
        newExpression += expression.charAt(i);
        if(i < expression.length() - 1 && operator(expression.charAt(i + 1)) == 1) {
          return null;
        }
      }
      else if (operator(expression.charAt(i)) == 5 ) {
        return null;
      }
      else if (i < expression.length() - 1 && Character.isLetter(expression.charAt(i)) && Character.isLetter(expression.charAt(i + 1))) {
        return null;
      }  
      else if (i < expression.length() - 1 && Character.isLetter(expression.charAt(i)) && Character.isDigit(expression.charAt(i + 1))) {
          return null;
        } 
      else if (i < expression.length() - 1 && Character.isDigit(expression.charAt(i)) && Character.isLetter(expression.charAt(i + 1))) {
          return null;
        } 
      else {
        newExpression += expression.charAt(i);
      }
    }
    while (!openBracket.isEmpty() && !closeBracket.isEmpty()) {
      openBracket.pop();
      closeBracket.pop();
    }
    if (!openBracket.isEmpty() || !closeBracket.isEmpty()) {
      return null;
    }
    return newExpression;
  }
   
  @Override
  public String infixToPostfix(String expression) {
    String postFix = "";
    Stack operators = new Stack();
    expression = checkString(expression);
    expression = checkString(expression);
    if (expression != null) {
      for (int i = 0; i < expression.length(); i++) {
        if (operator(expression.charAt(i)) == 0) {
          Object[] digits = number(expression.substring(i));
          postFix += (String)digits[0];
          postFix += " ";
          i += (int)digits[1] - 1;
        }
        else if (operator(expression.charAt(i)) == 3) {
          while (!operators.isEmpty() && operator((char)operators.peek()) != 1 && precedence((char)operators.peek(),expression.charAt(i))) {
            postFix += operators.pop();
            postFix += " ";
    	  }
          operators.push(expression.charAt(i));
        }
        else if (operator(expression.charAt(i)) == 1) {
          operators.push(expression.charAt(i));
        }
        else if (operator(expression.charAt(i)) == 2) {
          while (!operators.isEmpty() && operator((char)operators.peek()) != 1) {
            postFix += operators.pop();
            postFix += " ";
          }
          operators.pop();
        }
      }
    }
    else {
      throw new RuntimeException("Wrong expression");
    }
    while (! operators.isEmpty()) {
      postFix += operators.pop();
      postFix += " ";
    }
    return postFix;
  }

  @Override
  public int evaluate(String expression) {
    Stack evaluate = new Stack();
    float result = 0;
    for (int i = 0; i < expression.length(); i++) {
      float x, y;
      if (!Character.isDigit(expression.charAt(i)) && operator(expression.charAt(i)) == 0 ) {
        throw new IllegalStateException("Wrong input");
      }
      if (operator(expression.charAt(i)) == 0) {
        String num = "";
        while (expression.charAt(i) != ' ') {
          num += expression.charAt(i);
          i++;
        }
        evaluate.push(num); 
      }
      else if (operator(expression.charAt(i)) == 3) {
        switch (expression.charAt(i)) {
          case '+':
            x = Float.parseFloat(evaluate.pop().toString());
            y = Float.parseFloat(evaluate.pop().toString());
            result = y + x;
            evaluate.push(result);
            break;
          case '-':
            x = Float.parseFloat(evaluate.pop().toString());
            y = Float.parseFloat(evaluate.pop().toString());
            result = y - x;
            evaluate.push(result);
            break;
          case '*':
            x = Float.parseFloat(evaluate.pop().toString());
            y = Float.parseFloat(evaluate.pop().toString());
            result = y * x;
            evaluate.push(result);
            break;
          case '/':
            x = Float.parseFloat(evaluate.pop().toString());
            y = Float.parseFloat(evaluate.pop().toString());
            if (x != 0) {
              result = y / x;
              evaluate.push(result);
            }
            else {
              throw new ArithmeticException("Error: Division by zero");
            }
            break;
          default :
            System.out.println("Invalid operator");
        }
      }
    } 
    return (int)result;
  }
}
