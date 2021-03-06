package io.github.hadiahmed098.SimpleCipher;

import javax.swing.*;

public class Decoder {

    private static final Decoder INSTANCE = new Decoder();

    public static String decodeStart(String input)
    {
        return INSTANCE.decode(input);
    }

    private String decode(String input)
    {
        if(input.length()==0)
            return "";

        String intString;                                                 //Turn Unicode characters into the int string
        try {
            intString = unicodeToIntString(input);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
            return "";
        }

        String mCode = deRandomize(intString);                            //Turn int string into Morse Code

        String output = "";

        String[] mCodeArray = mCode.split("/");                    //Split the morse code string into an array based on '/'
        for(String s: mCodeArray) {
            if(s.length() == 0)                                           //If the string has length 0 it is a space
                output = output.concat(" ");
            output = output.concat(morseDeCoder(s + "/"));     //De-Morse Code the result and add to string
        }

        output = output.trim();                                           //String postprocessing before return
        output = output.toUpperCase();

        return output;
    }

    private String unicodeToIntString(String unicodeString)
    {
        char[] unicodeArray = unicodeString.toCharArray();
        String outputString = "";

        for(char c : unicodeArray) {
            int check = Integer.parseInt(String.format("%04x", (int) c)); // Turns the character into the unicode hex

            if(check < 100 || check >199)       // Check charset for valid character between U+0100 and U+0199
                throw new IllegalArgumentException("Output contains invalid character!");


            outputString = outputString.concat(Integer.toString(check).substring(1)); //Trim the first 1 from the code
        }

        return outputString;
    }

    private String deRandomize(String randomCode)
    {
        String dRCode = "";
        String tempString = "";

        for(int i=0;i<randomCode.length()-1;i++)
        {
            tempString = randomCode.substring(i,i+1);                     //tempString is a character of the output string at index i

            if(Integer.parseInt(tempString)%2==0 && !tempString.equals("0"))
            {
                dRCode = dRCode.concat("-");
            } else if(Integer.parseInt(tempString)%2==1)
            {
                dRCode = dRCode.concat(".");
            } else
            {
                dRCode = dRCode.concat("/");
            }
        }

        return dRCode.concat("/");
    }

    private String morseDeCoder(String morseCode)
    {
        //Hardcoded Morse Code library
        String morseChar = "";

        switch(morseCode)
        {
            case ".-/": morseChar = "a"; break;
            case "-.../": morseChar = "b"; break;
            case "-.-./": morseChar = "c"; break;
            case "-../": morseChar = "d"; break;
            case "./": morseChar = "e"; break;
            case "..-./": morseChar = "f"; break;
            case "--./": morseChar = "g"; break;
            case "..../": morseChar = "h"; break;
            case "../": morseChar = "i"; break;
            case ".---/": morseChar = "j"; break;
            case "-.-/": morseChar = "k"; break;
            case ".-../": morseChar = "l"; break;
            case "--/": morseChar = "m"; break;
            case "-./": morseChar = "n"; break;
            case "---/": morseChar = "o"; break;
            case ".--./": morseChar = "p"; break;
            case "--.-/": morseChar = "q"; break;
            case ".-./": morseChar = "r"; break;
            case ".../": morseChar = "s"; break;
            case "-/": morseChar = "t"; break;
            case "..-/": morseChar = "u"; break;
            case "...-/": morseChar = "v"; break;
            case ".--/": morseChar = "w"; break;
            case "-..-/": morseChar = "x"; break;
            case "-.--/": morseChar = "y"; break;
            case "--../": morseChar = "z"; break;
            case "-----/": morseChar = "0"; break;
            case ".----/": morseChar = "1"; break;
            case "..---/": morseChar = "2"; break;
            case "...--/": morseChar = "3"; break;
            case "....-/": morseChar = "4"; break;
            case "...../": morseChar = "5"; break;
            case "-..../": morseChar = "6"; break;
            case "--.../": morseChar = "7"; break;
            case "---../": morseChar = "8"; break;
            case "----./": morseChar = "9"; break;
            case ".-.-.-/": morseChar = "."; break;
            case "--..--/": morseChar = ","; break;
            case "---.../": morseChar = ":"; break;
            case "..--../": morseChar = "?"; break;
            case "-....-./": morseChar = "-"; break;
            case "-..-./": morseChar = "/"; break;
        }

        return morseChar;
    }
}