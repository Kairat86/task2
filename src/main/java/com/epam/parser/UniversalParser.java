package com.epam.parser;


import com.epam.text.*;
import com.epam.text2.Component;
import com.epam.util.PropertyManager;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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
            System.out.println("Parsing text");
            parts = src.split(propertyManager.getProperty(PARAGRAPH_REGEX));
            for (String s : parts) {
                Paragraph paragraph = parse(Paragraph.class, s);
                ((Text) t).add(paragraph);
            }
        } else if (t instanceof Paragraph) {
            System.out.println("Parsing paragraph");
            parts = src.split(propertyManager.getProperty(SENTENCE_REGEX));
            for (String s : parts) {
                Sentence sentence = parse(Sentence.class, s);
                ((Paragraph) t).add(sentence);
            }
        } else if (t instanceof Sentence) {
            System.out.println("parsing sentence");
            parts = src.split(propertyManager.getProperty(WORD_REGEX));
            for (String s : parts) {
                Word word = parse(Word.class, s);
                ((Sentence) t).add(word);
            }
        } else if (t instanceof Word) {
            System.out.println("parsing word");
            char[] chars = src.toCharArray();
            for (char ch : chars) {
                ((Word) t).add(Char.of(ch));
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

    public Word parseWord(String src) {
        Word word = new Word();
        try {
            word = parse(Word.class, src);
        } catch (OperationNotSupportedException e) {
            word = new ParserImplementation(propertyManager).parseWord(src);
        }
        return word;
    }


}
