package com.epam;

import com.epam.factory.TextFactory;
import com.epam.text.Text;
import com.epam.util.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

public class Runner {

   private final static Logger logger= LoggerFactory.getLogger(Runner.class.getSimpleName());

    public static void main(String[] args) throws IOException {

        String sourceString= TextFactory.createTextFromFile("text.txt");


        Text text= Parser.parseText(sourceString);
      System.out.println(text.toString());


     //   System.out.println(text.toPlaitText());



    }



    }



