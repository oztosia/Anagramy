package com.example.anagramy.dictionary;

import com.example.anagramy.activity.GameActivity;
import com.example.anagramy.activity.DictionaryCalculation;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Dictionary {

    public static void isAnagram (String searchWord, BufferedReader reader) throws IOException {
        String line = DictionaryCalculation.reader.readLine();
        while (line != null) {
            String anagram = line.trim();
            char[] anagramToArray = anagram.toCharArray();
            char[] searchWordToArray = searchWord.toCharArray();
                Arrays.sort(anagramToArray);
                Arrays.sort(searchWordToArray);
            if (Arrays.equals(anagramToArray, searchWordToArray) && !anagram.equals(searchWord)) {
                GameActivity.listOfAnagrams.add(anagram);
            }
            line = reader.readLine();
        }
    }

}
