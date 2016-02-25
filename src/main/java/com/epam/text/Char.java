package com.epam.text;


public class Char {


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
                chArray[i] = new Char((char) i);
            }
        }

        public static Char checkAndReturn(char ch) {
            if (ch == chArray.length) {
                return chArray[ch];
            }
            return chArray[ch] = new Char(ch);
        }
    }

    public char getValue() {
        return value;
    }

}
