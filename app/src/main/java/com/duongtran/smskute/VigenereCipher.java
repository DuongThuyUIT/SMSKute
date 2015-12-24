package com.duongtran.smskute;

import java.util.Scanner;

/**
 * Created by thuydao on 24/12/2015.
 */
public class VigenereCipher {

    public String vigenereCipherEn(String plainText, String key){
        int tableRowSize = 93;
        int tableColumnSize = 93;
        int vignereTable[][] = new int[93][93];
        Scanner input = new Scanner(System.in);
        for (int rows = 0; rows < tableRowSize; rows++){
            for (int columns = 0; columns < tableColumnSize; columns++){
                vignereTable[rows][columns] = (rows + columns) % 93;
            }
        }
//        System.out.println("Enter the plaintext");
//        String plainText = input.nextLine();
//        System.out.print("Enter the key: ");
//        String key = input.nextLine();
        String cipherText = "";
        int keyIndex = 0;
        for (int ptextIndex = 0; ptextIndex < plainText.length();
             ptextIndex++){
            char pChar = plainText.charAt(ptextIndex);
            int asciiVal = (int) pChar;          if (pChar == ' '){
                cipherText += pChar;
                continue;
            }
            if (asciiVal < 30 || asciiVal > 123){
                cipherText += pChar;
                continue;
            }

            int basicPlainTextValue = ((int) pChar) - 31;

            char kChar = key.charAt(keyIndex);
            int basicKeyValue = ((int) kChar ) - 31;
            int tableEntry = vignereTable[basicPlainTextValue][basicKeyValue];
            char cChar = (char) (tableEntry + 31);
            cipherText += cChar;
            keyIndex++;
            if (keyIndex == key.length())
                keyIndex = 0;


        }
        return cipherText;
    }

    public String vigenereCipherDe(String cipherText, String key){
        int tableRowSize = 93;
        int tableColumnSize = 93;
        int vignereTable[][] = new int[93][93];
        Scanner input = new Scanner(System.in);
        for (int rows = 0; rows < tableRowSize; rows++){
            for (int columns = 0; columns < tableColumnSize; columns++){
                vignereTable[rows][columns] = (rows + columns) % 93;
            }
        }
//        System.out.println("Enter the cipher text");
//        String cipherText = input.nextLine();
//        System.out.print("Enter the key: ");
//        String key = input.nextLine();
        String plainText = "";
        int keyIndex = 0;
        for (int ctextIndex = 0; ctextIndex < cipherText.length();
             ctextIndex++){
            char cChar = cipherText.charAt(ctextIndex);
            int asciiVal = (int) cChar;
            if (cChar == ' '){
                plainText += cChar;
                continue;
            }
            if (asciiVal < 30 || asciiVal > 123){
                plainText += cChar;
                continue;
            }
            int basiccipherTextValue = ((int) cChar) - 31;
            char kChar = key.charAt(keyIndex);
            int basicKeyValue = ((int) kChar ) - 31;
            for (int pIndex = 0; pIndex < tableColumnSize; pIndex++){
                if (vignereTable[basicKeyValue][pIndex] == basiccipherTextValue){
                    char potcChar = (char)
                            (vignereTable[basicKeyValue][pIndex] + 31);
                    char potpChar = (char) (pIndex + 31);
                    plainText += potpChar;
                }
            }
            keyIndex++;
            if (keyIndex == key.length())
                keyIndex = 0;
        }
        return plainText;
    }

}
