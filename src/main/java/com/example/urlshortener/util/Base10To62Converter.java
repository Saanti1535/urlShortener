package com.example.urlshortener.util;

public class Base10To62Converter {

    private static final String allowedString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static char[] allowedCharacters = allowedString.toCharArray();
    private static int base = allowedCharacters.length;

    static public String encode(long input){
    	StringBuilder encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    static public long decode(String input) {
    	char[] characters = input.toCharArray();
    	int length = characters.length;

    	long decoded = 0;
    	int counter = 1;
    	
        for (int i = 0; i < length; i++) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        
        return decoded;
    }
}

