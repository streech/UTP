/**
 *
 *  @author Stryszawski Emil S20607
 *
 */

package zad3;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {

  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
    Function<String,List<String>> flines = line -> {
      try {
        return Files.readAllLines(Paths.get(line));
      } catch (IOException e1) {
        return null;
      }
    };

    Function<List<String>,String> join = line -> String.join("", line);

    Function<String,List<Integer>> collectInts = s -> {
      List<Integer> ints = new ArrayList<>();
      Pattern p = Pattern.compile("-?\\d+");
      Matcher m = p.matcher(s);

      while(m.find()) ints.add(Integer.parseInt(m.group()));

      return ints;
    };

    Function<List<Integer>,Integer> sum = e -> {
      int s = 0;

      for (Integer i : e) s += i;
      return s;
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
