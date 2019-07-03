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
        String code = "";   //Output
        String tCode = "";  //Transcribes Input -> Morse Code
        String rCode = "";  //Randomizes Morse Code -> Output

        input = input.toLowerCase();

        //Go through each character in the input string
        for(int i=0; i<input.length(); i++)
        {
            try
            {
                tCode = morseCode(input.charAt(i)); //Encode the character into Morse Code
            } catch (RuntimeException e)            //Unless the character is invalid
            {
                return "";
            }
            rCode = randomize(tCode); //Set the Morse Code pattern to a number pattern based on set of rules
            code = code + rCode;      //Add the number pattern to the output string
        }
        return code;
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
            case '-': response = "-....-."; break;
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
        int tempTempInt = -1;
        Random r = new Random();
        //TODO fix generation of random number to be the same for even/odd and more coherent
        for(int i=0;i<mCode.length();i++) //Go through each character in the Morse Code string
        {
            if(mCode.charAt(i) == '-')
            {
                tempInt = Integer.toString((r.nextInt(4)+1)*2); //If dash, convert into an even number
            }
            else if(mCode.charAt(i) == '.') //If dot, convert into an odd number.
            {
                do
                {
                    tempTempInt = r.nextInt(10); //generate random number and confirm its odd
                } while(tempTempInt % 2 == 0);

                tempInt = Integer.toString(tempTempInt);
            }
            else if(mCode.charAt(i) == '/') //If slash, convert into 0
            {
                tempInt = "0";
            }
            intString = intString + tempInt;
        }
        return intString;
    }
}