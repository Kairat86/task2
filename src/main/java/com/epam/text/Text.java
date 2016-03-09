package com.epam.text;

import java.util.ArrayList;
import java.util.List;

public class Text extends BaseEntity implements Component {

    private List<Paragraph> paragraphList;

    public Text() {
        this.paragraphList = new ArrayList<>();


    }

    public void add(Paragraph paragraph) {
        paragraphList.add(paragraph);

    }

    public Paragraph get(int index) {
        return paragraphList.get(index);
    }

    public StringBuilder toPlainText(StringBuilder stringBuilder) {

        for (Paragraph p : paragraphList) {
            p.toPlainText(stringBuilder);
        }
        return stringBuilder;
    }

    public int size() {
        return paragraphList.size();
    }

    @Override
    public String toString() {
        return "Number of paragraphs=" + this.size();
    }
}
