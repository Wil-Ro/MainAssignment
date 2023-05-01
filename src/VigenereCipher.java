public class VigenereCipher extends Cipher{
    String keyHint = "This cipher expects a string";

    String key;

    public String encryptString(String keyString, String plainText){
        char[] key = keyString.toCharArray();

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

    public String decryptString(String keyString, String plainText){

        return plainText;
    }

    public String getKeyHint() {
        return keyHint;
    }

    public boolean validateKey(String keyString){
        return keyString.matches("[a-zA-Z]*");
    }

    public void processKey(String keyString){
        key = keyString.toUpperCase();
    }
}

// todo less casting in shifts, just make char always an integer
// todo key sanitisation method?