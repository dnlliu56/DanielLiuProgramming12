import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File();
        List<String> lines = file.readFile("illiad.txt");

        int output = file.processFile(lines);
        System.out.println("There are " + output + " unique word(s)");
    }
}