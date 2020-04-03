package eg.edu.alexu.csd.datastructure.stack.cs77;

public class Node {
  Object value;
  Node next;

  
  /**
   * @return the value of the node.
   */
  public Object getvalue() {
    return value;
  }
  
  /**
   * @return the next node of the current node
   */
  public Node getNext() {
    return next;
  }

  /**
   * @param element object
   * sets the value of element in node
   */
  public void setValue(Object element) {
    value = element;
  } 

  /**
   * @param node Node
   * sets the next node of the previous node
   */
  public void setNext(Node node) {
    next = node;
  }

  /**
 * @param element object
 * @param node Node
 * constructor to the class.
 */
public Node(Object element, Node node) {
    value = element;
    next = node;
  }

}
