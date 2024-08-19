package com.liu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CaesarCipher {
    private int shift;
    private Map<Character, Character> encryptMap;
    private Map<Character, Character> decryptMap;

    /**
     * Takes a valid integer which will serve as the alphabet offset for initializeCipherMaps().
     * @param shift as the alphabet offset.
     */
    public CaesarCipher(int shift) {
        if (shift < 1 || shift > 25) {
            throw new IllegalArgumentException("Shift must be between 1 and 25");
        }
        this.shift = shift;
        this.encryptMap = new HashMap<>();
        this.decryptMap = new HashMap<>();
        initializeCipherMaps();
    }

    /**
     * Iterates through the alphabet and applies Caesar Cipher using the shift parameter of CaesarCipher().
     * Explanation for code:
     * c - 'a' + shift: converts the char from ASCII value to zero based index value, then adds the shift integer to the char value.
     * % 26 + 'a': applies modulus 26 to keep the char value within the alphabet, then converts the char value back into ASCII value.
     */
    private void initializeCipherMaps() {
        // Initialize encryption and decryption maps for lowercase letters
        for (char c = 'a'; c <= 'z'; c++) {
            // Calculate the encrypted character using a Caesar Cipher shift
            char encrypted = (char) ((c - 'a' + shift) % 26 + 'a');
            encryptMap.put(c, encrypted);
            decryptMap.put(encrypted, c);
        }

        // Initialize encryption and decryption maps for uppercase letters
        for (char c = 'A'; c <= 'Z'; c++) {
            // Calculate the encrypted character using a Caesar Cipher shift
            char encrypted = (char) ((c - 'A' + shift) % 26 + 'A');
            encryptMap.put(c, encrypted);
            decryptMap.put(encrypted, c);
        }
    }

    public String encrypt(String message) {
        return transformMessage(message, encryptMap);
    }

    public String decrypt(String message) {
        return transformMessage(message, decryptMap);
    }

    /**
     * Takes a String a maps each character using the according encryption or decryption map.
     * @param message that the user wants to transform (encrypt or decrypt).
     * @param map that will offset the letters.
     * @return the newly transformed message.
     */
    private String transformMessage(String message, Map<Character, Character> map) {
        // Create a StringBuilder to build the transformed message
        StringBuilder result = new StringBuilder();
        // Iterate through each character in the input message
        for (char c : message.toCharArray()) {
            // Check if the current character is in the transformation map
            if (map.containsKey(c)) {
                // Append the mapped character to the result if it exists in the map
                result.append(map.get(c));
            }
            else {
                // Append the original character if it is not in the map
                result.append(c);
            }
        }
        // Convert the StringBuilder to a string and return the transformed message
        return result.toString();
    }


    /**
     * Takes a String message and iterates through each possible shift.
     * The words of the message are then compared to words in the dictionary to find a correct shift(s).
     * @param message that needs to be decrypted.
     * @return a String containing possible shift(s) and their according decrypted message(s).
     */
    public String cipherCracker(String message) {
        HashSet<String> dictionary = parseDictionary();
        String[] messageByWords = splitMessage(message);
        ArrayList<Integer> possibleShifts = checkShifts(messageByWords, dictionary);
        String result = collectPossibleShifts(message, possibleShifts);
        return result;
    }

    /**
     * Reads a txt file containing all English words and parses it into a HashSet.
     * @return a HashSet containing all the English words.
     */
    private HashSet<String> parseDictionary() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("words_alpha.txt"));
            HashSet<String> dictionary = new HashSet<>(lines);
            return dictionary;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes a String, splits in into an array of words, and removes all non-alphabetic characters.
     * @param message that needs to be split.
     * @return a String Array containing all the words from the String parameter.
     */
    private String[] splitMessage(String message) {
        // Convert the message to lowercase and split it into words by whitespace
        String[] messageByWords = message.toLowerCase().split("\\s");
        // Iterate through each word in the split message
        for (int i = 0; i < messageByWords.length; i++) {
            // Remove all non-alphabetic characters from the word
            messageByWords[i] = messageByWords[i].replaceAll("[^a-z]", "");
        }
        // Return the array of cleaned, lowercase words
        return messageByWords;
    }


    /**
     * Iterates over every possible shift for a given message, and returns valid possible shift(s) by comparing words to a dictionary.
     * @param messageByWords containing the given message split by words.
     * @param dictionary containing all English words.
     * @return an ArrayList which contains value(s) for valid possible shift(s).
     */
    private ArrayList<Integer> checkShifts(String[] messageByWords, HashSet<String> dictionary) {
        // Initialize a list to store the possible valid shifts
        ArrayList<Integer> possibleShifts = new ArrayList<>();

        // Iterate through each possible Caesar Cipher shift from 1 to 25
        for(int i = 1; i <= 25; i++) {
            int validationScore = 0;  // Counter to track how many shifted words are valid dictionary words
            // Create a CaesarCipher object with the current shift value
            CaesarCipher cipherCheck = new CaesarCipher(i);
            // Iterate through each word in the message
            for(String word : messageByWords) {
                // Decrypt the word using the current shift
                String testWord = cipherCheck.decrypt(word);
                // Check if the decrypted word exists in the dictionary
                if(dictionary.contains(testWord)) {
                    validationScore++;  // Increment the score if the word is found in the dictionary
                }
            }
            // If all words in the message match dictionary words after the shift, add the shift to possibleShifts
            if(validationScore == messageByWords.length) {
                possibleShifts.add(i);
            }
        }

        // Return the list of possible shifts that resulted in valid words
        return possibleShifts;
    }

    /**
     * Uses an ArrayList containing all valid possible shifts to give possibilities for message decryption.
     * @param message that needs to be decrypted.
     * @param possibleShifts contains value(s) for valid possible shift(s).
     * @return a String containing possible shift(s) and their according decrypted message(s).
     */
    private String collectPossibleShifts(String message, ArrayList<Integer> possibleShifts) {
        if(!possibleShifts.isEmpty()) {
            // Use StringBuilder to build the result string
            StringBuilder result = new StringBuilder();
            // Iterate through each possible shift value
            for (int possibleShift : possibleShifts) {
                CaesarCipher cipherString = new CaesarCipher(possibleShift);
                String decryptedMessage = cipherString.decrypt(message);
                // Append the shift and the corresponding decrypted message to the result
                result.append("Possible shift of ").append(possibleShift)
                        .append(", yielding message: ").append(decryptedMessage)
                        .append("\n");
            }
            // Convert the StringBuilder to a string and return it
            return result.toString();
        }
        return "No solution was found.";
    }

}