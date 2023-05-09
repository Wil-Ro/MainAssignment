/**
 * Vigenere Cipher class, encrypts and decrypts given text with a Vigenere Cipher
 * @version 2.0
 * @author Rosia E Evans
 */
public class VigenereCipher extends Cipher{
    String keyHint = "This cipher expects a string";
    String keyString;

    String key;

    public VigenereCipher(){
        keyFileUrl = "Data/vigenere-key.txt";
    }

    /**
     * Encrypts a given string with a given key
     * @param plainText Text to encrypt
     * @return Encrypted text
     * @throws NumberFormatException Thrown when key is incorrect
     */
    public String encryptString(String plainText) throws NumberFormatException{
        char[] key = this.key.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();
        char[] tempArray = plainText.toCharArray();
        for (int i = 0; i < tempArray.length; i++){
            char character = tempArray[i];
            character -= 65;
            character = (char) (Math.floorMod(((int)character + (key[i % key.length])-65), 26));
            character += 65;
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /**
     * Decrypts a given string with a given key
     * @param plainText Text to decrypt
     * @return Decrypted text
     * @throws NumberFormatException
     */
    public String decryptString(String plainText) throws NumberFormatException{
        char[] key = this.key.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();
        char[] tempArray = plainText.toCharArray();
        for (int i = 0; i < tempArray.length; i++){
            char character = tempArray[i];
            character -= 65;
            character = (char) (Math.floorMod(((int)character - (key[i % key.length])-65), 26));
            character += 65;
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /**
     * Validated whether a given key will work for this cipher
     * @param keyString String representation of key
     * @return Whether the key is valid
     */
    public boolean validateKey(String keyString){
        return keyString.matches("[a-zA-Z]*");
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
        key = keyString.toUpperCase();
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
        return "Vignere Cipher";
    }
}

// todo less casting in shifts, just make char always an integer
// todo do we actually need process key????