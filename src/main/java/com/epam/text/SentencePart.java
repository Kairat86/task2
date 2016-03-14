package com.epam.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SentencePart extends BaseEntity implements Iterable<Char>{

    private List<Char> charList;
    private String word;

    public SentencePart(){
        this.charList = new ArrayList<>();

    }

    public SentencePart(String word) {
        this();
        this.word=word;

    }

    public Char get(int index) {
        return charList.get(index);
    }


    public void add(Char ch) {
        charList.add(ch);
    }

    public void setWord(String word) {
        this.word = word;
    }


    public String getWord() {
        return word;
    }

    public StringBuilder toPlainText(StringBuilder sb) {

        for (Char ch : charList) {
            ch.toPlainText(sb);
        }
        return sb;
    }

    public int size() {
        return charList.size();
    }

    @Override
    public Iterator<Char> iterator() {
        return charList.iterator();
    }

    @Override
    public String toString(){
        String value="";
        for (Char c:charList) {
           value+=c.toPlainText(new StringBuilder());
        }
        return value;
    }

    @Override
    public boolean equals(Object obj) {
         return this.toString().toLowerCase().equals(((SentencePart)obj).toString());
    }



}
