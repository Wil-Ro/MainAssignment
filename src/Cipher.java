public abstract class Cipher {
    String keyHint;

    abstract public String encryptString(String keyString, String plainText);

    abstract public String decryptString(String keyString, String plainText);

    abstract public String getKeyHint();

    abstract public boolean validateKey(String keyString);

    abstract public void processKey(String keyString);

}
