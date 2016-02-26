package com.epam;

import com.epam.text.Text;
import com.epam.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

public class Runner {

    private final static Logger logger = LoggerFactory.getLogger(Runner.class.getSimpleName());

    public static void main(String[] args) throws IOException {

        String sourceString = readFromFile("text.txt");


        Text text = Parser.parseText(sourceString);
        System.out.println(text.get(6).toString());
        System.out.println(text.get(6).get(0).toPlainText(new StringBuilder()));
    }

    public static String readFromFile(String path) {
        InputStream in = Runner.class.getClassLoader().getResourceAsStream(path);
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.next();


    }
}



