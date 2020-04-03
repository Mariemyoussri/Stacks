package eg.edu.alexu.csd.datastructure.stack.cs77;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionEvaluatorTest {
  
  ExpressionEvaluator test = new ExpressionEvaluator();
 
  int value;
  String postFix;
  String expectedPostFix;
  String expression;
  
  /**
   * Testing the two functions with valid expressions 
   */
  @Test
  void test1() {
    //Numeric expressions
    //Test one
    expression = "2 + 3 * 4";
    expectedPostFix = "2 3 4 * + ";
    postFix = test.infixToPostfix(expression);
    value = test.evaluate(postFix);
    assertEquals(expectedPostFix, postFix);
    assertEquals(14, value);
   
    //Test two
    expression = "( 6 / 2 ) - 3 + 4 * 2";
    expectedPostFix = "6 2 / 3 - 4 2 * + ";
    postFix = test.infixToPostfix(expression);
    value = test.evaluate(postFix);
    assertEquals(expectedPostFix, postFix);
    assertEquals(8, value);
    
    //Test three
    expression = "(( 20 + 80 ) * 2) - ( 15 * 7)";
    expectedPostFix = "20 80 + 2 * 15 7 * - ";
    postFix = test.infixToPostfix(expression);
    value = test.evaluate(postFix);
    assertEquals(expectedPostFix, postFix);
    assertEquals(95, value);
  }
  @Test
  void test2() {
    //Testing expressions with letters and alphanumeric expressions
    //Test one
	expression = "a * b + 5";
	expectedPostFix = "a b * 5 + ";
	postFix = test.infixToPostfix(expression);
	assertEquals(expectedPostFix, postFix);
    assertThrows(IllegalStateException.class,() -> test.evaluate(postFix));    
  
    //Test two
    expression = "(a / (b - c + d)) * (e - a) * c";
	expectedPostFix = "a b c - d + / e a - * c * ";
	postFix = test.infixToPostfix(expression);
	assertEquals(expectedPostFix, postFix);
    assertThrows(IllegalStateException.class,() -> test.evaluate(postFix));
    
    //test three
    expression = "a / b - c + d * e - a * c";
	expectedPostFix = "a b / c - d e * + a c * - ";
	postFix = test.infixToPostfix(expression);
	assertEquals(expectedPostFix, postFix);
    assertThrows(IllegalStateException.class,() -> test.evaluate(postFix));    
 }

  /**
   * Testing invalid expressions
   */
  @Test
  void test3() {
    //Test one: more than one operator
    expression = "3*/6";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    expression = "9++*-7";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
	
    //Test two: missing open or closing bracket
    expression = "(a / (b - c + d) * (e - a) * c";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    expression = "(a * b + 5";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    
    //Test three: more than one letter ex:ab
    expression = "ab+ohj + k";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    
    //Test four: digit and char 23X or x12
    expression = "2x+6";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    expression = "x88x+7";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    
    //Test five: operator at the beginning or the end
    expression = "* 3 + 5";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    expression = "6-9/4+";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
    
    //Test six: two brackets without * in between
    expression = "a / (b - c +d) (e - a) * c";
    assertThrows(RuntimeException.class,() -> test.infixToPostfix(expression));
  }
  
  /**
 * Testing negative cases
 */
@Test
  void test4() {
    //Test one: negative integer
	expression = "-2 + 3 * 4";
    expectedPostFix = "0 2 - 3 4 * + ";
    postFix = test.infixToPostfix(expression);
    value = test.evaluate(postFix);
    assertEquals(expectedPostFix, postFix);
    assertEquals(10, value);
    
    expression = "4+6*-1";
    expectedPostFix = "4 6 0 1 - * + ";
    postFix = test.infixToPostfix(expression);
    value = test.evaluate(postFix);
    assertEquals(expectedPostFix, postFix);
    assertEquals(-2, value);
    
    //Test two:negative bracket (handled negative bracket with ((0-1)*(bracket))
    expression = "(a / -(b - c)+9)";
    expectedPostFix = "a 0 1 - b c - * / 9 + ";
    postFix = test.infixToPostfix(expression);
    assertEquals(expectedPostFix, postFix);
    
    expression = "(1-3)/-(1--2)*6";
    expectedPostFix = "1 3 - 0 1 - 1 0 2 - - * / 6 * ";
    postFix = test.infixToPostfix(expression);
    value = test.evaluate(postFix);
    assertEquals(expectedPostFix, postFix);
    assertEquals(4, value);
    
    expression = "-(-2*3+5)+8";
    expectedPostFix = "0 1 - 0 2 - 3 * 5 + * 8 + ";
    postFix = test.infixToPostfix(expression);
    value = test.evaluate(postFix);
    assertEquals(expectedPostFix, postFix);
    assertEquals(9, value);
  }
  
}
