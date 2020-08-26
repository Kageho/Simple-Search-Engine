package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Menu {
    private ArrayList<String> information;
    private final Scanner scanner;
    private final Map<String, Set<Integer>> invertedIndex;
    private final String pathToFile;

    Menu(String path) {
        this.pathToFile = path;
        information = new ArrayList<>();
        scanner = new Scanner(System.in);
        invertedIndex = new HashMap<>();
        if (readFromFile()) {
            createInvertedIndex();
            start();
        } else {
            System.out.println("Sorry file not found");
        }

    }

    private void start() {
        while (true) {
            printMenu();
            int action = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (action) {
                case 1:
                    searchInformation();
                    break;
                case 2:
                    printList();
                    break;
                case 0:
                    sayBye();
                    return;
                default:
                    System.out.println("Incorrect option! Try again.\n");
            }
        }
    }

    // method fill the map where key is some word in lower case and value it is indexes of sentences
//    which contain this word
    private void createInvertedIndex() {
        for (int i = 0; i < information.size(); i++) {
            for (var j : information.get(i).split("\\s")) {
                if (!Objects.equals(invertedIndex.get(j.toLowerCase()), null)) {
                    Set<Integer> localSet = new HashSet<>(invertedIndex.get(j.toLowerCase()));
                    localSet.add(i);
                    invertedIndex.put(j.toLowerCase(), localSet);
                } else {
                    invertedIndex.put(j.toLowerCase(), Set.of(i));
                }

            }
        }
    }

    private boolean readFromFile() {
        try (Scanner fileScanner = new Scanner(new File(pathToFile))) {
            while (fileScanner.hasNextLine()) {
                information.add(fileScanner.nextLine());
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("file not found");
            return false;
        }
        return true;
    }

    private void printMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    private void printList() {
        System.out.println("=== List of people ===");
        for (var i : information) {
            System.out.println(i);
        }
        System.out.println();
    }

    private void searchInformation() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine().toUpperCase();
        Context context = new Context();
        System.out.println("Enter a name or email to search all suitable people.");
        String template = scanner.nextLine().toLowerCase();
        switch (strategy) {
            case "ALL":
                context.setSearchMode(new SearchAllMatching());
                context.executeStrategy(template, getInformation(), getInvertedIndex());
                break;
            case "ANY":
                context.setSearchMode(new SearchAnyMatching());
                context.executeStrategy(template, getInformation(), getInvertedIndex());
                break;
            case "NONE":
                context.setSearchMode(new SearchNoneMatching());
                context.executeStrategy(template, getInformation(), getInvertedIndex());
                break;
            default:
                System.out.println("Looks like you miss. There is no such option");
        }
    }

    public Map<String, Set<Integer>> getInvertedIndex() {
        return invertedIndex;
    }

    public ArrayList<String> getInformation() {
        return information;
    }

    private void sayBye() {
        System.out.println("Bye!");
    }
}
