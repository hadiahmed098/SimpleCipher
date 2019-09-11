import javax.swing.JOptionPane;
import java.util.Random;
class Encoder {

    String encodeStart(String input)
    {
        return encode(input);
    }

    private String encode(String input)
    {
        //Initialize variables and sanitize inputs
        String code = "";                                                       //Output
        String tCode;                                                           //Temp variable to hold the value of the morse code
        String rCode;

        input = input.toLowerCase();

        //Go through each character in the input string
        for(int i=0; i<input.length(); i++)
        {
            try
            {
                tCode = morseCode(input.charAt(i));                             //Encode the character into Morse Code
            } catch (RuntimeException e)                                        //Unless the character is invalid
            {
                return "";
            }
            code = code.concat(tCode);
        }
        rCode = randomize(code);
        return intStringToUnicode(rCode);
    }

    private String morseCode(char letter)
    {
        //hardcoded Morse Code library for encoding
        String response = "";
        switch(letter)
        {
            case 'a': response = ".-/"; break;
            case 'b': response = "-.../"; break;
            case 'c': response = "-.-./"; break;
            case 'd': response = "-../"; break;
            case 'e': response = "./"; break;
            case 'f': response = "..-./"; break;
            case 'g': response = "--./"; break;
            case 'h': response = "..../"; break;
            case 'i': response = "../"; break;
            case 'j': response = ".---/"; break;
            case 'k': response = "-.-/"; break;
            case 'l': response = ".-../"; break;
            case 'm': response = "--/"; break;
            case 'n': response = "-./"; break;
            case 'o': response = "---/"; break;
            case 'p': response = ".--./"; break;
            case 'q': response = "--.-/"; break;
            case 'r': response = ".-./"; break;
            case 's': response = ".../"; break;
            case 't': response = "-/"; break;
            case 'u': response = "..-/"; break;
            case 'v': response = "...-/"; break;
            case 'w': response = ".--/"; break;
            case 'x': response = "-..-/"; break;
            case 'y': response = "-.--/"; break;
            case 'z': response = "--../"; break;
            case '0': response = "-----/"; break;
            case '1': response = ".----/"; break;
            case '2': response = "..---/"; break;
            case '3': response = "...--/"; break;
            case '4': response = "....-/"; break;
            case '5': response = "...../"; break;
            case '6': response = "-..../"; break;
            case '7': response = "--.../"; break;
            case '8': response = "---../"; break;
            case '9': response = "----./"; break;
            case '.': response = ".-.-.-/"; break;
            case ',': response = "--..--/"; break;
            case ':': response = "---.../"; break;
            case '?': response = "..--../"; break;
            case '-': response = "-....-./"; break;
            case '/': response = "-..-./"; break;
            case ' ': response= "/"; break;
            //Check for invalid characters
            default: JOptionPane.showMessageDialog(null,String.format("Invalid Character: \'%s\'", letter), "Error",JOptionPane.ERROR_MESSAGE); throw new RuntimeException("Invalid Character");

        }
        return response;
    }

    private String randomize(String mCode)
    {
        String intString = "";
        String tempInt = "-1";
        Random r = new Random();
        for(int i=0;i<mCode.length();i++)                                       //Go through each character in the Morse Code string
        {
            if(mCode.charAt(i) == '-') //If dash, convert into an even number
            {
                tempInt = Integer.toString((r.nextInt(4)+1)*2);       //Random int on bounds 0-3, adds 1 and multiplies by 2 to avoid under 0 and over 10
                                                                                //Gives an even number in range [2-8]
            }
            else if(mCode.charAt(i) == '.') //If dot, convert into an odd number.
            {
                tempInt = Integer.toString(r.nextInt(5)*2 + 1);       //Random int on bounds 0-4, multiplies by 2, and adds 1 to avoid under 0 and over 10
                                                                                //Gives an odd number in range[1-9]
            }
            else if(mCode.charAt(i) == '/') //If slash, convert into 0
            {
                tempInt = "0";
            }
            intString = intString.concat(tempInt);
        }
        return intString;
    }

    private String intStringToUnicode(String intString)
    {
        String outputString = "";

        if(intString.length()%2!=0) {
            intString = intString.concat("0");
        }

        for(int i=0; i<intString.length(); i=i+2) {
            String subIntString = intString.substring(i,i+2);

            outputString = outputString.concat(Character.toString((char) Integer.parseInt("01"+subIntString,16)));
        }
        return outputString;
    }
}