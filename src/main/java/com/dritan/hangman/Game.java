package com.dritan.hangman;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Game {

    private String wordToGuess;
    private String maskedWord;
    private String lastEnteredLetter;

    public void onInitializeGame() {
        final WordDictionary dictionary = new WordDictionary();
        wordToGuess = dictionary.get();
        maskedWord = generateMask(wordToGuess);
    }

    public String getMaskedWord() {
        return maskedWord;
    }

    public String getLastEnteredLetter() {
        return lastEnteredLetter;
    }

    public void setLastEnteredLetter(String lastEnteredLetter) {
        this.lastEnteredLetter = lastEnteredLetter;
    }

    private String generateMask(final String word) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append("_ ");
        }
        return sb.toString();
    }
}