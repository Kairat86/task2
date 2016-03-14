package com.epam.service;

import com.epam.text.DeepIterator;
import com.epam.text.Sentence;
import com.epam.text.Text;
import com.epam.text.SentencePart;
import com.epam.util.HashMapHelper;

import java.util.*;

public class TextService {


    public int sameWordSentenceMaxNum(Text text) {
        DeepIterator<Sentence> sentenceDeepIterator = new DeepIterator<>(text, Sentence.class);
        HashMapHelper map = new HashMapHelper();
        List<SentencePart> usedWordList = new ArrayList<>();
        int currentIteratorPosition = 0;

        while (sentenceDeepIterator.hasNext()) {

            Sentence sentence = sentenceDeepIterator.next();
            currentIteratorPosition++;

            for (SentencePart sentencePart : sentence) {
                DeepIterator<Sentence> deepIterator = new DeepIterator<>(text, Sentence.class);

                int i = 0;
                while (i < currentIteratorPosition) {
                    deepIterator.next();
                    i++;
                }
                while (deepIterator.hasNext()) {
                    if(!usedWordList.contains(sentencePart.getWord())) {
                        usedWordList.add(sentencePart);
                    }
                    if (deepIterator.next().contains(sentencePart)&&usedWordList.contains(sentencePart)&&!sentencePart.getWord().isEmpty()) {
                        map.put(sentencePart.getWord().toLowerCase(), map.get(sentencePart.getWord()) == null ? 0 : map.get(sentencePart.getWord()) + 1);
                        System.out.println(sentencePart.getWord());
                    }

                }



            }
        }
        map.getKey();
        return map.getMaxVal();
    }
}
