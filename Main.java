import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        // Load words from the file
        Scanner fileScanner = new Scanner(new File("/Users/omarawilhassan/Desktop/WORDFILE/words_alpha.txt"));
        List<String> words = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            words.add(fileScanner.nextLine());
        }
        fileScanner.close(); // Close the scanner to avoid resource leak

        // Pick a random word
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        // List to store player guesses
        List<Character> playerGuesses = new ArrayList<>();

        // Number of wrong attempts allowed
        int wrongGuesses = 0;
        int maxWrongGuesses = 6;

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");

        // Game loop
        while (wrongGuesses < maxWrongGuesses) {
            printWordState(word, playerGuesses); // Display the word with guessed letters and underscores
            System.out.println("\nYou have " + (maxWrongGuesses - wrongGuesses) + " wrong guesses left.");
            System.out.print("Guess a letter: ");

            String guessInput = inputScanner.nextLine();
            if (guessInput.length() != 1) {
                System.out.println("Please enter only a single letter.");
                continue;
            }

            char guess = guessInput.charAt(0);
            if (playerGuesses.contains(guess)) {
                System.out.println("You already guessed that letter!");
                continue;
            }

            playerGuesses.add(guess);

            if (word.contains(String.valueOf(guess))) {
                System.out.println("Good guess!");
            } else {
                wrongGuesses++;
                System.out.println("Wrong guess.");
            }

            // Check if the player has guessed the entire word
            if (isWordGuessed(word, playerGuesses)) {
                System.out.println("Congratulations! You've guessed the word: " + word);
                break;
            }
        }

        if (wrongGuesses == maxWrongGuesses) {
            System.out.println("Game over! You've run out of guesses. The word was: " + word);
        }

        inputScanner.close();
    }

    // Method to print the current state of the word
    private static void printWordState(String word, List<Character> playerGuesses) {
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println(); // Move to the next line
    }

    // Method to check if the entire word has been guessed
    private static boolean isWordGuessed(String word, List<Character> playerGuesses) {
        for (int i = 0; i < word.length(); i++) {
            if (!playerGuesses.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
