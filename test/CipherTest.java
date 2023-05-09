public class CipherTest {
    protected String convertToPlainText(String text){
        text = text.toUpperCase();
        return text.replaceAll("[0-9,.!?\" ]", "");
    }

}
