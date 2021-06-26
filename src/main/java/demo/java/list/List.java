package demo.java.list;

/**
 * 用链表实现的栈，内含push pop peak 方法
 * <p>
 * https://blog.csdn.net/ly969434341/article/details/51484669
 *
 * @author ly
 */
public class List<T> {

  Node<T> header;//栈顶元素
  int elementCount;//栈内元素个数
  int size;//栈的大小

  /**
   * 通过构造器自定义栈的大小
   *
   * @param size
   */
  public List(int size) {
    header = null;
    elementCount = 0;
    this.size = size;
  }

  public void setHeader(Node<T> header) {
    this.header = header;
  }

  public Node<T> getHeader() {
    return this.header;
  }

  public boolean isFull() {
    return elementCount == size;
  }

  public boolean isEmpty() {
    return elementCount == 0;
  }

  /**
   * 入栈
   *
   * @param value
   */
  public void push(T value) {
    if (this.isFull()) {
      throw new RuntimeException("ListStack is Full");
    }
    //注意这里面试将原来的header作为参数传入，然后以新new出来的Node作为header
    header = new Node<>(value, header);
    elementCount++;
  }

  /**
   * 出栈
   *
   * @return
   */
  public T pop() {
    if (this.isEmpty()) {
      throw new RuntimeException("ListStack is empty");
    }
    T object = header.getElement();
    header = header.next;
    elementCount--;
    return object;
  }

  /**
   * 返回栈顶元素
   */
  public T peak() {
    if (this.isEmpty()) {
      throw new RuntimeException("ListStack is empty");
    }
    return header.getElement();
  }
}