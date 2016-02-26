package com.epam.text;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Char {

    private final static Logger logger= LoggerFactory.getLogger(Char.class.getSimpleName());

    private char value;

    private Char(char ch) {
        this.value = ch;
    }

    public static Char of(char ch) {

        return Cache.checkAndReturn(ch);
    }

    private static class Cache {
        static Char[] chArray = new Char[65536];

        static {
            for (int i = 0; i < 1071; i++) {
                chArray[i] = new Char((char)i);
            }
        }

        public static Char checkAndReturn(char ch) {
            if (ch == chArray[(int)ch].value) {
                logger.info("Got char from cache");
                return chArray[ch];
            }
            return chArray[ch] = new Char(ch);
        }
    }

    public char getValue() {
        return value;
    }

}
