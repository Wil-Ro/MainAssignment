public enum CipherOptions {
    Caesar{
        @Override
        public String toString(){
            return "Caesar";
        }
    },
    KeyedCaesar {
        @Override
        public String toString(){
            return "Keyed Caesar";
        }
    },
    Vigenere{
        @Override
        public String toString(){
            return "Vigenere";
        }
    }
}
