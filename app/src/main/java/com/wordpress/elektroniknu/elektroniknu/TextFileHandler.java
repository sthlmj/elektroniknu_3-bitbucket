package com.wordpress.elektroniknu.elektroniknu;

import android.content.res.Resources;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TextFileHandler {

    public static String getLine(int position, Resources r) throws IOException{ // Get line of position position
        InputStream is = r.getAssets().open("StoresPdf.txt"); // InputStream to open StoresPdf.txt
        BufferedReader textReader = new BufferedReader(new InputStreamReader(is)); // BufferedReader to read that file

        String storeAndUrl; // String that content store name and URI
        for(int i = 1; i < position; i++){ // Skip all unnecessary lines
            textReader.readLine(); // Read that line but not save it
        }

        storeAndUrl = textReader.readLine(); // Read that line we want
        textReader.close(); // Close the reader
        is.close();//close the opened file
        return storeAndUrl; // Return the line
    }

    public static String getStoreName(int position, Resources r) throws IOException{ // Get store name of one line
        String storeAndUrl = getLine(position, r); // Call method getLine to get line of position position

        return storeAndUrl.split(" ")[0]; // Return the name of store

    }

    public static String getURL(int position, Resources r) throws IOException{ // Get URI of one line
        String storeAndUrl = getLine(position, r); // Call method getLine to get line of position position

        return storeAndUrl.split(" ")[1].replace("\n", ""); // Return the URI of store
    }

    public static int getLines(Resources r) throws IOException{ // Get total lines in StoresPdf.txt
        InputStream is = r.getAssets().open("StoresPdf.txt"); // InputStream to open StoresPdf.txt
        BufferedReader textReader = new BufferedReader(new InputStreamReader(is)); // BufferedReader to read that file

        String aLine; // String that save line
        int numberOfLines = 0; // Number of lines

        while((aLine = textReader.readLine()) != null){ // While one line isn't empty
            numberOfLines++; // Increase numberOfLines by 1
        }
        textReader.close(); // Close that file

        return numberOfLines; // Return total number lines
    }
}
