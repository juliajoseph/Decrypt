import java.lang.module.FindException;
import java.util.Scanner;

/**
 * CS312 Assignment 9.
 *
 * On my honor, Julia , this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 *  email address: juliajoseph65@gmail.com
 *  UTEID: jaj4598
 *  TA name: Alec
 *  Number of slip days used on this assignment:1
 *
 * Program to decrypt a message that has been
 * encrypted with a substitution cipher.
 * We assume only characters with ASCII codes
 * from 32 to 126 inclusive have been encrypted.
 */

public class Decrypt {
// This method executes the decryption program until the user doesn't want to change
// the key
    public static void main(String[] arg) {
        Scanner keyboard = new Scanner(System.in);
        String fileName = getFileName(keyboard);
        String encryptedText = DecryptUtilities.convertFileToString(fileName);
        int[] ASCII = frequencyAnalysis(encryptedText);
        char[] key = DecryptUtilities.getDecryptionKey(ASCII);
        printIntro(encryptedText);
        printFrequencies(ASCII);
        printKey(key);

        boolean changeKey = true;
        while (changeKey) {
            String keep = "";
            currDecryptedText(key, encryptedText, false);
            System.out.println();
            System.out.println("Do you want make a change to the key?");
            System.out.print("Enter 'Y' or 'y' to make change: ");
            keep = keyboard.nextLine();
            if ((keep.equalsIgnoreCase("Y"))) {
                System.out.print("Enter the decrypt character you want to change: ");
                char decryptCharacter = keyboard.nextLine().charAt(0);
                System.out.print("Enter what the character " + decryptCharacter + " " +
                        "should decrypt to instead: ");
                char decryptTo = keyboard.nextLine().charAt(0);
                System.out.println(decryptCharacter + "'s will now decrypt to " + decryptTo + "'s and vice versa.");
                changeKey(key, decryptCharacter, decryptTo);
            }
            else {
                changeKey = false;
                printKey(key);
                currDecryptedText(key, encryptedText, true);
            }
        }
        keyboard.close();
    }

    // Get the name of file to use. For the assignment no error
    // checking is required.
    public static String getFileName(Scanner kbScanner) {
        System.out.print("Enter the name of the encrypted file: ");
        String fileName = kbScanner.nextLine().trim();
        System.out.println();
        return fileName;
    }
// This method prints intro
    public static void printIntro(String encryptedText){
        System.out.println("The encrypted text is: ");
        System.out.println(encryptedText);
    }
 // calculates frequency analysis on the data
    public static int [] frequencyAnalysis(String encryptedText){
        final int ARRAYLENGTH = 128;
        //int frequencies = 0;
        int [] ASCII = new int[ARRAYLENGTH];
        for(int i = 0; i < encryptedText.length(); i++){
            char letter = encryptedText.charAt(i);
                    ASCII[letter]++;
        }
        return ASCII;
    }
// This prints the frequencies of each encrypted character
    public static void printFrequencies(int[] ASCII){
       int START = 32;
        System.out.println("Frequencies of characters.");
        System.out.println("Character - Frequency");
        for (int i = START; i < ASCII.length -1; i++){
            System.out.println((char)START + " - " + ASCII[i]);
            START++;
        }
        System.out.println();
    }
// This prints the encrypted and decrypted key
    public static void printKey(char[] key){
        final int ARRAYLENGTH = 128;
        final int START = 32;
        //char ASCIILETTER = START;
        System.out.println();
        System.out.println("The current version of the key for ASCII characters 32 to " +
                "126 is:");
        for (int i = START; i < ARRAYLENGTH - 1; i++){
            System.out.println("Encrypt character: " + (char)i + "," + " decrypt " +
                    "character: " + key[i]);
        }
    }
    // This prints the decrypted text
    public static void currDecryptedText(char[] key, String encryptedText,
                                         boolean finalText){
        System.out.println();
        if (!finalText)
            System.out.println("The current version of the decrypted text is:");
        else
            System.out.println("The final version of the decrypted text is:");

        System.out.println();
        for(int i = 0; i < encryptedText.length(); i++){
                    System.out.print(key[encryptedText.charAt(i)]);
        }
    }
        // This uses the indexOf method to change the key
    public static void changeKey(char[] key, char decryptCharacter, char decryptTo){
        int decryptCharacterIndex = indexOf(key, decryptCharacter, 0);
        int decryptToIndex = indexOf(key, decryptTo, 0);

        char temp = key[decryptCharacterIndex];
        key[decryptCharacterIndex] = key[decryptToIndex];
        key[decryptToIndex] = temp;
    }
  // This obtains the index of the decrypted character the user wants to change
    public static int indexOf(char[] list, char target, int start)
    {   int result = -1;
        int index = start;
        while(result == -1 && index < list.length)
        {   if( list[index] == target )
            result = index;
            index++;
        }
        return result;
    }
}
