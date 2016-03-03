package com.epam.parser;

import com.epam.text.Paragraph;
import com.epam.text.Sentence;
import com.epam.text.Text;
import com.epam.text.Word;

public interface Parser {

    String PARAGRAPH_REGEX = "paragraph_regex";
    String SENTENCE_REGEX = "sentence_regex";
    String WORD_REGEX = "word_regex";

    Text parseText(String src);

    Paragraph parseParagraph(String src);

    Sentence parseSentence(String src);

    Word parseWord(String src);

}
