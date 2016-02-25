package com.epam.text;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {

    private List<Sentence> sentenceList;

    public Paragraph(){
        this.sentenceList=new ArrayList<>();
    }

    public void add(Sentence sentence) {
        sentenceList.add(sentence);
    }

    public Sentence get(int index){
        return sentenceList.get(index);
    }

    public String toPlainText(StringBuilder sb) {
        for (Sentence s:sentenceList) {
            s.toPlainText(sb);
        }
    return sb.toString();
    }
}
