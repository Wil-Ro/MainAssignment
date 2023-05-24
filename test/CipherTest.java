/**
 * The parent class for all cipher tests
 */
public class CipherTest {
    /**
     * Converts given text to prepared text
     * @param text Text to alter
     * @return Altered text
     */
    protected String convertToPlainText(String text){
        text = text.toUpperCase();
        return text.replaceAll("[0-9,.!?\" ]", "");
    }

}
