package com.epam.text;

import java.util.ArrayList;
import java.util.List;

public class Word extends BaseEntity implements Component{

    private List<Char> charList;

    public Word() {
        this.charList = new ArrayList<>();
    }

    public void add(Char ch) {
        charList.add(ch);
    }

    public Char get(int index) {
        return charList.get(index);
    }

    public StringBuilder toPlainText(StringBuilder sb) {

        for (Char ch : charList) {
            ch.toPlainText(sb);
        }
        return sb;
    }
}
