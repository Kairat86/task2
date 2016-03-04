package com.epam.parser;

import com.epam.text.*;

import javax.naming.OperationNotSupportedException;

public interface Parser {

    String PARAGRAPH_REGEX = "paragraph_regex";
    String SENTENCE_REGEX = "sentence_regex";
    String WORD_REGEX = "word_regex";

    <T extends BaseEntity> T parse(Class<T> type, String src) throws OperationNotSupportedException;

    Text parseText(String src);

    Paragraph parseParagraph(String src);

    Sentence parseSentence(String src);

    Word parseWord(String src);


}
