package demo.java;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function5;

public class FunctionTest {
  public static void main(String[] args) {
    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

    Function2<Integer, Integer, Integer> sum2 = new Function2<Integer, Integer, Integer>() {
      @Override
      public Integer apply(Integer a, Integer b) {
        return a + b;
      }
    };

    Function2<Integer, Integer, Integer> sumFromMethod =
        Function2.of(FunctionTest::add);
    System.out.println(sumFromMethod.apply(1, 2));

    // composition
    Function1<Integer, Integer> plusOne = a -> a + 1;
    Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
    Function1<Integer, Integer> composed = plusOne.andThen(multiplyByTwo);
    System.out.println(composed.apply(2));

    // partial applied function
    Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum5 =
        (a, b, c, d, e) -> a + b + c + d + e;
    Function2<Integer, Integer, Integer> add6 = sum5.apply(2, 3, 1);
    System.out.println(add6.apply(4, 3));

    // Currying
    Function3<Integer, Integer, Integer, Integer> sum3 =
        (a, b, c) -> a + b + c;
    final Function1<Integer, Function1<Integer, Integer>> add2 =
        sum3.curried().apply(2);
    System.out.println(add2.apply(4).apply(3));
  }

  public static int add(int a, int b) {
    return a + b;
  }
}
