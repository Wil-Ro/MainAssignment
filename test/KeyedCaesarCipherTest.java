import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Keyed Caesar Cipher, runs basic tests used during development to ensure encrypt and
 * decrypt work
 * @version 1.0
 * @author Rosia E Evans
 */
public class KeyedCaesarCipherTest extends CipherTest{
    /**
     * Runs a successful basic encryption
     */
    @Test
    public void successfulEncryption(){
        Cipher cipher = new KeyedCaesarCipher();
        String key = "5 LEMON";
        String text = "The quick brown fox jumped over the lazy dog";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("YIFVZJCPBWTESGTMKZRUFDTLFWYIFQANODTH", result);
    }

    /**
     * Runs a successful basic decryption
     */
    @Test
    public void successfulDecryption(){
        Cipher cipher = new KeyedCaesarCipher();
        String key = "5 LEMON";
        String text = "Yif vzjcp bwtes gtm kzrufd tlfw yif qano dth";
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
        Cipher cipher = new KeyedCaesarCipher();
        String key = "12 qwertyuiop";
        String text = "abcdefghijklmnopqrstuvwxyz";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("CDFGHJKLMNSVXZQWERTYUIOPAB", result);
    }

    /**
     * Tests Cipher deals with keys with duplicate letters,
     * Note: does not test whether encryption works as a whole
     */
    @Test
    public void DuplicateLetterTest(){
        Cipher cipher = new KeyedCaesarCipher();
        String key = "5 bananna";
        String text = "abcdefghijklmnopqrstuvwxyz";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        result = cipher.decryptString(result);
        Assert.assertEquals(text, result);
    }

    /**
     * Tests whether keys are correctly altered
     */
    @Test
    public void mixedCaseKey(){
        Cipher cipher = new KeyedCaesarCipher();
        String key = "12 qwertyUIOp";
        String text = "abcdefghijklmnopqrstuvwxyz";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("CDFGHJKLMNSVXZQWERTYUIOPAB", result);
    }
}