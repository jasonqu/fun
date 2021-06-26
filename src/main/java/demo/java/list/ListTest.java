package demo.java.list;

import java.util.LinkedList;

public class ListTest {

  static void setHead(List<Integer> stack, int i) {
    stack.pop();
    stack.push(i);
  }

  static void printList(List<Integer> stack) {
    Node<Integer> node = stack.getHeader();
    while(node != null) {
      System.out.print(node.element + " -> ");
      node = node.next;
    }
    System.out.println("null");
  }

  public static void main(String[] args) {
    List<Integer> stack = new List<>(10);
    // setHead(stack, 0);

    for(int i = 3; i >= 1; i --)
      stack.push(i);

    printList(stack);
    setHead(stack, 0);
    printList(stack);

    while(!stack.isEmpty()) {
      System.out.println(stack.pop());
    }

    LinkedList<String> listOfWords = new LinkedList<>();
    listOfWords.add("His life story is recounted in two fascinating volumes of autobiography.");
    listOfWords.add("New facts were brought to light by scholars.");
    listOfWords.add("to be or not to be?");
    listOfWords.add("This evidence did not come to light until after the trial.");
    listOfWords.add("Are you able to shed any light on this subject?");

    // map 映射成为单词的集合
    LinkedList<String[]> listOfArray = new LinkedList<>();
    for(String str: listOfWords) {
      listOfArray.add(str.split(" "));
    }

    // filter，找出字符数大于20的句子
    LinkedList<String[]> filterLengthBiggerThan20 = new LinkedList<>();
    for(String str: listOfWords) {
      if(str.length() > 20) {
        filterLengthBiggerThan20.add(str.split(" "));
      }
    }

    // reduce fold
    // 对Linked List 求和
    LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);

    int sum = 0;
    for(Integer i : list) {
      sum += i;
    }
    System.out.println("sum of list " + sum);

    // 求出所有句子的单词数总和
    int wc = 0;
    for(String str: listOfWords) {
      wc += str.split(" ").length;
    }
    // 或者
    wc = 0;
    for(String[] strArr: listOfArray) {
      wc += strArr.length;
    }

    // reduce
    int sum2 = list.stream().reduce(0, (Integer a, Integer b) -> a + b);
  }
}
