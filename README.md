# Hangman Game in Java

This is a simple command-line implementation of the classic Hangman game written in Java. The game loads a list of words from an external text file, allows the player to guess letters, and provides feedback on the state of the word after each guess.

## How It Works

1. The program reads a list of words from a text file (`words_alpha.txt`).
2. A random word is selected from the list for the player to guess.
3. The player is prompted to guess one letter at a time.
4. The player has 6 wrong attempts allowed before the game is over.
5. The game continues until the player either guesses the entire word or runs out of guesses.

## Features

- Random word selection from an external file.
- Tracks player guesses and displays the word's current state (with underscores for unguessed letters).
- Allows up to 6 incorrect guesses.
- Informs the player when they've guessed the word correctly or if they've run out of guesses.

## Prerequisites

- Java Development Kit (JDK) installed (version 8 or later).
- A text file (`words_alpha.txt`) containing a list of words, each word on a new line. The file should be located at `/Users/omarawilhassan/Desktop/WORDFILE/words_alpha.txt`.

## How to Run the Game

1. **Compile the Java file:**

   Open a terminal and navigate to the directory containing `Hangman.java`:
   ```bash
   javac Hangman.java
