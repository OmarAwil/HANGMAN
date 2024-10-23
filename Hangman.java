import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/Users/omarawilhassan/Desktop/WORDFILE/words_alpha.txt"));
        List<String> words = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            words.add(fileScanner.nextLine());
        }
        fileScanner.close();

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        List<Character> playerGuesses = new ArrayList<>();

        int wrongGuesses = 0;
        int maxWrongGuesses = 6;

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");

        while (wrongGuesses < maxWrongGuesses) {
            printHangman(wrongGuesses);
            printWordState(word, playerGuesses);
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

            if (isWordGuessed(word, playerGuesses)) {
                System.out.println("Congratulations! You've guessed the word: " + word);
                break;
            }
        }

        if (wrongGuesses == maxWrongGuesses) {
            printHangman(wrongGuesses);
            System.out.println("Game over! You've run out of guesses. The word was: " + word);
        }

        inputScanner.close();
    }

    private static void printWordState(String word, List<Character> playerGuesses) {
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private static boolean isWordGuessed(String word, List<Character> playerGuesses) {
        for (int i = 0; i < word.length(); i++) {
            if (!playerGuesses.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static void printHangman(int wrongGuesses) {
        System.out.println("_________");
        System.out.println("|       |");

        if (wrongGuesses >= 1) {
            System.out.println("|       O");
        } else {
            System.out.println("|");
        }

        if (wrongGuesses >= 3) {
            System.out.print("|      /|\\");
        } else if (wrongGuesses == 2) {
            System.out.print("|      /|");
        } else if (wrongGuesses == 1) {
            System.out.print("|       |");
        } else {
            System.out.println("|");
        }

        if (wrongGuesses >= 4) {
            System.out.println();
            if (wrongGuesses == 6) {
                System.out.println("|      / \\");
            } else if (wrongGuesses == 5) {
                System.out.println("|      / ");
            }
        } else {
            System.out.println("|");
        }

        System.out.println("|");
        System.out.println("|_________\n");
    }
}
