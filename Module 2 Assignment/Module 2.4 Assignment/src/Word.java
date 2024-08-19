import java.util.Objects;

public class Word {
    private String word;

    public Word(String word) {
        this.word = normalize(word);
    }

    /**
     * Changes the provided String test to all lower case, and removing any
     * non-alphabetic characters (excluding apostrophes and hyphens).
     * @param word that needs to be normalized.
     * @return the normalized String word.
     */
    private String normalize(String word) {
        return word.toLowerCase().replaceAll("[^a-zA-Z'-]", "");
    }

    @Override
    public boolean equals(Object word) {
        // Check if the current object is the same as the one being compared
        if(this == word) return true;
        // Check if the passed object is null or if the objects are of different classes
        if(word == null || getClass() != word.getClass()) return false;
        // Cast the passed object to a Word type for comparison
        Word word1 = (Word) word;
        // Compare the string representations of the two Word objects
        return Objects.equals(this.word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return word;
    }
}
