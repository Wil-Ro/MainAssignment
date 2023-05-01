public class CaesarCipher extends Cipher{
    String keyHint = "This cipher expects a number";

    int shift;

    public String encryptString(String keyString, String plainText) throws NumberFormatException{
        processKey(keyString);
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : plainText.toCharArray()){
            character -= 65;
            character = (char) (Math.floorMod(((int)character + shift), 26));
            character += 65;
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    public String decryptString(String keyString, String plainText) throws NumberFormatException{
        processKey(keyString);
        shift = 0-shift;
        return encryptString(String.valueOf(shift), plainText);
    }

    public String getKeyHint() {
        return keyHint;
    }

    public boolean validateKey(String keyString){
        return keyString.matches("[0-9]*");
    }

    public void processKey(String keyString){
        shift = Integer.parseInt(keyString);
    }
}
