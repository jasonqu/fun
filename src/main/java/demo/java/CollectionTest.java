package demo.java;

import io.vavr.collection.Array;
import io.vavr.collection.List;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CollectionTest {
  public static void main(String[] args) {
    // = ["1", "2", "3"] in Java 8
    System.out.println(Arrays.asList(1, 2, 3)
        .stream()
        .map(Object::toString)
        .collect(Collectors.toList()));

    // in Vavr
    System.out.println(Array.of(1, 2, 3)
        .map(Object::toString));
  }

  String join(String... words) {
    StringBuilder builder = new StringBuilder();
    for(String s : words) {
      if (builder.length() > 0) {
        builder.append(", ");
      }
      builder.append(s);
    }
    return builder.toString();
  }

  String joinVavr1(String... words) {
    return List.of(words)
        .intersperse(", ")
        .foldLeft(new StringBuilder(), StringBuilder::append)
        .toString();
  }

  String joinVavr2(String... words) {
    return List.of(words).mkString(", ");
  }
}
