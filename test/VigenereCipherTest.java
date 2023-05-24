import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Vigenere Cipher, runs basic tests used during development to ensure encrypt and
 * decrypt work
 * @version 1.0
 * @author Rosia E Evans
 */
public class VigenereCipherTest extends CipherTest{
    /**
     * Runs a successful basic encryption
     */
    @Test
    public void successfulEncryption(){
        Cipher cipher = new VigenereCipher();
        String key = "LEMON";
        String text = "The quick brown fox jumped over the lazy dog";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("ELQEHTGWPEZAZTBINGACPHAJRCXTSYLDKRBR", result);
    }

    /**
     * Runs a successful basic decryption
     */
    @Test
    public void successfulDecryption(){
        Cipher cipher = new VigenereCipher();
        String key = "LEMON";
        String text = "ELQEHTGWPEZAZTBINGACPHAJRCXTSYLDKRBR";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.decryptString(text);
        Assert.assertEquals("THEQUICKBROWNFOXJUMPEDOVERTHELAZYDOG", result);
    }

    /**
     * Tests whether values wrap around correctly
     */
    @Test
    public void wrapAroundTest(){
        Cipher cipher = new VigenereCipher();
        String key = "qwertyuiop";
        String text = "abcdefghijklmnopqrstuvwxyz";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("QXGUXDAPWYAHQEHNKZGIKRAORX", result);
    }

    /**
     * Tests whether keys are correctly altered
     */
    @Test
    public void mixedCaseKey(){
        Cipher cipher = new VigenereCipher();
        String key = "qwertyUIOp";
        String text = "abcdefghijklmnopqrstuvwxyz";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("QXGUXDAPWYAHQEHNKZGIKRAORX", result);
    }
}
