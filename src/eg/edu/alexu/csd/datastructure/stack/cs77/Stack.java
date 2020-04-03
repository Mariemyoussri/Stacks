package eg.edu.alexu.csd.datastructure.stack.cs77;

import java.util.EmptyStackException;

public class Stack implements IStack {
 
  Node top = null;
  public int size = 0;

  @Override
  public Object pop() {
    if (top == null) {
      throw new EmptyStackException();
    }
    Object temp = top.getvalue();
    top = top.getNext();
    size--;
    return temp;
  }

  @Override
  public Object peek() {
    if (top == null) {
      throw new EmptyStackException();
    }
    return top.getvalue();
  }

  @Override
  public void push(Object element) {
    Node newNode = new Node(element,top);
    top = newNode;
    size++;
  }

  @Override
  public boolean isEmpty() {
    if (top == null) {
      return true;
    }
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  /**
 * This function prints the stack
 */
public void show() {
    Node temp = new Node(null,null);
    temp = top;
    while (temp != null) {
      System.out.println(temp.getvalue());
      temp = temp.getNext();
    }
  }
}
