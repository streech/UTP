/**
 *
 *  @author Stryszawski Emil S20607
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    private final String filePath;
    private final List<String> words;
    private List<List<String>> anagramsList;

    public Anagrams(String filePath) {
        this.filePath = filePath;
        words = readAllWords();
    }

    public List<List<String>> getSortedByAnQty() {
        anagramsList = new ArrayList<>();
        List<String> innerList = new ArrayList<>();

        for (String word : words) {

            if (!innerList.contains(word)) {

                List<String> tmp = new ArrayList<>();

                for (String wordToCompare : words) {
                    if (isAnagram(word, wordToCompare)) {
                        innerList.add(wordToCompare);
                        tmp.add(wordToCompare);
                        tmp.sort(String::compareTo);
                    }
                }
                anagramsList.add(tmp);
            }
        }

        anagramsList.sort((l1, l2) -> {
            int compare = -(l1.size() - l2.size());
            return compare == 0 ? l1.get(0).compareTo(l2.get(0)) : compare;
        });
        return anagramsList;
    }

    private String sortWord(String word) {
        List<String> listWord = new ArrayList<>(Arrays.asList(word.split("")));
        Collections.sort(listWord);
        return listWord.toString();
    }

    private boolean isAnagram(String word1, String word2) {
        String sortedWord1 = sortWord(word1);
        String sortedWord2 = sortWord(word2);
        return sortedWord1.equals(sortedWord2);
    }

    private List<String> readAllWords() {
        List<String> resultList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String[] allWords = br.lines().collect(Collectors.joining(" ")).split("\\s+");
            resultList.addAll(Arrays.asList(allWords));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public String getAnagramsFor(String anagramsForWord) {
        StringBuilder sb = new StringBuilder();

        for (List<String> list : anagramsList) {

            if (list.contains(anagramsForWord)) {
                sb.append(anagramsForWord)
                        .append(": ")
                        .append(list.stream()
                                .skip(1)
                                .collect(Collectors.toList()));
                return sb.toString();
            }
        }

        return Collections.EMPTY_LIST.toString();
    }
}
