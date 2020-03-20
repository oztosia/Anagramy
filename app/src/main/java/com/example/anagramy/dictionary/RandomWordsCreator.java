package com.example.anagramy.dictionary;

import java.io.IOException;
import java.util.Random;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class RandomWordsCreator {

    public static String randomWord(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        List<String> words = new ArrayList<String>();
        while (line != null) {
            String[] wordsLine = line.split(" ");
            for (String word : wordsLine) {
                words.add(word);
            }
            line = reader.readLine();
        }

        Random rand = new Random();
        String randomWord = words.get(rand.nextInt(words.size()));
        System.out.println(randomWord);
        return randomWord;
    }
}
