package search;


import java.util.Objects;

public class Main {
    public static void main(String[] args) {
//        parse path to some file
        String path = "";
        for (var i = 0; i < args.length; i++) {
            if (Objects.equals("--data", args[i])) {
                path = args[++i];
                break;
            }
        }
        new Menu(path);
    }
}
