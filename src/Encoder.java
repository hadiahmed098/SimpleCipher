import java.util.Random;
public class Encoder {

    public String encodeStart(String input)
    {
        return encode(input);
    }

    private String encode(String input)
    {
        String code = "";
        String tCode = "";
        String rCode = "";

        input = input.toLowerCase();

        for(int i=0; i<input.length(); i++)
        {
            tCode = morseCode(input.charAt(i));
            rCode = randomize(tCode);
            code = code + rCode;
        }
        return code;
    }

    private String morseCode(char letter)
    {
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
            default: response = "/"; break;

        }
        return response;
    }

    private String randomize(String mCode)
    {
        String intString = "";
        String tempInt = "-1";
        int tempTempInt = -1;
        Random r = new Random();

        for(int i=0;i<mCode.length();i++)
        {
            if(mCode.charAt(i) == '-')
            {
                tempInt = Integer.toString((r.nextInt(4)+1)*2);
            }
            else if(mCode.charAt(i) == '.')
            {
                do
                {
                    tempTempInt = r.nextInt(10);
                } while(tempTempInt % 2 == 0);

                tempInt = Integer.toString(tempTempInt);
            }
            else if(mCode.charAt(i) == '/')
            {
                tempInt = "0";
            }
            intString = intString + tempInt;
        }
        return intString;
    }
}