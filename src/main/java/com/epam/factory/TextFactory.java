package com.epam.factory;

import java.io.InputStream;
import java.util.Scanner;

public class TextFactory {

    public static String createTextFromFile(String path) {
        InputStream in = TextFactory.class.getClassLoader().getResourceAsStream(path);
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.next();
    }
}
