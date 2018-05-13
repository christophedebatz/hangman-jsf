package com.dritan.hangman;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class Game {

    private int tries = 0;
    private String wordToGuess;
    private List<String> letters = new ArrayList<>();

    public void onInitializeGame() {
        final WordDictionary dictionary = new WordDictionary();
        wordToGuess = dictionary.get().toUpperCase();
        letters.clear();
        tries = 0;
    }

    public boolean isGameInitialized() {
        return wordToGuess != null;
    }

    public boolean isGameCompleted() {
        if (wordToGuess == null || letters.isEmpty()) {
            return false;
        }
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (!letters.contains(wordToGuess.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }

    public String getMaskedWord() {
        if (wordToGuess == null) {
            return "";
        }
        if (letters.isEmpty()) {
            return generateMask(wordToGuess);
        }
        // get the positions of letters we already know
        final List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < wordToGuess.length(); i++) {
            for (final String letter : letters) {
                if (wordToGuess.charAt(i) == letter.toUpperCase().charAt(0)) {
                    positions.add(i);
                }
            }
        }
        // generate mask with new known letter
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordToGuess.length(); i++) {
            boolean hasUpdateChar = false;
            for (final Integer position : positions) {
                if (i == position) {
                    sb.append(wordToGuess.charAt(i)).append(" ");
                    hasUpdateChar = true;
                }
            }
            if (!hasUpdateChar) {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    public String getLastEnteredLetter() {
        return "";
    }

    public void setLastEnteredLetter(final String lastEnteredLetter) {
        if (lastEnteredLetter != null && lastEnteredLetter.trim().length() > 0) {
            final String letter = lastEnteredLetter.substring(lastEnteredLetter.length() - 1).toUpperCase();
            if (!letters.contains(letter)) {
                tries++;
                letters.add(letter);
            }
        }
    }

    public String getTries() {
        if (tries <= 1) {
            tries = 1;
            return tries + "st";
        } else if (tries == 2) {
            return tries + "nd";
        } else if (tries == 3) {
            return tries + "rd";
        } else {
            return tries + "th";
        }
    }

    private static String generateMask(final String word) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append("_ ");
        }
        return sb.toString().trim();
    }
}