function encode() {
  console.log("click");
  let input = document.getElementsByName("plaintext")[0].value.toLowerCase();
  let code = "";
  let tCode;
  let rCode;

  for(let i=0; i<input.length; ++i) {
    try {
      tCode = morseCode(input.charAt(i));
    } catch (error) {
      document.getElementsByName("plaintext")[0].value = "";
      return;
    }

    code = code.concat(tCode);
  }
  rCode = randomize(code);
  document.getElementsByName("ciphertext")[0].value = intStringToUnicode(rCode);
}

function morseCode(letter) {
  let response = "";
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
    default : alert(`Invalid Character: U+${letter.charCodeAt(0).toString(16)}!`); 
              throw "Invalid Character";
  }

  return response;
}

function randomize(mCode) {
  let intString = "";
  let tempString = "";

  for(let i=0; i<mCode.length; ++i) {
    if(mCode.charAt(i) === "-") {
      // Gives an even number in range [2-8]
      tempString = ((Math.floor(Math.random() * (3 - 0)) + 1) * 2).toString();
    } else if(mCode.charAt(i) === ".") {
      // Gives an odd number in range [1-9]
      tempString = ((Math.floor(Math.random() * (4 - 0)) * 2) + 1).toString();
    } else {
      tempString = "0";
    }

    intString = intString.concat(tempString);
  }

  return intString;
}

function intStringToUnicode(intString) {
  let outputString = "";

  if(intString.length % 2 != 0) { // Needs to be an even number of chars to encode
    intString = intString.concat("0");
  }

  for(let i=0; i<intString.length; i=i+2) {
    let subIntString = intString.substring(i, i+2);
    console.log("test");
    let wonkyHexStuff = parseInt("01"+subIntString, 16);
    outputString = outputString.concat(String.fromCodePoint(wonkyHexStuff));
    console.log(wonkyHexStuff);
  }

  return outputString;
}