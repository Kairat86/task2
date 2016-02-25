package com.epam.text;

import java.util.ArrayList;
import java.util.List;

public class Text {

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

    public String toPlaitText(){
        StringBuilder sb= new StringBuilder();
        for(Paragraph p:paragraphList){
           p.toPlainText(sb);
        }
        return sb.toString();
    }

    public int size(){
       return paragraphList.size();
    }

    @Override
    public String toString(){

        return "Number of paragraphs="+this.size();
    }
}
