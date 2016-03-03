package com.epam.text;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Char {

    private final static Logger logger= LoggerFactory.getLogger(Char.class.getSimpleName());

    private char value;


    public Char(char ch) {
        this.value = ch;
    }

    public static Char of(char ch) {

        return Cache.checkAndReturn(ch);
    }

    private static class Cache {
        private static Char[] chArray = new Char[65536];


        static {
            for (int i = 0; i < 1104; i++) {
                chArray[i]=defineCharType(i);
            }
        }



        public static Char checkAndReturn(char ch) {

            if (chArray[ch]==null) {
               logger.info("Added '{}'=>{} to cache", ch,(int)ch);
               return chArray[ch] =defineCharType(ch);
            }
            return chArray[ch];
        }

        private static Char defineCharType(int i) {
            char c=(char)i;
            if("0123456789".indexOf(i)>=0){
                return new Number(c);
            }else if("`~@#$%^&*_+=/|\\><".indexOf(i)>=0){
                logger.info(c+"");
                return new Symbol(c);
            }else if(".?!:;,-()\"[]{}'".indexOf(i)>=0){
                return new Stop(c);
            }else {
                return new Letter(c);
            }
        }
    }

    public char getValue() {
        return value;
    }

}
