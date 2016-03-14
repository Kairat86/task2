package com.epam.text;

import java.util.*;

public class DeepIterator<T extends BaseEntity> implements Iterator<T> {

    private Iterator<T> iterator;
    private int currentPosition;
    private T iterationEntity;
    private Stack<Integer> positionStack;
    private Stack<BaseEntity> containerStack;
    private static final ArrayList l = new ArrayList();
    private int size;
    private Iterator<T> iteratorForSizeCalc;

    public DeepIterator(Text text, Class<T> clazz) {
        T iterationEntity = null;
        try {
            iterationEntity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Could not initialize an instance");
        }
        this.iterationEntity = iterationEntity;
        positionStack = new Stack<>();
        containerStack = new Stack<>();
        iterator = checkAndReturnIterator(text, iterationEntity);
    }

    private Iterator<T> checkAndReturnIterator(BaseEntity container, T iterationEntity) {

        if (iterationEntity instanceof Text) {
            throw new IllegalArgumentException("You can't iterate over Text!");
        } else if ((container instanceof Text) && (iterationEntity instanceof Paragraph)) {
            return (Iterator<T>) ((Text) container).iterator();
        } else if (container instanceof Text) {
            Text text = (Text) container;
            while (currentPosition < text.size()) {
                positionStack.push(currentPosition + 1);
                containerStack.push(text);
                return checkAndReturnIterator(text.get(currentPosition), iterationEntity);
            }
            return l.iterator();
        } else if ((container instanceof Paragraph) && iterationEntity instanceof Sentence) {
            return (Iterator<T>) ((Paragraph) container).iterator();
        } else if (container instanceof Paragraph) {
            Paragraph paragraph = (Paragraph) container;
            while (currentPosition < paragraph.size()) {
                positionStack.push(currentPosition + 1);
                containerStack.push(paragraph);
                return checkAndReturnIterator((paragraph.get(currentPosition)), iterationEntity);
            }
            currentPosition = positionStack.pop();
            checkAndReturnIterator(containerStack.pop(), iterationEntity);
        } else if ((container instanceof Sentence) && iterationEntity instanceof SentencePart) {
            return (Iterator<T>) ((Sentence) container).iterator();
        } else if (container instanceof Sentence) {
            Sentence sentence = (Sentence) container;
            while (currentPosition < sentence.size()) {
                positionStack.push(currentPosition + 1);
                containerStack.push(sentence);
                return checkAndReturnIterator(sentence.get(currentPosition), iterationEntity);
            }
            currentPosition = positionStack.pop();
            checkAndReturnIterator(containerStack.pop(), iterationEntity);
        } else if (container instanceof SentencePart) {
            return (Iterator<T>) ((SentencePart) container).iterator();
        }
        return l.iterator();
    }

    @Override
    public boolean hasNext() {
        if (iterator.hasNext()) {
            return true;
        }
        try {
            currentPosition = positionStack.pop();
            iterator = checkAndReturnIterator(containerStack.pop(), iterationEntity);
        } catch (EmptyStackException ese) {
            return false;
        }
        return hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }

}
