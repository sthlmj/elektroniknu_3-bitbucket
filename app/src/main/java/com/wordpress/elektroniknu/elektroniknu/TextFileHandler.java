package com.wordpress.elektroniknu.elektroniknu;

import android.content.res.Resources;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TextFileHandler {

    public static String getLine(int position, Resources r) throws IOException{
        InputStream is = r.getAssets().open("StoresPdf.txt");
        BufferedReader textReader = new BufferedReader(new InputStreamReader(is));

        String storeAndUrl;
        for(int i = 1; i < position; i++){
            textReader.readLine();
        }

        storeAndUrl = textReader.readLine();
        textReader.close();
        return storeAndUrl;
    }

    public static String getStoreName(int position, Resources r) throws IOException{
        String storeAndUrl = getLine(position, r);

        return storeAndUrl.split(" ")[0];

    }

    public static String getURL(int position, Resources r) throws IOException{
        String storeAndUrl = getLine(position, r);

        return storeAndUrl.split(" ")[1].replace("\n", "");
    }

    public static int getLines(Resources r) throws IOException{
        InputStream is = r.getAssets().open("StoresPdf.txt");
        BufferedReader textReader = new BufferedReader(new InputStreamReader(is));

        String aLine;
        int numberOfLines = 0;

        while((aLine = textReader.readLine()) != null){
            numberOfLines++;
        }
        textReader.close();

        return numberOfLines;
    }
}
