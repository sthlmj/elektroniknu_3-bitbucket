package com.wordpress.elektroniknu.elektroniknu;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class TextFileHandler {

    public static String getLine(int position) throws IOException{
        FileReader fr = new FileReader("StoresPdf.txt");
        BufferedReader textReader = new BufferedReader(fr);

        String storeAndUrl;
        for(int i = 1; i < position; i++){
            textReader.readLine();
        }

        storeAndUrl = textReader.readLine();
        textReader.close();
        return storeAndUrl;
    }

    public static String getStoreName(int position) throws IOException{
        String storeAndUrl = getLine(position);

        return storeAndUrl.split(" ")[0];

    }

    public static String getURL(int position) throws IOException{
        String storeAndUrl = getLine(position);

        return storeAndUrl.split(" ")[1].replace("\n", "");
    }

    public static int getLines() throws IOException{
        FileReader fr = new FileReader("StoresPdf.txt");
        BufferedReader bf = new BufferedReader(fr);

        String aLine;
        int numberOfLines = 0;

        while((aLine = bf.readLine()) != null){
            numberOfLines++;
        }
        bf.close();

        return numberOfLines;
    }
}
