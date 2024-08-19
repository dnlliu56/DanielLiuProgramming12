import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class File {

    /**
     * Takes a List of lines and yields the quantity of unique words in the text.
     * @param lines that contain the words to be compared.
     * @return the quantity of unique words.
     */
    public int processFile(List<String> lines) {
        Set<Word> uniqueWords = new HashSet<>();

        for(String line : lines) {
            // Split the line into words based on whitespace
            String[] words = line.split("\\s+");

            for(String word : words) {
                // Add a new Word object to the set (duplicates are ignored)
                uniqueWords.add(new Word(word));
            }
        }

        return uniqueWords.size();
    }

    public List<String> readFile(String file) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(file));
            return lines;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
