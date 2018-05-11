package com.dritan.hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class WordDictionary implements Supplier<String> {

    private static String WORDS_FILE = "/words.txt";
    private static List<String> dictionary = new ArrayList<>();
    private BufferedReader reader;

    WordDictionary() {
        final InputStream inputStream = this.getClass().getResourceAsStream(WORDS_FILE);
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public String get() {
        if (dictionary.isEmpty()) {
            try (Scanner scanner = new Scanner(reader)) {
                while (scanner.hasNextLine()) {
                    dictionary.add(scanner.nextLine());
                }
                scanner.close();
            }
        }
        final Random rand = new Random();
        return dictionary.get(rand.nextInt(dictionary.size()));
    }
}
