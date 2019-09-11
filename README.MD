Cipher Application that encodes any string into a random list of numbers. When decoded, these numbers will result in the original text.
Currently only supports alphanumeric characters and basic punctuation.

### METHOD OF ENCODING ###
Converts any string into equivalent Morse Code and turns the dots, dashes, and slashes into a predetermined type of number. This number is then converted into 
a string of predetermined Unicode characters.

Dots are odd numbers, Dashes are even numbers, and slashes representing end of character and spaces are zeros. This string of integers is then
turned into Unicode characters between U+0100 and U+0199.
Every run, the numbers and characters in the encoded string will change, but they follow the pattern above.

### Project History ###
Originally thought of in late 2016 and implemented in a Proof of Concept by early 2017. This is technically the second version with a proper UI.
This program has no real value as its a very weak encoding method. It's meant as a fun project.