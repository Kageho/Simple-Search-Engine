package search;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Context for Strategy pattern
 */
public class Context {
    private SearchMode searchMode;

    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    public void executeStrategy(String template, List<String> information, Map<String, Set<Integer>> invertedIndex) {
        this.searchMode.searchByCertainMode(template, information, invertedIndex);
    }
}
