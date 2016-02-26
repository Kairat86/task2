package com.epam.parser;

import com.epam.text.*;

public class Parser {

    public static Text parseText(String src) {
        Text text = new Text();
        String[] paragraphStrings = src.split("(?<=(\\n) {4})");
        for (String paragraphStr : paragraphStrings) {
            Paragraph paragraph = parseParagraph(paragraphStr);
            text.add(paragraph);
        }
        return text;
    }

    private static Paragraph parseParagraph(String src) {
        Paragraph paragraph = new Paragraph();
        String[] sentenceStrs = src.split("(?<=[.!?])( )");
        for (String sentenceStr : sentenceStrs) {
            Sentence sentence = parseSentence(sentenceStr);
            paragraph.add(sentence);
        }
        return paragraph;
    }

    private static Sentence parseSentence(String src) {
        Sentence sentence = new Sentence();
        String[] wordStrs = src.split("(?<= +)");
        for (String wordStr : wordStrs) {
            Word word = parseWord(wordStr);
            sentence.add(word);
        }
        return sentence;
    }

    private static Word parseWord(String src) {
        Word word = new Word();
        char[] chars = src.toCharArray();
        for (char ch : chars) {
            Char charr = Char.of(ch);
            word.add(charr);
        }
        return word;
    }

}


