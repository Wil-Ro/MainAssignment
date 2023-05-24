import java.util.ArrayList;

/**
 * Keyed Caesar Cipher class, encrypts and decrypts given text with a Keyed Caesar Cipher
 * @version 2.0
 * @author Rosia E Evans
 */
public class KeyedCaesarCipher extends Cipher{
    char[] wheel;
    int shift;

    /**
     * Keyed Caesar Cipher's constuctor, sets default values
     */
    public KeyedCaesarCipher(){

        keyFileUrl = "Data/keyed-caesar-key.txt";
        keyHint = "This cipher expects a number and a string separated by a space";
    }

    /**
     * Encrypts a given string with a given key
     * @param plainText Text to encrypt
     * @return Encrypted text
     * @throws NumberFormatException Thrown when key is incorrect
     */
    public String encryptString(String plainText) throws NumberFormatException{
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : plainText.toCharArray()){
            character -= 65;
            character = (char) (Math.floorMod(((int)character + shift), 26));
            stringBuilder.append(wheel[character]);
        }
        return stringBuilder.toString();
    }

    /**
     * Decrypts a given string with a given key
     * @param plainText Text to decrypt
     * @return Decrypted text
     * @throws NumberFormatException Throw when invalid key is given
     */
    public String decryptString(String plainText) throws NumberFormatException{
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : plainText.toCharArray()){
            int charIndex = 0;
            for (int i = 0; i < wheel.length; i++) {
                if (character == wheel[i]){
                    charIndex = i;
                    break;
                }
            }

            charIndex = Math.floorMod((charIndex-shift), 26);
            stringBuilder.append((char)(charIndex+65));
        }

        return stringBuilder.toString();
    }

    /**
     * Validated whether a given key will work for this cipher
     * @param keyString String representation of key
     * @return Whether the key is valid
     */
    public boolean validateKey(String keyString){
        return keyString.matches("^-*[0-9]+ [a-zA-Z]+");
    }

    /**
     * Internal method to extract key from given string
     * @param keyString String representing key
     * @throws NumberFormatException Thrown when key is invalid
     */
    protected void processKey(String keyString) throws NumberFormatException{
        if (!validateKey(keyString)){
            throw new NumberFormatException("Key invalid for this cipher");
        }

        String[] keyParts = keyString.split(" ");

        shift = Integer.parseInt(keyParts[0]);

        String keyWord = keyParts[1].toUpperCase();
        keyWord = removeDuplicateLetters(keyWord);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet = alphabet.replaceAll("[" + keyWord + "]", "");
        String wheelString = (keyWord + alphabet).toUpperCase();
        wheel = wheelString.toCharArray();
    }

    /**
     * Internal method that removes duplicate letters from a string
     * @param text String to alter
     * @return Altered string
     */
    private String removeDuplicateLetters(String text){
        ArrayList<Character> checked = new ArrayList<Character>();
        StringBuilder builder = new StringBuilder(text);
        for (int i = 0; i < builder.length();)
        {
            if (checked.contains(builder.charAt(i))){
                builder.deleteCharAt(i);
            }
            else{
                checked.add(text.charAt(i));
                i++;
            }
        }
        return builder.toString();
    }

    /**
     * Getter for the key hint
     * @return Key hint
     */
    public String getKeyHint() {
        return keyHint;
    }

    /**
     * Gets string representation of object
     * @return String representation of object
     */
    @Override
    public String toString(){
        return "Keyed Caesar Cipher";
    }
}
