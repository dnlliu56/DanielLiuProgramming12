package com.liu;

public class Main {
    public static void main(String[] args) {
        //example method callings
        CaesarCipher cipher = new CaesarCipher(21);
        String encrypted = cipher.encrypt("I came, I saw, I conquered.");
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        String unknownKey = "Huk fvb, Iybabz?";    //example with o ne possible shift
        System.out.println(cipher.cipherCracker(unknownKey));
        String unknownKey2 = "fsi";    //example with multiple possible shifts
        System.out.println(cipher.cipherCracker(unknownKey2));
        String unknownKey3 = "asdfjkl";    //no solution
        System.out.println(cipher.cipherCracker(unknownKey3));
    }
}