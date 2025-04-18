import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Casear Cipher, runs basic tests used during development to ensure encrypt and
 * decrypt work
 * @version 1.0
 * @author Rosia E Evans
 */
public class CaesarCipherTest extends CipherTest{
    /**
     * Runs a successful basic encryption
     */
    @Test
    public void successfulEncryption(){
        Cipher cipher = new CaesarCipher();
        String key = "5";
        String text = "The quick brown fox jumped over the lazy dog";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("YMJVZNHPGWTBSKTCOZRUJITAJWYMJQFEDITL", result);
    }

    /**
     * Runs a successful basic decryption
     */
    @Test
    public void successfulDecryption(){
        Cipher cipher = new CaesarCipher();
        String key = "5";
        String text = "Ymj vznhp gwtbs ktc ozruji tajw ymj qfed itl";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.decryptString(text);
        Assert.assertEquals("THEQUICKBROWNFOXJUMPEDOVERTHELAZYDOG", result);
    }

    /**
     * Runs a basic encryption with a large shift
     */
    @Test
    public void largeShiftTest(){
        Cipher cipher = new CaesarCipher();
        String key = "40";
        String text = "abcdefghijklmnopqrstuvwxyz";
        text = convertToPlainText(text);

        cipher.setKey(key);
        String result = cipher.encryptString(text);
        Assert.assertEquals("OPQRSTUVWXYZABCDEFGHIJKLMN", result);
    }



}