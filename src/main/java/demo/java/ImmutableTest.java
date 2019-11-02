package demo.java;

import io.vavr.collection.List;

public class ImmutableTest {
  public static void main(String[] args) {
    final int x = 10;
    //x++;

    List<Integer> list1 = List.of(1, 2, 3);
    List<Integer> list2 = list1.tail().prepend(0);
    System.out.print(list2);
  }

  class Person {
    final public String name;
    final public int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }
  }

}
