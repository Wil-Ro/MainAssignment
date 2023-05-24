/**
 * Caesar Cipher class, encrypts and decrypts given text with a Caesar Cipher
 * @version 2.0
 * @author Rosia E Evans
 */
public class CaesarCipher extends Cipher{
    int shift;

    /**
     * Constructor for Caesar Cipher
     */
    public CaesarCipher(){
        keyFileUrl = "Data/caesar-key.txt";
        keyHint = "This cipher expects a number";
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
            character += 65;
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /**
     * Decrypts a given string with a given key
     * @param plainText Text to decrypt
     * @return Decrypted text
     * @throws NumberFormatException Thrown when invalid key is given
     */
    public String decryptString(String plainText) throws NumberFormatException{
        shift = 0-shift;
        String result = encryptString(plainText);
        shift = 0-shift;
        return result;
    }

    /**
     * Validated whether a given key will work for this cipher
     * @param keyString String representation of key
     * @return Whether the key is valid
     */
    public boolean validateKey(String keyString){
        return keyString.matches("-*[0-9]+");
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

        shift = Integer.parseInt(keyString);
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
        return "Caesar Cipher";
    }
}
