package com.epam.text;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericIterator<C, T extends BaseEntity> implements Iterator<T> {

    private Iterator<T> iterator;
    private int currentPosition;
    private T iterationEntity;
    private C currentContainer;

    public GenericIterator(Text text, Class<T> clazz) {
        T iterationEntity = null;
        try {
            iterationEntity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Could not initialize an instance");
        }
        this.iterationEntity = iterationEntity;
        iterator = checkAndReturnIterator((C) text, iterationEntity);
    }

    private Iterator<T> checkAndReturnIterator(C container, T iterationEntity) {

        if ((container instanceof Text) && (iterationEntity instanceof Paragraph)) {
            return (Iterator<T>) ((Text) container).iterator();
        } else if (container instanceof Text && !(iterationEntity instanceof Text)) {
            currentContainer = container;
            Text text = (Text) container;
            while (currentPosition < text.size()) {
                return checkAndReturnIterator((C) text.get(currentPosition), iterationEntity);
            }
        } else if ((container instanceof Paragraph) && iterationEntity instanceof Sentence) {
            return (Iterator<T>) ((Paragraph) container).iterator();
        } else if (container instanceof Paragraph && !(iterationEntity instanceof Paragraph)) {
            currentContainer = container;
            Paragraph paragraph = (Paragraph) container;
            while (currentPosition < paragraph.size()) {
                return checkAndReturnIterator((C) paragraph.get(currentPosition), iterationEntity);
            }
        } else if ((container instanceof Sentence) && iterationEntity instanceof Word) {
            return (Iterator<T>) ((Sentence) container).iterator();
        } else if (container instanceof Sentence && !(iterationEntity instanceof Text)) {
            currentContainer = container;
            Sentence sentence = (Sentence) container;
            while (currentPosition < sentence.size()) {
                return checkAndReturnIterator((C) sentence.get(currentPosition), iterationEntity);
            }
        } else if ((container instanceof Word) && iterationEntity instanceof Char) {
            return (Iterator<T>) ((Word) container).iterator();
        } else {
            //  throw new IllegalArgumentException(container.getClass().getSimpleName() + " is not container of " + iterationEntity.getClass().getSimpleName());
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (iterator.hasNext()) {
            return true;
        } else {
            currentPosition++;
            iterator = checkAndReturnIterator(currentContainer, iterationEntity);
            if (iterator == null) {
                return false;
            } else {
                return hasNext();
            }
        }

    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }
}
