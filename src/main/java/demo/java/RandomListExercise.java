package demo.java;

import io.vavr.Tuple2;

import java.util.*;

public class RandomListExercise {
  public static void main(String[] args) {
    Random rand = new Random();
    int length = 20;
    int[] randoms = new int[length];
    for(int i = 0; i < length; i++) {
      randoms[i] = rand.nextInt(10);
    }

    Map<Integer, Integer> stats = new HashMap<>();
    for(int v : randoms) {
      if(v % 2 == 0) {
        stats.put(v, stats.getOrDefault(v, 0) + 1);
      }
    }

    List<Tuple2<Integer, Integer>> list = new ArrayList<>();
    for(int k: stats.keySet()) {
      list.add(new Tuple2<>(k, stats.get(k)));
    }
    System.out.println(list);

    Collections.sort(list,new Comparator<Tuple2<Integer, Integer>>() {
      @Override
      public int compare(Tuple2<Integer, Integer> o1, Tuple2<Integer, Integer> o2) {
        return o2._2 - o1._2;
      }
    });

    System.out.println(list.subList(0, 3));
  }
}
