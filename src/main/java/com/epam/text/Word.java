package com.epam.text;

import javafx.scene.chart.Chart;

import java.util.ArrayList;
import java.util.List;

public class Word {

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

    public String toPlainText(StringBuilder sb) {

        for (Char ch : charList) {
            sb.append(ch.getValue());
        }
        return sb.toString();
    }
}
