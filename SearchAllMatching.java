package search;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchAllMatching implements SearchMode {
    @Override
    public void searchByCertainMode(String template, List<String> information, Map<String, Set<Integer>> invertedIndex) {
        Set<Integer> indexes = invertedIndex.get(template);
        SearchMode.printResults(indexes, information);
    }
}
