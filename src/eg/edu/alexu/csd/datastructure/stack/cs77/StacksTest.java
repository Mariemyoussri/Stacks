package eg.edu.alexu.csd.datastructure.stack.cs77;

import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;


import org.junit.jupiter.api.Test;

class StacksTest {

  Stack s = new Stack();
  
  StacksTest() {
    s.push(1);
    s.push(2);
    s.push(3);
    s.push(4);
  }
  
@Test
  void testPop() {
	  //first  pop
    assertEquals(4,s.pop());
    assertEquals(3,s.peek());
    //second pop
    assertEquals(3,s.pop());
    assertEquals(2,s.peek());
    s.pop();
    s.pop();
    assertEquals(true,s.isEmpty());
    //checking for exception when you remove from empty stack
    assertThrows(EmptyStackException.class,() -> s.pop());    
  }

  @Test
  void testPeak() {
    assertEquals(4,s.peek());
    s.push(5);
    assertEquals(5,s.peek());
  }

  @Test
  void testPush() {
    assertEquals(4,s.peek());
    assertEquals(4,s.size());
    s.push(6);
    assertEquals(6,s.peek());
    assertEquals(5,s.size());
  }

  @Test
  void testIsEmpty() {
    assertEquals(false,s.isEmpty());
    s.pop();
    s.pop();
    s.pop();
    s.pop();
    assertEquals(true,s.isEmpty());
  }
  
  @Test
  void testSize() {
    assertEquals(4,s.size());
  }

}
