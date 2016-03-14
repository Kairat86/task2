package com.epam.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sentence extends BaseEntity implements Iterable<SentencePart> {

    private List<SentencePart> wordList;

    public Sentence() {
        this.wordList = new ArrayList<>();
    }

    public void add(SentencePart word) {
        wordList.add(word);
    }

    public SentencePart get(int index) {
        return wordList.get(index);
    }

    public StringBuilder toPlainText(StringBuilder sb) {
        for (SentencePart w : wordList) {
            w.toPlainText(sb);
        }
        return sb;
    }

    public Iterator<SentencePart> iterator() {
        return wordList.iterator();
    }

    public int size() {
        return wordList.size();
    }

    public boolean contains(SentencePart o) {
        return wordList.contains(o);
    }
}
