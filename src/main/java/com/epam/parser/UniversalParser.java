package com.epam.parser;


import com.epam.text.*;
import com.epam.util.PropertyManager;

import javax.naming.OperationNotSupportedException;

public class UniversalParser implements Parser {

    private PropertyManager propertyManager;

    public UniversalParser(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }


    public <T extends BaseEntity> T parse(Class<T> type, String src) throws OperationNotSupportedException {
        String[] parts;
        T t = null;
        try {
            t = type.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Couldnt instantiate " + type.getSimpleName());
        } catch (IllegalAccessException e) {
            System.out.println("Access denied");
        }
        if (t instanceof Text) {
            parts = src.split(propertyManager.getProperty(PARAGRAPH_REGEX));
            for (String s : parts) {
                Paragraph paragraph = parse(Paragraph.class, s);
                ((Text) t).add(paragraph);
            }
        } else if (t instanceof Paragraph) {
            parts = src.split(propertyManager.getProperty(SENTENCE_REGEX));
            for (String s : parts) {
                Sentence sentence = parse(Sentence.class, s);
                ((Paragraph) t).add(sentence);
            }
        } else if (t instanceof Sentence) {

            parts = src.split(propertyManager.getProperty(WORD_REGEX));
            for (String s : parts) {
                SentencePart word = parse(SentencePart.class, s);
                ((Sentence) t).add(word);
            }
        } else if (t instanceof SentencePart) {

            SentencePart sentencePart=((SentencePart) t);
            char[] chars = src.toCharArray();
            if(",.:?! ".indexOf(chars[chars.length-1])>=0){
                sentencePart.setWord(src.substring(0,src.length()-1));
            }else {
                sentencePart.setWord(src);
            }


            for (char ch : chars) {
               sentencePart.add(Char.of(ch));
            }
        }
        return t;
    }


    public Text parseText(String src) {
        Text text;
        ;
        try {
            text = parse(Text.class, src);
        } catch (OperationNotSupportedException e) {
            text = new ParserImplementation(propertyManager).parseText(src);
        }
        return text;
    }

    public Paragraph parseParagraph(String src) {
        Paragraph paragraph;
        try {
            paragraph = parse(Paragraph.class, src);
        } catch (OperationNotSupportedException e) {
            paragraph = new ParserImplementation(propertyManager).parseParagraph(src);
        }
        return paragraph;
    }

    public Sentence parseSentence(String src) {
        Sentence sentence = new Sentence();
        try {
            sentence = parse(Sentence.class, src);
        } catch (OperationNotSupportedException e) {
            sentence = new ParserImplementation(propertyManager).parseSentence(src);
        }
        return sentence;
    }

    public SentencePart parseWord(String src) {
        SentencePart word = new SentencePart();
        try {
            word = parse(SentencePart.class, src);
        } catch (OperationNotSupportedException e) {
            word = new ParserImplementation(propertyManager).parseWord(src);
        }
        return word;
    }


}
