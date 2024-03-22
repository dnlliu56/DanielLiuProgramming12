import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args)
            throws IOException {
        //using input() to count emoticons
        Main mainLineInput = new Main();
        String lineInput = mainLineInput.input();
        //defining emoticons
        String happyEmoticon = ":-)";
        String sadEmoticon = ":-(";
        //using happy/sadEmoticonCounter() to count emoticons
        int happyCount = happyEmoticonCounter(lineInput, happyEmoticon);
        int sadCount = sadEmoticonCounter(lineInput, sadEmoticon);

        //logic for output
        if (happyCount == 0 && sadCount == 0) {
            System.out.println("none");
        }

        if (happyCount == sadCount && happyCount != 0) {
            System.out.println("unsure");
        }

        if (happyCount > sadCount) {
            System.out.println("happy");
        }

        if (happyCount < sadCount) {
            System.out.println("sad");
        }
    }

    public String input()
            throws IOException {

        //change file path
        Path fileName
                = Path.of("C:\\Users\\dnlli\\IdeaProjects\\Emoticons\\src\\happyorsad.txt");

        //define variable by file contents
        String happyOrSadTxt = Files.readString(fileName);

        return happyOrSadTxt;
    }

    static int happyEmoticonCounter(String lineInput, String happyEmoticon) {
        //avoid errors with brackets
        String escapedHappyEmoticon = Pattern.quote(happyEmoticon);
        //count
        if (lineInput.contains(happyEmoticon)) {
            return 1 + happyEmoticonCounter(lineInput.replaceFirst(escapedHappyEmoticon, ""), happyEmoticon);
        }
        return 0;
    }

    static int sadEmoticonCounter(String lineInput, String sadEmoticon) {
        //avoid errors with brackets
        String escapedSadEmoticon = Pattern.quote(sadEmoticon);
        //count
        if (lineInput.contains(sadEmoticon)) {
            return 1 + happyEmoticonCounter(lineInput.replaceFirst(escapedSadEmoticon, ""), sadEmoticon);
        }
        return 0;
    }
}
