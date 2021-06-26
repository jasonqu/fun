package demo.java.list;


public class Node<T> {
  T element;
  Node<T> next;

  public Node(T element) {
    this(element, null);
  }

  public Node(T element, Node<T> n) {
    this.element = element;
    next = n;
  }

  public T getElement() {
    return element;
  }
}