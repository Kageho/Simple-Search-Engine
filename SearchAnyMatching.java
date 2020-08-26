package search;


import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchAnyMatching implements SearchMode {
    @Override
    public void searchByCertainMode(String template, List<String> information, Map<String, Set<Integer>> invertedIndex) {
        Set<Integer> indexes = SearchMode.getAnyMatching(template, information, invertedIndex);
        SearchMode.printResults(indexes, information);
    }
}
