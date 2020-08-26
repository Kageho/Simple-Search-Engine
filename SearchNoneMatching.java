package search;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchNoneMatching implements SearchMode {

    @Override
    public void searchByCertainMode(String template, List<String> information, Map<String, Set<Integer>> invertedIndex) {
//       get set with any matching
        Set<Integer> indexes = SearchMode.getAnyMatching(template, information, invertedIndex);
//        set for every index
        Set<Integer> allIndexes = new HashSet<>();
        for (var i : invertedIndex.entrySet()) {
            allIndexes.addAll(i.getValue());
        }
//        set for indexes which don't contain template
        Set<Integer> rightIndexes = new HashSet<>();
        for (var i : allIndexes) {
            if (!indexes.contains(i)) {
                rightIndexes.add(i);
            }
        }
        SearchMode.printResults(rightIndexes, information);
    }
}
