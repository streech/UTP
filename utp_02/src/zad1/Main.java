/**
 *
 *  @author Stryszawski Emil S20607
 *
 */

package zad1;

import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12); /*<-- tu dopisać inicjację elementów listy */
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" ); /*<-- tu dopisać inicjację elementów listy */
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = new Selector<Integer>() { /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
      @Override
      public boolean select(Integer integer) {
        return integer < 10;
      }
    };
    Mapper<Integer, Integer> map = new Mapper<Integer, Integer>() { /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
      @Override
      public Integer map(Integer integer) {
        return integer + 10;
      }
    };
    return ListCreator /*<-- zwrot wyniku uzyskanego przez wywołanie statycznej metody klasy ListCreator: */
            .collectFrom(src)
            .when(sel)
            .mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector<String> sel = new Selector<String>() { /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
      @Override
      public boolean select(String s) {
        return s.length() > 3;
      }
    };
    /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
//    Mapper<String, Integer> map = ;
    return ListCreator /*<-- zwrot wyniku uzyskanego przez wywołanie statycznej metody klasy ListCreator: */
            .collectFrom(src)
            .when(sel)
            .mapEvery(s -> s.length() + 10);
  }

  public static void main(String[] args) {
    new Main();
  }
}