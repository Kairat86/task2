package com.epam;

import com.epam.parser.Parser;
import com.epam.parser.UniversalParser;
import com.epam.text.*;
import com.epam.parser.ParserImplementation;
import com.epam.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Runner {

    private final static Logger logger = LoggerFactory.getLogger(Runner.class.getSimpleName());
    private static final String REGEX_PROPERTIES_FILE="regex.properties";

    public static void main(String[] args) throws IOException {

        String sourceString = readFromFile("text.txt");
        PropertyManager propertyManager=new PropertyManager();
        propertyManager.load(REGEX_PROPERTIES_FILE);
        Parser parser=new ParserImplementation(propertyManager);
        Text text = parser.parseText(sourceString);
       // System.out.println(text.toPlainText(new StringBuilder()));

    //    System.out.println(component.toPlainText(new StringBuilder()));

        parser=new UniversalParser(propertyManager);
        try {
            text=parser.parse(Text.class,sourceString);
        } catch (OperationNotSupportedException e) {
            System.out.println("Catch");
        }

     //   System.out.println(text.toPlainText(new StringBuilder()));

        Iterator<Word> genericIterator=new GenericIterator<Text,Word>(text,Word.class);

        while (genericIterator.hasNext()){
            System.out.println(genericIterator.next().toPlainText(new StringBuilder()));
        }



    }

    public static String readFromFile(String path) {
        InputStream in = Runner.class.getClassLoader().getResourceAsStream(path);
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.next();
    }


}



