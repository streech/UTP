package zad1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {

    private Set<String> languages;
    private List<Set<String>> programmers;
    private Set<String> setOfProgs;

    public ProgLang(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {

            languages = new LinkedHashSet<>();
            programmers = new ArrayList<>();
            setOfProgs = new LinkedHashSet<>();

            br.lines().forEach(s -> {
                String[] split = s.split("\t");
                languages.add(split[0]);
                Set<String> set = new LinkedHashSet<>();
                for (int i = 1; i < split.length; i++) {
                    set.add(split[i]);
                    setOfProgs.add(split[i]);
                }
                programmers.add(set);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Set<String>> getLangsMap() {
        Map<String, Set<String>> langsMap = new LinkedHashMap<>();
        Iterator<String> keysIter = languages.iterator();
        Iterator<Set<String>> valuesIter = programmers.iterator();

        while (keysIter.hasNext() && valuesIter.hasNext()) {
            langsMap.put(keysIter.next(), valuesIter.next());
        }

        return langsMap;
    }

    public Map<String, Set<String>> getProgsMap() {
        Map<String, Set<String>> map = getLangsMap();
        Map<String, Set<String>> mapOfProgs = new LinkedHashMap<>();

        Set<String> keys;
        for (String prog : setOfProgs) {

            keys = new LinkedHashSet<>();

            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                if (entry.getValue().contains(prog)) {
                    keys.add(entry.getKey());

                }
                mapOfProgs.put(prog, keys);
            }
        }
        return mapOfProgs;
    }

    public  <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<? super Map.Entry<K, V>> comparator) {
        Map<K, V> sortedMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(comparator)
                .forEach(e -> sortedMap.put(e.getKey(), e.getValue()));

        return sortedMap;
    }

    public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
        Map<K, V> filteredMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .filter(predicate)
                .forEach(e -> filteredMap.put(e.getKey(), e.getValue()));

        return filteredMap;
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(getLangsMap(), (l1, l2) -> {
            int compare = -(l1.getValue().size() - l2.getValue().size());
            return compare == 0 ? l1.getKey().compareTo(l2.getKey()) : compare;
        });
    }

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(getProgsMap(), (l1, l2) -> {
            int compare = -(l1.getValue().size() - l2.getValue().size());
            return compare == 0 ? l1.getKey().compareTo(l2.getKey()) : compare;
        });
    }

    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int value) {
        return filtered(getProgsMap(), l -> l.getValue().size() > value);
    }
}
