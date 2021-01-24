/**@author Stryszawski Emil S20607*/

package zad1;

import java.io.*;
import java.util.*;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  private static String sort(String s) { return Stream.of(s.split("")).sorted().collect(Collectors.joining()); }

  public static List<Set<String>> getAnagrams(InputStreamReader isr) {
    Map<String, Set<String>> map = new BufferedReader(isr).lines().collect(Collectors.groupingBy(Main::sort, Collectors.toCollection(TreeSet::new)));
    Optional<Set<String>> optionalMax = map.values().stream().max(Comparator.comparingInt(Set::size));
    Integer max = optionalMax.map(Set::size).orElse(null);
    return map.values().stream().filter(set -> set.size() == max).sorted(Comparator.comparing(set -> set.iterator().next())).collect(Collectors.toList());
  }

  public static void main(String[] args) throws IOException {
    getAnagrams(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream())).forEach(list -> System.out.println(list.toString().replaceAll("[\\[,\\]]", "")));
  }
}