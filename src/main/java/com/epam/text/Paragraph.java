package com.epam.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Paragraph extends BaseEntity implements Iterable<Sentence> {

    private List<Sentence> sentenceList;

    public Paragraph() {
        this.sentenceList = new ArrayList<>();
    }

    public void add(Sentence sentence) {
        sentenceList.add(sentence);
    }

    public Sentence get(int index) {
        return sentenceList.get(index);
    }

    public StringBuilder toPlainText(StringBuilder sb) {
        for (Sentence s : sentenceList) {
            s.toPlainText(sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return "Number of sentences=" + this.size();
    }

    public int size() {
        return sentenceList.size();
    }

    @Override
    public Iterator<Sentence> iterator() {
        return sentenceList.iterator();
    }
}
