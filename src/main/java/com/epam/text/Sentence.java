package com.epam.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sentence extends BaseEntity implements Iterable<Word> {

    private List<Word> wordList;

    public Sentence() {
        this.wordList = new ArrayList<>();
    }

    public void add(Word word) {
        wordList.add(word);
    }

    public Word get(int index) {
        return wordList.get(index);
    }

    public StringBuilder toPlainText(StringBuilder sb) {
        for (Word w : wordList) {
            w.toPlainText(sb);
        }
        return sb;
    }

    public Iterator<Word> iterator() {
        return wordList.iterator();
    }

    public int size() {
        return wordList.size();
    }
}
