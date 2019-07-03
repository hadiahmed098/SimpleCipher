import javax.swing.JOptionPane;
import javax.swing.JTextArea;
public class Decoder {

    public String decodeStart(String input)
    {
        return decode(input);
    }

    private String decode(String input)
    {

        String mCode = deRandomize(input);


        String tempMCode = "";
        String output = "";
        int i = 0;

        while(i < mCode.length()-1)
        {
            while(mCode.charAt(i) != '/')
            {
                tempMCode = tempMCode + mCode.charAt(i);
                i++;
            }
            tempMCode = tempMCode + "/";

            output = output + morseDeCoder(tempMCode);
            tempMCode = "";

            if(i<mCode.length()-1 && mCode.charAt(i+1) == '/')
            {
                output = output + " ";
                i++;
            }
            else
                i++;
        }

        output.trim();
        output = output.toUpperCase();

        return output;
    }

    private String deRandomize(String randomCode)
    {
        String dRCode = "";
        String tempString = "";

        for(int i=0;i<randomCode.length()-1;i++)
        {
            tempString = randomCode.substring(i,i+1);

            if(tempString.indexOf('2')>-1 || tempString.indexOf('4')>-1 || tempString.indexOf('6')>-1 || tempString.indexOf('8')>-1)
            {
                dRCode = dRCode + "-";
            } else if(tempString.indexOf('1')>-1 || tempString.indexOf('3')>-1 || tempString.indexOf('5')>-1 || tempString.indexOf('7')>-1 || tempString.indexOf('9')>-1)
            {
                dRCode = dRCode + ".";
            } else
            {
                dRCode = dRCode + "/";
            }
        }

        return dRCode + "/";
    }

    private String morseDeCoder(String morseCode)
    {
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
            case "-....-.": morseChar = "-"; break;
            case "-..-./": morseChar = "/"; break;
        }

        return morseChar;
    }
}