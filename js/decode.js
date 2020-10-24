function decode() {
  let input = document.getElementsByName("ciphertext")[0].value;
  
  if(input.length === 0) {
    document.getElementsByName("plaintext")[0].value = "";
  }

  let intString;
  try {
    intString = unicodeToIntString(input);
  } catch (error) {
    alert(error);
    document.getElementsByName("plaintext")[0].value = "";
    return;
  }

  let mCode = deRandomize(intString);
  let output = "";

  let mCodeArray = mCode.split("/");
  for(let s of mCodeArray) {
    if(s.length == 0) {
      output = output.concat(" ");
    }
    output = output.concat(morseDecode(s + "/"));
  }


  document.getElementsByName("plaintext")[0].value = output.toUpperCase().trim();
}

function unicodeToIntString(unicodeString) {
  unicodeArray = [...unicodeString];
  let outputString = "";

  for(let c of unicodeArray) {
    let check = c.codePointAt(0).toString(16);
    
    if(check < 100 || check > 199)
      throw `Invalid Ciphertext Character: U+${c.charCodeAt(0).toString(16)}!`;

    outputString = outputString.concat(check.substring(1));
  }

  return outputString;
}

function deRandomize(randomCode) {
  let dRCode = "";
  let tempString;

  for(let i=0; i<randomCode.length-1; ++i) {
    tempString = randomCode.charAt(i);
    if(parseInt(tempString) % 2 === 0 && parseInt(tempString) !== 0) {
      dRCode = dRCode.concat("-");
    } else if(parseInt(tempString) % 2 == 1) {
      dRCode = dRCode.concat(".");
    } else {
      dRCode = dRCode.concat("/");
    }
  }

  return dRCode.concat("/");
}

function morseDecode(morseCode) {
  let morseChar = "";

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