package com.epam.parser;

import com.epam.text.*;
import com.epam.util.PropertyManager;

import javax.naming.OperationNotSupportedException;

public class ParserImplementation implements Parser {

    private PropertyManager propertyManager;

    public ParserImplementation(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }

    @Override
    public <T extends BaseEntity> T parse(Class<T> type, String src) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("This operation is not supported in this parser");
    }

    public Text parseText(String src) {
        Text text = new Text();
        String[] paragraphStrings = src.split(propertyManager.getProperty(PARAGRAPH_REGEX));
        for (String paragraphStr : paragraphStrings) {
            Paragraph paragraph = parseParagraph(paragraphStr);
            text.add(paragraph);
        }
        return text;
    }

    public Paragraph parseParagraph(String src) {
        Paragraph paragraph = new Paragraph();
        String[] sentenceStrs = src.split(propertyManager.getProperty(SENTENCE_REGEX));
        for (String sentenceStr : sentenceStrs) {
            Sentence sentence = parseSentence(sentenceStr);
            paragraph.add(sentence);
        }
        return paragraph;
    }

    public Sentence parseSentence(String src) {
        Sentence sentence = new Sentence();
        String[] wordStrs = src.split(propertyManager.getProperty(WORD_REGEX));
        for (String wordStr : wordStrs) {
            Word word = parseWord(wordStr);
            sentence.add(word);
        }
        return sentence;
    }

    public Word parseWord(String src) {
        Word word = new Word();
        char[] chars = src.toCharArray();
        for (char c : chars) {
            Char ch = Char.of(c);
            word.add(ch);
        }
        return word;
    }

}


