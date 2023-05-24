/**
 * Represents currently chosen cipher in GUI App
 */
public enum CipherOptions {

    /**
     * The Caesar Cipher option
     */
    Caesar{
        @Override
        public String toString(){
            return "Caesar";
        }
    },

    /**
     * The Keyed Caesar Cipher option
     */
    KeyedCaesar {
        @Override
        public String toString(){
            return "Keyed Caesar";
        }
    },

    /**
     * The Vigenere Cipher option
     */
    Vigenere{
        @Override
        public String toString(){
            return "Vigenere";
        }
    }
}
