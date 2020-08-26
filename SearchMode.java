package search;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchMode {
    void searchByCertainMode(String template, List<String> information, Map<String, Set<Integer>> invertedIndex);

    static void printResults(Set<Integer> indexes, List<String> information) {
        if (indexes != null) {
            if (indexes.isEmpty()) {
                System.out.println("No matching people found.");
            } else {
                System.out.println(indexes.size() + " person" + (indexes.size() == 1 ? " " : "s ") + "found:");
                for (var i : indexes) {
                    System.out.println(information.get(i));
                }
            }
        } else {
            System.out.println("NPE is occur");
        }

    }

    static Set<Integer> getAnyMatching(String template, List<String> information, Map<String, Set<Integer>> invertedIndex) {
        String[] arrays = template.split("\\s");
        Set<Integer> indexes = new HashSet<>();
        for (var i : arrays) {
            indexes.addAll(invertedIndex.get(i));
        }
        return indexes;
    }
}
