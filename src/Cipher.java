import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class to represent generic cipher
 * @version 2.0
 * @author Rosia E Evans
 */
public abstract class Cipher {
    String keyHint;
    String keyFileUrl;
    String keyString;


    /**
     * Encrypts a given string with a given key
     * @param plainText Text to encrypt
     * @return Encrypted text
     * @throws NumberFormatException Thrown when key is incorrect
     */
    abstract public String encryptString(String plainText) throws NumberFormatException;

    /**
     * Decrypts a given string with a given key
     * @param plainText Text to decrypt
     * @return Decrypted text
     * @throws NumberFormatException Thrown when invalid key is given
     */
    abstract public String decryptString(String plainText) throws NumberFormatException;

    /**
     * Validated whether a given key will work for this cipher
     * @param keyString String representation of key
     * @return Whether the key is valid
     */
    abstract public boolean validateKey(String keyString);

    /**
     * Internal method to extract key from given string
     * @param keyString String representing key
     * @throws NumberFormatException Thrown when key is invalid
     */
    abstract protected void processKey(String keyString) throws NumberFormatException;

    /**
     * Loads key into cipher from file at given url
     * @throws FileNotFoundException Thrown when invalid url given
     * @throws NumberFormatException Thrown when key loaded is invalid
     */
    public void loadKey() throws FileNotFoundException, NumberFormatException {
        Scanner scan = new Scanner(new FileReader(keyFileUrl));
        keyString = scan.nextLine();
        scan.close();

        processKey(keyString);
    }

    /**
     * Saves key into file at given url
     * @throws FileNotFoundException Thrown when invalid url given
     */
    public void saveKey() throws FileNotFoundException{
        PrintWriter printWriter = new PrintWriter(keyFileUrl);
        printWriter.print(keyString);
        printWriter.close();
    }

    /**
     * Getter for the key hint
     * @return Key hint
     */
    abstract public String getKeyHint();

    /**
     * Setter for Ciphers key
     * @param key Key to set
     * @throws NumberFormatException Thrown when invalid key is given
     */
    public void setKey(String key) throws NumberFormatException{
        if (!validateKey(key)){
            throw new NumberFormatException("Invalid key given");
        }

        keyString = key;
        processKey(keyString);
    }

    /**
     * Getter for Ciphers key
     * @return String representation of currently held key
     */
    public String getKey(){
        return keyString;
    }
}
