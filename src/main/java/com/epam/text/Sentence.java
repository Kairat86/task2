package com.epam.text;

import java.util.ArrayList;
import java.util.List;

public class Sentence {

    private List<Word> wordList;

    public Sentence() {
        this.wordList = new ArrayList<>();
    }

    public void add(Word word) {
        wordList.add(word);
    }

    public Word get(int index){
        return wordList.get(index);
    }

    public String toPlainText(StringBuilder sb) {
        for (Word w:wordList) {
           w.toPlainText(sb);
        }
        return sb.toString();
    }
}
