public class KeyedCaesarCipher extends Cipher{
    String keyHint = "This cipher expects a number and a string separated by a space";

    char[] wheel;
    int shift;


    public String encryptString(String keyString, String plainText){
        processKey(keyString);

        StringBuilder stringBuilder = new StringBuilder();
        for (char character : plainText.toCharArray()){
            character -= 65;
            character = (char) (Math.floorMod(((int)character + shift), 26));
            stringBuilder.append(wheel[character]);
        }
        return stringBuilder.toString();
    }

    public String decryptString(String keyString, String plainText){
        processKey(keyString);

        StringBuilder stringBuilder = new StringBuilder();
        for (char character : plainText.toCharArray()){
            int charIndex = 0;
            for (int i = 0; i < wheel.length; i++) {
                if (character == wheel[i]){
                    charIndex = i;
                    break;
                }
            }

            stringBuilder.append((char)(charIndex+64));
        }

        return stringBuilder.toString();
    }

    public String getKeyHint() {
        return keyHint;
    }

    public boolean validateKey(String keyString){
        return keyString.matches("^[0-9]* [a-zA-Z]*");
    }

    public void processKey(String keyString){
        String[] keyParts = keyString.split(" ");

        shift = Integer.parseInt(keyParts[0]);

        String keyWord = keyParts[1];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        alphabet.replaceAll(keyWord, "");
        String wheelString = (keyWord + alphabet).toUpperCase();
        wheel = wheelString.toCharArray();
    }
}
