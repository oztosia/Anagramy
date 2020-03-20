package com.example.anagramy;

import com.example.anagramy.dictionary.Dictionary;
import org.junit.*;

import java.io.FileNotFoundException;


public class DictionaryTest{

    @Test
    public void shouldSayThatTakIsAnagram() throws FileNotFoundException {
        Assert.assertTrue(Dictionary.isAnagram("tak"));
    }

    @Test
    public void shouldSayThatTAKIsAnagram() throws FileNotFoundException {
        Assert.assertTrue(Dictionary.isAnagram("TAK"));
    }

    @Test
    public void shouldSayThatNiedzieleIsAnagram() throws FileNotFoundException {
        Assert.assertTrue(Dictionary.isAnagram("niedziele"));
    }

    @Test
    public void shouldSayThatRododendronIsNotAnagram() throws FileNotFoundException {
        Assert.assertFalse(Dictionary.isAnagram("rododendron"));
    }

    @Test
    public void shouldSayThatGuArIsNotAnagram() throws FileNotFoundException {
        Assert.assertFalse(Dictionary.isAnagram("GuAr"));
    }


}

